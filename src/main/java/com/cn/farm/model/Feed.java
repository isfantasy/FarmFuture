package com.cn.farm.model;

/**
 * @ClassName Feed
 * @Description: 饲料类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Feed extends BaseItem{
    // 增加健康值
    private Integer healthEffect;
    // 增加幸福值
    private Integer happinessEffect;
    private Integer type;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
