package com.demo.aspect;

import com.demo.controller.BaseController;
import com.demo.pojo.OperateRecord;
import com.demo.service.OperateRecordService;
import com.demo.service.PermsService;
import com.demo.service.RolePermsService;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class HttpAspect extends BaseController {

    @Autowired
    private RolePermsService rolePermissionService;

    @Autowired
    private PermsService permissionService;

    @Autowired
    private OperateRecordService operatingRecordService;

    private final static Logger log = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.demo.controller..*(..))")
    public void log() {

    }
    /**
     * @desc: 记录请求
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        shiroFilter(joinPoint);
    }

    /**
     * @desc: 响应请求
     */
    @After("log()")
    public void doAfter() {
        log.info("========================== ↓响应请求↓ ==========================");
    }

    /**
     * @desc: 打印返回值
     */
    @AfterReturning(returning = "obj",pointcut = "log()")
    public void doAfterReturnning(Object obj) {
         log.info("请求返回值：{}",obj);
    }


    /**
     * @desc: 统一参数验证处理
     */
    @Around("execution(* com.demo.controller..*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {

        shiroFilter(pjp);

        Object retVal;
        if (bindingResult.hasErrors()) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(),bindingResult.getFieldError().getDefaultMessage(),null);
        } else {
            retVal = pjp.proceed();
        }
        return retVal;
    }

    /**
     * @desc: 请求拦截器
     */
    public void shiroFilter(JoinPoint joinPoint){

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

        String requestUrl = request.getRequestURI().replaceAll(request.getContextPath(), "");
            String remoteAddr = request.getRemoteAddr();
            String method = request.getMethod();
            String args = Arrays.toString(joinPoint.getArgs());

        log.info("========================== ↓收到请求↓ ==========================");
        log.info("请求url:{}",requestUrl);
        log.info("请求源ip:{}",remoteAddr);
        log.info("请求方式:{}",method);
        log.info("请求方法:{}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+ "()");
        log.info("请求参数:{}", args);
        log.info("getContextPath:{}",request.getContextPath());
        log.info("========================== ↑收到请求↑ ==========================");

        OperateRecord or = new OperateRecord();
        or.setRequestUrl(requestUrl);
        or.setRemoteAddr(remoteAddr);
        or.setMethod(method);
       // or.setParams(args);   //参数太长会报错
        or.setCreateTime(new Date());
        or.setUserId(super.getUserId());

        //下面根据请求的url，进行权限验证
        Integer count = permissionService.findCountByUrl(requestUrl);
          //如果请求的url存在数据库中，说明是有权限的，否则不需要权限

        or.setIsSuccess(1);
        operatingRecordService.insert(or);
    }
}
