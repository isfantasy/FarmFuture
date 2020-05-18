package com.cn.farm.model;

import cn.hutool.core.date.DateUtil;
import com.cn.farm.database.Database;

/**
 * @ClassName: Plant
 * @Description: 植物类
 * @Author: Fantasy
 * @Version: v0.0.1
 * @Date: 2020/4/25
 **/
public class Plant extends BaseItem implements PlantAction {
    private Integer durationHour;
    private Integer adjHour;
    private Integer status;
    private String updateTime;
    private String lastWatering;
    private String createTime;

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
        Farm farm = new Farm(true);
        createTime = DateUtil.date().toString();
        status = 0;
        adjHour = 0;
        updateTime = createTime;

        farm.setMoney(farm.getMoney() - purchasePrice);
        farm.getPlantList().add(this);
        farm.updateFarm();
        return true;
    }

    @Override
    public boolean sell() {
        return false;
    }

    @Override
    public String toString() {
        return "Plant{" +
                ", durationHour=" + durationHour +
                ", adjHour=" + adjHour +
                ", status=" + status +
                ", updateTime='" + updateTime + '\'' +
                ", lastWatering='" + lastWatering + '\'' +
                ", createTime='" + createTime + '\'' +
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

    public Integer getAdjHour() {
        return adjHour;
    }

    public void setAdjHour(Integer adjHour) {
        this.adjHour = adjHour;
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

    public String getLastWatering() {
        return lastWatering;
    }

    public void setLastWatering(String lastWatering) {
        this.lastWatering = lastWatering;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
