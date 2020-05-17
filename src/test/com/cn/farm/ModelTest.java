package com.cn.farm;

import com.cn.farm.model.Animal;
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
        Farm farm = new Farm();
        System.out.println(farm.toString());
    }

    @Test
    public void AnimalTest(){
        Animal animal = new Animal();
        animal.setName("fantasy");
        System.out.println(animal.toString());
    }
}
