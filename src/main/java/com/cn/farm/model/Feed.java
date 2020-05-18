package com.cn.farm.model;

import java.util.List;

/**
 * @ClassName Feed
 * @Description: 饲料类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Feed extends BaseItem implements BaseAction{
    // 增加健康值
    private Integer healthEffect;
    // 增加幸福值
    private Integer happinessEffect;

    public Integer getHealthEffect() {
        return healthEffect;
    }

    public void setHealthEffect(Integer healthEffect) {
        this.healthEffect = healthEffect;
    }

    public Integer getHappinessEffect() {
        return happinessEffect;
    }

    public void setHappinessEffect(Integer happinessEffect) {
        this.happinessEffect = happinessEffect;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "healthEffect=" + healthEffect +
                ", happinessEffect=" + happinessEffect +
                ", name='" + name + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellPrice=" + sellPrice +
                ", level=" + level +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean purchase() {
        Farm farm = new Farm(true);
        farm.setMoney(farm.getMoney() - purchasePrice);
        List<Feed> feedList = farm.getFeedList();
        for (Feed feed: feedList){
            if (feed.getLevel().equals(level)){
                feed.setCount(feed.getCount() + 1);
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
