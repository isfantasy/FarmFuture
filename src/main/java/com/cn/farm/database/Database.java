package com.cn.farm.database;

import cn.hutool.core.util.ObjectUtil;
import com.cn.farm.model.Animal;
import com.cn.farm.model.Feed;
import com.cn.farm.model.Muck;
import com.cn.farm.model.Plant;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Database.
 *
 * @ClassName Database
 * @Description: json数据文件操作类
 * @Author Fantasy
 * @Date 2020 /4/24
 * @Version V1.0
 */
public class Database {
    /**
     * 保存json文件所有数据.
     */
    public static ObjectNode dataJson;
    /**
     * 保存所有农场信息.
     */
    public static ObjectNode farmData;
    public static ObjectNode currentFarmData;
    private static File file;
    //  系统分隔符
    private static String separator;

    static {
        separator = System.getProperty("file.separator");
        // 读取数据库文件
        file = new File(System.getProperty("user.dir") + separator + "database.json");
        //  转换数据库文件
        ObjectMapper mapper = new ObjectMapper();

        try {
            //  json转成Java可用json
            dataJson = (ObjectNode) mapper.readTree(file);
            farmData = (ObjectNode) dataJson.get("farm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean setCurrentFarm(String name) {
        currentFarmData = (ObjectNode) farmData.get(name);
        return ObjectUtil.isNotEmpty(currentFarmData);
    }

    /**
     * 获取名称为name的农场.
     *
     * @param name the name
     * @return the object node
     */
    public static ObjectNode getFarmByName(String name) {

        return (ObjectNode) farmData.get(name);
    }

    /**
     * 获取农场name的所有植物.
     *
     * @param name the name
     * @return the array node
     */
    public static ArrayNode getPlantByName(String name) {
        return (ArrayNode) farmData.get(name).get("plant");
    }

    /**
     * 获取农场name的所有动物.
     *
     * @param name the name
     * @return array node
     */
    public static ArrayNode getAnimalByName(String name) {
        return (ArrayNode) farmData.get(name).get("animal");
    }

    /**
     * 获取农场所有肥料.
     *
     * @param name the name
     * @return the array node
     */
    public static ArrayNode getMuckByName(String name) {
        return (ArrayNode) farmData.get(name).get("item").get("muck");
    }

    /**
     * 获取农场所有饲料.
     *
     * @param name the name
     * @return the array node
     */
    public static ArrayNode getFeedByName(String name) {
        return (ArrayNode) farmData.get(name).get("item").get("feed");
    }

    /**
     * 获取系统所有农场类型.
     *
     * @return the array node
     */
    public static ArrayNode getFarmType() {
        return (ArrayNode) dataJson.get("farmType");
    }


    /**
     * 获取全局变量.
     *
     * @param param 变量名
     * @return the object
     */
    public static Object getGlobalParam(String param) {
        return dataJson.get(param);
    }

    public static List<Animal> getGlobalAnimal() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode animalNode = (ArrayNode) Database.getGlobalParam("animal");
        List<Animal> animalList = null;
        try {
            animalList = mapper.readValue(animalNode.toString(), new TypeReference<List<Animal>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("jsonNode to list error");
            e.printStackTrace();
        }
        return animalList;
    }

    public static List<Plant> getGlobalPlant() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode plantNode = (ArrayNode) Database.getGlobalParam("plant");
        List<Plant> plantList = null;
        try {
            plantList = mapper.readValue(plantNode.toString(), new TypeReference<List<Plant>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("jsonNode to list error");
            e.printStackTrace();
        }
        return plantList;
    }

    public static List<Muck> getGlobalMuck() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode muckNode = (ArrayNode) ((ObjectNode) Database.getGlobalParam("item")).get("muck");
        List<Muck> muckList = null;
        try {
            muckList = mapper.readValue(muckNode.toString(), new TypeReference<List<Muck>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("jsonNode to list error");
            e.printStackTrace();
        }
        return muckList;
    }

    public static List<Feed> getGlobalFeed() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode feedNode = (ArrayNode) ((ObjectNode) Database.getGlobalParam("item")).get("feed");
        List<Feed> feedList = null;
        try {
            feedList = mapper.readValue(feedNode.toString(), new TypeReference<List<Feed>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("jsonNode to list error");
            e.printStackTrace();
        }
        return feedList;
    }

    /**
     * Save data.
     */
    public static void updateData() {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();
        dataJson.set("farm", farmData);
        try {
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                    file, JsonEncoding.UTF8);
            mapper.writeTree(jsonGenerator, dataJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
