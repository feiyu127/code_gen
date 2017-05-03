package com.feiyu.gen;

import java.util.List;
import java.util.Map;

import com.feiyu.util.FreeMarkerUtil;
import com.feiyu.util.jdbcUtils;

public class GenerateBean
{
    public static void main(String[] args)
    {
        Map<String, String> tableInfo = jdbcUtils.getTableInfo("tickets", "t_sys_user");
        List<Map<String, String>> columnList = jdbcUtils.getTableColumnInfo("tickets", "t_sys_user");
        Object dataModel = BaseParam.initData(tableInfo, columnList);
        System.out.println(dataModel);
        FreeMarkerUtil.processConsole("beanTemplate.ftl", dataModel);
    }
}
