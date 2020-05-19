package com.cn.farm.tools;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @ClassName FormatTool
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/5/19
 * @Version V1.0
 **/

public class FormatTool {
    public static String dateBetweenParse(Date start, Date end) {
        String result = "";
        long temp;
        temp =  DateUtil.between(start, end, DateUnit.DAY);
        result += temp + "天";
        start = DateUtil.offset(start, DateField.DAY_OF_YEAR, (int) temp);

        temp =  DateUtil.between(start, end, DateUnit.HOUR);
        result += temp + "小时";
        start = DateUtil.offset(start, DateField.HOUR, (int) temp);

        temp =  DateUtil.between(start, end, DateUnit.MINUTE);
        result += temp + "分";
        start = DateUtil.offset(start, DateField.MINUTE, (int) temp);

        temp =  DateUtil.between(start, end, DateUnit.SECOND);
        result += temp + "秒";
        start = DateUtil.offset(start, DateField.SECOND, (int) temp);

        return result;
    }
}
