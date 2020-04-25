package com.cn.farm.model;

import com.cn.farm.database.Database;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @ClassName Farm
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/4/24
 * @Version V1.0
 **/

public class Farm {
    public static String name;
    public static ObjectNode farmJson;

    public Farm(String name) {
        Farm.name = name;
        farmJson = Database.getFarmByName(name);
        FarmType.farmTypeArray = Database.getFarmType();
    }

    public boolean updateFarm() {
        Database.dataJson.set(farmJson.get("name").toString(), farmJson);
        return true;
    }
}
