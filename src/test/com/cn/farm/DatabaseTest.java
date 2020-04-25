package com.cn.farm;

import com.cn.farm.database.Database;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.Test;

/**
 * @ClassName DatabaseTest
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/4/25
 * @Version V1.0
 **/

public class DatabaseTest {

    @Test
    public void animalTest(){
        ArrayNode animals = Database.getAnimal();
        for (JsonNode node:animals){
            System.out.println(node.toString());
        }
        System.out.println(animals.toString());
    }
}
