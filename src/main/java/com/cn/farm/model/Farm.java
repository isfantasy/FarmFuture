package com.cn.farm.model;

import com.cn.farm.database.Database;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
    private String createTime;

    List<Animal> animalList;
    List<Plant> plantList;
    List<Muck> muckList;
    List<Feed> feedList;

    public Farm(){}

    public Farm(boolean loadData){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode farmJson = Database.currentFarmData;
        this.name = farmJson.get("name").toString();
        this.type = Integer.parseInt(farmJson.get("type").toString());
        this.fenceDamaged = Integer.parseInt(farmJson.get("fenceDamaged").toString());
        this.duration = Integer.parseInt(farmJson.get("duration").toString());
        this.money = Integer.parseInt(farmJson.get("money").toString());
        this.farmerRemainderCount = Integer.parseInt(farmJson.get("farmerRemainderCount").toString());
        this.createTime = farmJson.get("duration").toString() ;

        // 初始化动物
        ArrayNode animalNode = (ArrayNode) farmJson.get("animal");
        animalList = new ArrayList<>();
        for (JsonNode animalJson:animalNode){
            Animal animal = null;
            try {
                animal = mapper.readValue(animalJson.toString(), Animal.class);
                animalList.add(animal);
            } catch (JsonProcessingException e) {
                System.err.println("转化错误");
                e.printStackTrace();
            }
        }

        // 初始化植物
        ArrayNode plantNode = (ArrayNode) farmJson.get("plant");
        plantList = new ArrayList<>();
        for (JsonNode plantJson:plantNode){
            Plant plant = null;
            try {
                plant = mapper.readValue(plantJson.toString(), Plant.class);
                plantList.add(plant);
            } catch (JsonProcessingException e) {
                System.err.println("转化错误");
                e.printStackTrace();
            }
        }

        // 初始化肥料
        ArrayNode muckNode = (ArrayNode) farmJson.get("item").get("muck");
        muckList = new ArrayList<>();
        for (JsonNode muckJson:muckNode){
            Muck muck = null;
            try {
                muck = mapper.readValue(muckJson.toString(), Muck.class);
                muckList.add(muck);
            } catch (JsonProcessingException e) {
                System.err.println("转化错误");
                e.printStackTrace();
            }
        }

        // 初始化饲料
        ArrayNode feedNode = (ArrayNode) farmJson.get("item").get("feed");
        feedList = new ArrayList<>();
        for (JsonNode feedJson:feedNode){
            Feed feed = null;
            try {
                feed = mapper.readValue(feedJson.toString(), Feed.class);
                feedList.add(feed);
            } catch (JsonProcessingException e) {
                System.err.println("转化错误");
                e.printStackTrace();
            }
        }

    }

    public boolean createFarm() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 农场节点
        ObjectNode farmNode = (ObjectNode) mapper.readTree(mapper.writeValueAsString(this));
        // 动物节点
        ArrayNode animalNode = mapper.createArrayNode();
        farmNode.set("animal", animalNode);
        // 植物节点
        ArrayNode plantNode = mapper.createArrayNode();
        farmNode.set("plant", plantNode);
        // 辅助物品节点
        ObjectNode itemNode = mapper.createObjectNode();
        // 肥料节点
        ArrayNode muckNode = mapper.createArrayNode();
        // 饲料节点
        ArrayNode feedNode = mapper.createArrayNode();
        itemNode.set("muck", muckNode);
        itemNode.set("feed", feedNode);
        farmNode.set("animal", itemNode);
        System.out.println(farmNode.toString());
        Database.farmData.set(name, farmNode);
        Database.updateData();
        return true;
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
