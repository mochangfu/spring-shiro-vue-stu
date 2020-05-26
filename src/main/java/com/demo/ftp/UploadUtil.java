package com.demo.ftp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 上传辅助类
 */
public final class UploadUtil {

    public static String downloadFile(String namespace, String fileName, String newName,HttpServletRequest request,HttpServletResponse response){
        if(namespace==null)namespace="file";
        String url = "G:\\ftpserver\\"+namespace+"\\"+fileName;
        File fileurl = new File(url);
        //浏览器下载后的文件名称showValue,从url中截取到源文件名称以及，以及文件类型，如board.docx;

        System.out.println(fileName);
        try{
            //根据条件得到文件路径
            System.out.println("===========文件路径==========="+fileurl);
            //将文件读入文件流
            InputStream inStream = new FileInputStream(fileurl);
            //获得浏览器代理信息
            final String userAgent = request.getHeader("USER-AGENT");
            //判断浏览器代理并分别设置响应给浏览器的编码格式
            String finalFileName = null;
            if(StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent,"Trident")){//IE浏览器
                finalFileName = URLEncoder.encode(fileName,"UTF8");
                System.out.println("IE浏览器");
            }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
                finalFileName = new String(fileName.getBytes(), "ISO8859-1");
            }else{
                finalFileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器
            }
            if(StringUtils.isEmpty(newName))newName=finalFileName;
            //设置HTTP响应头
            response.reset();//重置 响应头
            response.setContentType("application/x-download");//告知浏览器下载文件，而不是直接打开，浏览器默认为打开
            response.addHeader("Content-Disposition" ,"attachment;filename=\"" +newName+ "\"");//下载文件的名称

            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
            response.getOutputStream().close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String readFile(String namespace, String fileName, HttpServletResponse response)throws IOException{
        if(namespace==null)namespace="file";
        ServletOutputStream out = null;
        FileInputStream ips = null;
        String url = "G:\\ftpserver\\"+namespace+"\\"+fileName;
        try {
//获取图片存放路径 
            File file = new File(url);
            if(!file.exists()) {
                return null;
            }
            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
//读取文件流  
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
        return null;
    }
    /**
     * 上传文件处理(支持批量)
     *
     * @param request
     * @param namespace
     */
    public static Map<String, String> uploadFile(HttpServletRequest request, String namespace)throws  Exception {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String, String> fileNames = new HashMap<String, String>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            while (iterator.hasNext()) {
                String key = iterator.next();
                MultipartFile multipartFile = multiRequest.getFile(key);
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    if (!"".equals(name)) {
                        fileNames.put("name",name);
                        String[] strArray = name.split("\\.");
                        int suffixIndex = strArray.length -1;
                        String endName = strArray[suffixIndex];
                        Calendar c=Calendar.getInstance();
                        String fileName = c.get(Calendar.YEAR)+""+c.get(Calendar.MONDAY)+c.get(Calendar.DATE)+c.get(Calendar.MINUTE)+"_"+c.hashCode()+"."+endName;
                        try {
                            String fileNme = saveLocal(multipartFile, namespace, fileName);
                            if (fileNme != null && !"".equals(fileNme)) {
                                fileNames.put(key, fileName);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
        return fileNames;
    }
    public static String uploadFile(MultipartFile file,String namespace) {
        if(namespace==null)namespace="file";
        if (!file.isEmpty()) {
            try {
                String fileName=file.getName();
                String filePath = "G:\\ftpserver\\"+namespace+"\\"+fileName;
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(filePath)));
                System.out.println(file.getName());

                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    /**
     * 上传文件处理
     *
     * @param request
     * @param namespace
     */
    public static Map<String, String> uploadImg(HttpServletRequest request, String namespace)throws  Exception {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String, String> fileNames = new HashMap<String, String>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            while (iterator.hasNext()) {
                String key = iterator.next();
                MultipartFile multipartFile = multiRequest.getFile(key);
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    if (!"".equals(name)) {
                        Date d = new Date();
                        String[] strArray = name.split("\\.");
                        int suffixIndex = strArray.length -1;
                        name =strArray[suffixIndex];
                        String fileName = d.getYear()+""+d.getMonth()+d.getDay()+d.getMinutes()+d.getSeconds()+"."+name.trim();
                        try {
                            String fileNme = saveLocal(multipartFile, namespace, fileName);
                            if (fileNme != null && !"".equals(fileNme)) {
                                fileNames.put(key, fileNme);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
        return fileNames;
    }
    public static String saveLocal(MultipartFile multipartFile, String namespace, String fileName) {
        if(namespace==null)namespace="file";
        if (multipartFile == null) {
            throw new RuntimeException("文件" + fileName + "不存在");
        }
        String path = "G:\\ftpserver\\"+namespace;
        String filePath = "G:\\ftpserver\\"+namespace+"\\"+fileName;
        try {
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            File file = new File(filePath);
            multipartFile.transferTo(file);;
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 完毕，关闭所有链接
        }
            return fileName;

    }


    /**
     * 文件转二进制
     *
     * @param filePath
     */
    public static byte[] getFile2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}

