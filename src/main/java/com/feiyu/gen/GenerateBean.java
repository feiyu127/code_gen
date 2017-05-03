package com.feiyu.gen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.feiyu.util.FreeMarkerUtil;
import com.feiyu.util.jdbcUtils;

public class GenerateBean
{
    public static void main(String[] args)
    {
        Map<String, String> tableInfo = jdbcUtils.getTableInfo("tickets", "t_sys_user");
        List<Map<String, String>> columnList = jdbcUtils.getTableColumnInfo("tickets", "t_sys_user");
        Object dataModel = initData(tableInfo, columnList);
        System.out.println(dataModel);
        FreeMarkerUtil.processConsole("beanTemplate.ftl", dataModel);
    }
    static Map<String, String> map = new HashMap<String, String>(){{
        put("varchar", "String");
        put("text", "String");
        put("int", "Integer");
        put("double", "Double");
    }};
    public static Object initData(Map<String, String> tableInfo, List<Map<String, String>> columnList)
    {
        Map<String, Object> result = BaseParam.getBaseMap();
        result.put("entityComment", tableInfo.get("TABLE_COMMENT"));
        result.put("className", jdbcUtils.getHumName(tableInfo.get("TABLE_NAME"), true));
        List<Map<String, Object>> finalColumnList = columnList.stream().map(GenerateBean::initColumnInfo).collect(Collectors.toList());
        result.put("columnList", finalColumnList);
        return result;
    }
    
    private static Map<String, Object> initColumnInfo(Map<String, String> column){
        Map<String, Object> resultColumn = new HashMap<>();
        String columnName = column.get("COLUMN_NAME");
        String javaName = jdbcUtils.getHumName(columnName);
        resultColumn.put("columnName", columnName);
        resultColumn.put("javaName", javaName);
        resultColumn.put("columnComment", column.get("COLUMN_COMMENT"));
        String dataType = column.get("DATA_TYPE");
        resultColumn.put("javaType", Optional.ofNullable(map.get(dataType)).orElse("Object"));
        
        return resultColumn;
        
    }
}
