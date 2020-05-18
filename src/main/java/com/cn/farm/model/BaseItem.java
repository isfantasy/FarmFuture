package com.cn.farm.model;

/**
 * @ClassName Item
 * @Description: 基本辅助物品类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public abstract class BaseItem{
    // 名称
    public String name;
    // 购买价格
    public Integer purchasePrice;
    // 出售价格
    public Integer sellPrice;
    // 物品等级
    public Integer level;
    // 剩余数量
    public Integer count;
    // 简介
    private String description;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
