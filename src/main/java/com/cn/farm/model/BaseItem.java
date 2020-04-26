package com.cn.farm.model;

/**
 * @ClassName Item
 * @Description: 基本辅助物品类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class BaseItem{
    // 名称
    private String name;
    // 购买价格
    private Integer purchasePrice;
    // 出售价格
    private Integer sellPrice;

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
}
