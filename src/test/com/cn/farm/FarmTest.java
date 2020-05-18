package com.cn.farm;

import com.cn.farm.database.Database;
import com.cn.farm.model.Farm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @ClassName FarmTest
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/5/18
 * @Version V1.0
 **/

public class FarmTest {

    @Test
    public void autoFillJsonToFarm() throws JsonProcessingException {
        Database.setCurrentFarm("test");
        ObjectMapper mapper = new ObjectMapper();
        Farm farm = new Farm(true);
        System.out.println(mapper.writeValueAsString(farm));
    }

    @Test
    public void farmToJson() throws JsonProcessingException {
        Database.setCurrentFarm("test");
        ObjectMapper mapper = new ObjectMapper();
        Farm farm = new Farm(true);
        //farm.updateFarm();
        System.out.println(farm.getName());
        String farmStr = mapper.writeValueAsString(farm);
        System.out.println(farmStr);
        System.out.println(farmStr.replace("\\\"", ""));
        System.out.println(mapper.readTree(farmStr.replace("\\\"", "")));

    }
}
