package com.cn.farm.database;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName Database
 * @Description: json数据文件操作类
 * @Author Fantasy
 * @Date 2020/4/24
 * @Version V1.0
 **/

public class Database {
    /**
     * 保存json文件所有数据.
     */
    public static ObjectNode dataJson;
    /**
     * 保存所有农场信息.
     */
    public static ObjectNode farmJson;
    private static File file;
    //  系统分隔符
    private static String separator = System.getProperty("File.separator");

    static {
        // 读取数据库文件
        file = new File(System.getProperty("user.dir") + separator + "database.json");
        //  转换数据库文件
        ObjectMapper mapper = new ObjectMapper();

        try {
            //  json转成Java可用json
            dataJson = (ObjectNode) mapper.readTree(file);
            farmJson = (ObjectNode) dataJson.get("farm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取名称为name的农场.
     *
     * @param name the name
     * @return the object node
     */
    public static ObjectNode getFarmByName(String name){

        return (ObjectNode) farmJson.get(name);
    }

    /**
     * 获取农场name的所有植物.
     *
     * @param name the name
     * @return the array node
     */
    public static ArrayNode getPlantByName(String name){
        return (ArrayNode) farmJson.get(name).get("plant");
    }

    /**
     * 获取农场name的所有动物.
     *
     * @param name the name
     * @return array node
     */
    public static ArrayNode getAnimalByName(String name){
        return (ArrayNode) farmJson.get(name).get("animal");
    }

    /**
     * 获取农场所有肥料.
     *
     * @param name the name
     * @return the array node
     */
    public static ArrayNode getMuckByName(String name){
        return (ArrayNode) farmJson.get(name).get("item").get("muck");
    }

    /**
     * 获取农场所有饲料.
     *
     * @param name the name
     * @return the array node
     */
    public static ArrayNode getFeedByName(String name){
        return (ArrayNode) farmJson.get(name).get("item").get("feed");
    }

    /**
     * 获取系统所有农场类型.
     *
     * @return the array node
     */
    public static ArrayNode getFarmType(){
        return (ArrayNode) dataJson.get("farmType");
    }

    public static void saveData() {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                    file, JsonEncoding.UTF8);
            mapper.writeTree(jsonGenerator, dataJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
