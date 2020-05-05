package com.cn.farm.model;

import java.util.Date;

/**
 * @ClassName Animal
 * @Description: 动物类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Animal extends BaseItem implements AnimalAction{
    private String description;
    // 生长周期
    private Integer durationHour;
    // 幸福指数
    private Integer happinessIndex;
    // 健康指数
    private Integer healthIndex;
    // 状态
    private Integer status;
    private Date updateTime;
    private Date createTime;

    @Override
    public boolean feed(Feed feed) {
        return false;
    }

    @Override
    public boolean purchase() {
        return false;
    }

    @Override
    public boolean sell() {
        return false;
    }
}
