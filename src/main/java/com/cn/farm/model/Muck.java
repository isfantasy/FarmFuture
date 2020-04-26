package com.cn.farm.model;

import java.util.Date;

/**
 * @ClassName Muck
 * @Description: 肥料类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Muck extends BaseItem{

    private Date effect;
    private Integer type;

    public Date getEffect() {
        return effect;
    }

    public void setEffect(Date effect) {
        this.effect = effect;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
