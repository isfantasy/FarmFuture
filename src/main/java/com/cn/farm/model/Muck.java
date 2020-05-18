package com.cn.farm.model;

import java.util.List;

/**
 * @ClassName Muck
 * @Description: 肥料类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Muck extends BaseItem implements BaseAction{

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

    @Override
    public boolean purchase() {
        Farm farm = new Farm(true);
        farm.setMoney(farm.getMoney() - purchasePrice);
        List<Muck> muckList = farm.getMuckList();
        for (Muck muck: muckList){
            if (muck.getLevel().equals(level)){
                muck.setCount(muck.getCount() + 1);
                break;
            }
        }
        farm.updateFarm();
        return true;
    }

    @Override
    public boolean sell() {
        return false;
    }
}
