package com.cn.farm.model;

import cn.hutool.core.date.DateUtil;
import com.cn.farm.database.Database;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

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
    private Integer duration;
    // 可用金额
    private Integer money;
    // 农民剩余操作
    private Integer farmerRemainderCount;
    private String lastLoginTime;
    private String createTime;

    List<Animal> animalList;
    List<Plant> plantList;
    List<Muck> muckList;
    List<Feed> feedList;

    public Farm() {
    }

    public Farm(boolean loadData) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode farmJson = Database.currentFarmData;
        this.name = farmJson.get("name").toString();
        this.type = Integer.parseInt(farmJson.get("type").toString());
        this.fenceDamaged = Integer.parseInt(farmJson.get("fenceDamaged").toString());
        this.duration = Integer.parseInt(farmJson.get("duration").toString());
        this.money = Integer.parseInt(farmJson.get("money").toString());
        this.farmerRemainderCount = Integer.parseInt(farmJson.get("farmerRemainderCount").toString());
        this.createTime = farmJson.get("duration").toString();

        try {
            // 初始化动物
            if (!farmJson.get("animalList").isNull()) {
                ArrayNode animalNode = (ArrayNode) farmJson.get("animalList");
                animalList = mapper.readValue(animalNode.toString(), new TypeReference<List<Animal>>() {
                });
            }else {
                animalList = new ArrayList<>();
            }

            // 初始化植物
            if (!farmJson.get("plantList").isNull()) {
                ArrayNode plantNode = (ArrayNode) farmJson.get("plantList");
                plantList = mapper.readValue(plantNode.toString(), new TypeReference<List<Plant>>() {
                });
            }else {
                plantList = new ArrayList<>();
            }

            // 初始化肥料
            if (!farmJson.get("muckList").isNull()) {
                ArrayNode muckNode = (ArrayNode) farmJson.get("muckList");
                muckList = mapper.readValue(muckNode.toString(), new TypeReference<List<Muck>>() {
                });
            }else {
                muckList = new ArrayList<>();
            }

            // 初始化饲料
            if (!farmJson.get("feedList").isNull()) {
                ArrayNode feedNode = (ArrayNode) farmJson.get("feedList");
                feedList = mapper.readValue(feedNode.toString(), new TypeReference<List<Feed>>() {
                });
            }else {
                feedList = new ArrayList<>();
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void createFarm() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        createTime = DateUtil.date().toString();
        lastLoginTime = createTime;
        animalList = new ArrayList<>();
        plantList = new ArrayList<>();
        muckList = Database.getGlobalMuck();
        feedList = Database.getGlobalFeed();
        String thisStr = mapper.writeValueAsString(this);
        Database.farmData.set(name, mapper.readTree(thisStr.replace("\\\"", "")));
        Database.updateData();
    }

    public void updateFarm() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String thisStr = mapper.writeValueAsString(this);
            Database.farmData.set(name.replace("\"", ""), mapper.readTree(thisStr.replace("\\\"", "")));
            Database.updateData();
            Database.setCurrentFarm(name.replace("\"", ""));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Farm{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", fenceDamaged=" + fenceDamaged +
                ", duration=" + duration +
                ", money=" + money +
                ", farmerRemainderCount=" + farmerRemainderCount +
                ", createTime=" + createTime +
                '}';
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }

    public List<Muck> getMuckList() {
        return muckList;
    }

    public void setMuckList(List<Muck> muckList) {
        this.muckList = muckList;
    }

    public List<Feed> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<Feed> feedList) {
        this.feedList = feedList;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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
