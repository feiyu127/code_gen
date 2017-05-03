package com.feiyu.gen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.feiyu.util.jdbcUtils;

public interface BaseParam
{
    
    /**
     * 
     * 获取基本的参数
     *
     * @return
     */
    static Map<String, Object> getBaseMap(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("author", "nanshouxiao");
        paramMap.put("packageName", "com.feiyu");
        paramMap.put("basePackageName", "com.base");
        
        return paramMap;
    }
    
    /**
     * 
     * 获取数据库字段类型对应java类型的map
     *
     * @return
     */
    static Map<String, String> getJdbcToJavaTypeMap(){
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("varchar", "String");
        paramMap.put("text", "String");
        paramMap.put("int", "Integer");
        paramMap. put("double", "Double");
        return paramMap;
    }
    
    Map<String, String> jdbcToJavaTypeMap = getJdbcToJavaTypeMap();
    /**
     * 
     * 获取组装好的参数
     *
     * @param tableInfo
     * @param columnList
     * @return
     */
    static Object initData(Map<String, String> tableInfo, List<Map<String, String>> columnList)
    {
        Map<String, Object> result = BaseParam.getBaseMap();
        String tableName = tableInfo.get("TABLE_NAME");
        result.put("tableComment", tableInfo.get("TABLE_COMMENT"));
        result.put("tableName", tableName);
        result.put("className", jdbcUtils.getHumName(tableName, true));
        
        List<Map<String, Object>> finalColumnList = columnList.stream().map(BaseParam::initColumnInfo).collect(Collectors.toList());
        result.put("columnList", finalColumnList);
        
        // 参数里添加主键信息
        columnList.stream().filter(column -> "PRI".equalsIgnoreCase(column.get("COLUMN_KEY"))).forEach(column -> {
            result.put("primaryColumnName", column.get("COLUMN_NAME"));
            result.put("primaryJavaName", jdbcUtils.getHumName(column.get("COLUMN_NAME")));
            result.put("primaryDataType", column.get("DATA_TYPE"));
            result.put("primaryJavaType", Optional.ofNullable(jdbcToJavaTypeMap.get(column.get("DATA_TYPE"))).orElse("Object"));
        });
        return result;
    }
    
    static Map<String, Object> initColumnInfo(Map<String, String> column){
        Map<String, Object> resultColumn = new HashMap<>();
        String columnName = column.get("COLUMN_NAME");
        String javaName = jdbcUtils.getHumName(columnName);

        resultColumn.put("columnKey", column.get("COLUMN_KEY"));
        resultColumn.put("columnName", columnName);
        resultColumn.put("javaName", javaName);
        resultColumn.put("columnComment", column.get("COLUMN_COMMENT"));
        String dataType = column.get("DATA_TYPE");
        resultColumn.put("javaType", Optional.ofNullable(jdbcToJavaTypeMap.get(dataType)).orElse("Object"));

        return resultColumn;
        
    }
}
