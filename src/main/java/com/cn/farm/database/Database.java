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
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/4/24
 * @Version V1.0
 **/

public class Database {
    public static ObjectNode dataJson;
    public static ObjectNode farmJson;
    private static File file;
    private static String separator = System.getProperty("file.separator");

    static {
        file = new File(System.getProperty("user.dir") + separator + "database.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            dataJson = (ObjectNode) mapper.readTree(file);
            farmJson = (ObjectNode) dataJson.get("farm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayNode getFarmType(){
        return (ArrayNode) dataJson.get("farmType");
    }

    public static ArrayNode getAnimal(){
        return (ArrayNode) dataJson.get("animal");
    }

    public static ArrayNode getPlant(){
        return (ArrayNode) dataJson.get("plant");
    }

    public static ArrayNode getItemForMuck(){
        return (ArrayNode) dataJson.get("item").get("muck");
    }

    public static ArrayNode getItemForFeed(){
        return (ArrayNode) dataJson.get("item").get("feed");
    }

    public static ObjectNode getFarmByName(String name){
        return (ObjectNode) farmJson.get(name);
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
