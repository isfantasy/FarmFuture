package com.cn.farm.model;

import cn.hutool.core.date.DateUtil;

/**
 * @ClassName Animal
 * @Description: 动物类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Animal extends BaseItem implements AnimalAction{
    // 生长周期
    private Integer durationHour;
    // 幸福指数
    private Integer happinessIndex;
    // 健康指数
    private Integer healthIndex;
    // 状态
    private Integer status;
    private String updateTime;
    private String createTime;

    @Override
    public boolean feed(Feed feed) {
        return false;
    }

    @Override
    public boolean purchase() {
        Farm farm = new Farm(true);
        happinessIndex = 80;
        healthIndex = 80;
        createTime = DateUtil.date().toString();
        status = 0;
        updateTime = createTime;

        farm.setMoney(farm.getMoney() - purchasePrice);
        farm.getAnimalList().add(this);
        farm.updateFarm();
        return true;
    }

    @Override
    public boolean sell() {
        return false;
    }

    @Override
    public String toString() {
        return "Animal{" +
                ", durationHour=" + durationHour +
                ", happinessIndex=" + happinessIndex +
                ", healthIndex=" + healthIndex +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", name='" + name + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellPrice=" + sellPrice +
                ", level=" + level +
                ", count=" + count +
                '}';
    }

    public Integer getDurationHour() {
        return durationHour;
    }

    public void setDurationHour(Integer durationHour) {
        this.durationHour = durationHour;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
