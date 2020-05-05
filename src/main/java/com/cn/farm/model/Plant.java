package com.cn.farm.model;

import com.cn.farm.database.Database;

import java.util.Date;

/**
 * @ClassName: Plant
 * @Description: 植物类
 * @Author: Fantasy
 * @Version: v0.0.1
 * @Date: 2020/4/25
 **/
public class Plant extends BaseItem implements PlantAction {
    private String description;
    private Integer durationHour;
    private Integer adjHour;
    private Integer status;
    private Date updateTime;
    private Date lastWatering;
    private Date createTime;

    @Override
    public boolean watering() {
        adjHour += (Integer) Database.getGlobalParam("wateringHour");
        return true;
    }

    @Override
    public boolean fertilize(Muck muck) {
        if (muck.getCount() < 1) {
            return false;
        }
        adjHour += muck.getEffectHour();
        muck.setCount(muck.getCount());
        return true;
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
