package com.cn.farm.model;

import java.util.Date;

/**
 * @ClassName Animal
 * @Description: 动物类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Animal {
    private String name;
    private Integer purchasePrice;
    private Integer sellPrice;
    private String description;
    // 生长周期
    private Date duration;
    // 幸福指数
    private Integer happinessIndex;
    // 健康指数
    private Integer healthIndex;
    // 状态
    private Integer status;
    private Date updateTime;
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Integer getHappinessIndex() {
        return happinessIndex;
    }

    public void setHappinessIndex(Integer happinessIndex) {
        this.happinessIndex = happinessIndex;
    }

    public Integer getHealthIndex() {
        return healthIndex;
    }

    public void setHealthIndex(Integer healthIndex) {
        this.healthIndex = healthIndex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
