package com.cn.farm.model;

/**
 * @ClassName Muck
 * @Description: 肥料类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Muck extends BaseItem{

    private Integer effectHour;

    @Override
    public String toString() {
        return "Muck{" +
                "effectHour=" + effectHour +
                ", name='" + name + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellPrice=" + sellPrice +
                ", level=" + level +
                ", count=" + count +
                '}';
    }

    public Integer getEffectHour() {
        return effectHour;
    }

    public void setEffectHour(Integer effectHour) {
        this.effectHour = effectHour;
    }

}
