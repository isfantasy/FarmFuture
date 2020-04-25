package com.cn.farm;

import com.cn.farm.model.Farm;
import org.junit.Test;

/**
 * @ClassName ModelTest
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/4/24
 * @Version V1.0
 **/

public class ModelTest {

    @Test
    public void FarmTest(){
        Farm farm = new Farm("fantasy");
        System.out.println(farm.farmJson.get("animal").toString());
    }
}
