package com.cn.farm.model;

import com.cn.farm.database.Database;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FarmItem
 * @Description: 农场所有辅助物品类
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class FarmItem {
    private List<Feed> feedList;
    private List<Muck> muckList;

    public FarmItem(String name){
        ArrayNode feedArray = Database.getFeedByName(name);
        ArrayNode muckArray = Database.getMuckByName(name);
        feedList = new ArrayList<>();
        muckList = new ArrayList<>();
        for (JsonNode feed:feedArray){
            Feed feed1 = new Feed();
            feed1.setName(feed.get("name").toString());
            feed1.setPurchasePrice(feed.get("purchasePrice").toString());
            feed1.setSellPrice(feed.get("sellPrice").toString());
            feed1.setHappinessEffect(Integer.parseInt(feed.get("happinessEffect").toString()));
            feed1.setHealthEffect(Integer.parseInt(feed.get("healthEffect").toString()));
            feed1.setType(Integer.parseInt(feed.get("type").toString()));
            feedList.add(feed1);
        }
        for (JsonNode muck:muckArray){
            Muck muck1 = new Muck();
            muck1.setName(muck.get("name").toString());
            muck1.setPurchasePrice(Integer.parseInt(muck.get("purchasePrice").toString()));
            muck1.setSellPrice(Integer.parseInt(muck.get("sellPrice").toString()));
            muck1.setType(Integer.parseInt(muck.get("type").toString()));
            muck1.setEffect(Integer.parseInt(muck.get("effect").toString()));
            muckList.add(muck1);
        }
    }

    public List<Feed> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<Feed> feedList) {
        this.feedList = feedList;
    }

    public List<Muck> getMuckList() {
        return muckList;
    }

    public void setMuckList(List<Muck> muckList) {
        this.muckList = muckList;
    }
}
