/**
 * Copyright (C), 2015-2020, lianfankeji
 * FileName: Staa
 * Author: 25414
 * Date: 2020/6/8 1:37
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.demo.service.impl;

public class ScoreStats {
    private String score;

    private Integer count;
    private Double percent;
    private Integer scoreInt;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void addCount(Integer i) {
        count = count + i;
    }

    public Double getPercent() {
        return percent;
    }

    public String getPercentStr() {
        if (percent == null) return null;
        return (percent * 100 / 1) / 100.0 + "%";
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getScoreInt() {
        return scoreInt;
    }

    public void setScoreInt(Integer scoreInt) {
        this.scoreInt = scoreInt;
    }

    public ScoreStats(String score, Integer count, Double percent, Integer scoreInt) {
        this.score = score;
        this.count = count;
        this.percent = percent;
        this.scoreInt = scoreInt;

    }
}