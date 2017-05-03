package com.feiyu.gen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feiyu.util.FreeMarkerUtil;
import com.feiyu.util.jdbcUtils;

public class GenerateDAO
{
    public static void main(String[] args)
    {
        Map<String, String> tableInfo = jdbcUtils.getTableInfo("tickets", "t_sys_user");
        List<Map<String, String>> columnList = jdbcUtils.getTableColumnInfo("tickets", "t_sys_user");
        Object dataModel = initData(tableInfo, columnList);
        System.out.println(dataModel);
        FreeMarkerUtil.processConsole("daoTemplate.ftl", dataModel);
    }
    public static Object initData(Map<String, String> tableInfo, List<Map<String, String>> columnList)
    {
        Map<String, Object> result = new HashMap<>();
        result.put("entityComment", tableInfo.get("TABLE_COMMENT"));
        result.put("author", "nanshouxiao");
        result.put("packageName", "com.nsx");
        result.put("className", jdbcUtils.getHumName(tableInfo.get("TABLE_NAME"), true));
        return result;
    }
}
