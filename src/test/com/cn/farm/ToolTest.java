package com.cn.farm;

import cn.hutool.core.date.DateUtil;
import com.cn.farm.tools.FormatTool;
import org.junit.Test;

import java.util.Date;

/**
 * @ClassName ToolTest
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/5/19
 * @Version V1.0
 **/

public class ToolTest {

    @Test
    public void dateBetweenParseTest(){
        Date start = DateUtil.parse("2020-05-16 00:00:00");
        System.out.println(FormatTool.dateBetweenParse(start, DateUtil.date()));
    }
}
