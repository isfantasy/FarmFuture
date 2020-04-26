package com.cn.farm.model;

import com.cn.farm.database.Database;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Date;

/**
 * @ClassName Farm
 * @Description: 农场类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class Farm {
    private String name;
    // 农场类型
    private Integer type;
    // 是否损坏
    private Integer fenceDamaged;
    private Date duration;
    // 可用金额
    private Integer money;
    // 农民剩余操作
    private Integer farmerRemainderCount;
    private Date createTime;

    public Farm(String name) {
        ObjectNode farmJson = Database.getFarmByName(name);
        this.name = name;
        this.type = Integer.parseInt(farmJson.get("type").toString());
        this.fenceDamaged = Integer.parseInt(farmJson.get("fenceDamaged").toString());
        this.duration = farmJson.get("duration").toString();
        this.money = Integer.parseInt(farmJson.get("money").toString());
        this.farmerRemainderCount = Integer.parseInt(farmJson.get("farmerRemainderCount").toString());
        this.createTime = farmJson.get("duration").toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFenceDamaged() {
        return fenceDamaged;
    }

    public void setFenceDamaged(Integer fenceDamaged) {
        this.fenceDamaged = fenceDamaged;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getFarmerRemainderCount() {
        return farmerRemainderCount;
    }

    public void setFarmerRemainderCount(Integer farmerRemainderCount) {
        this.farmerRemainderCount = farmerRemainderCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
