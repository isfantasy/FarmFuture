package com.cn.farm.model;

public interface PlantAction extends BaseAction {
    // 浇水
    public boolean watering();
    // 施肥
    public boolean fertilize(Muck muck);
}