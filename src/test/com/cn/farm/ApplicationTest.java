package com.cn.farm;

import com.cn.farm.controller.Application;
import org.junit.Test;

/**
 * @ClassName ApplicationTest
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/5/19
 * @Version V1.0
 **/

public class ApplicationTest {

    @Test
    public void printGlobalFarmType() {
        Application application = new Application();
        application.printGlobalFarmType();
    }

}
