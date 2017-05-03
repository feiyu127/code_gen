package com.feiyu.gen;

import java.util.HashMap;
import java.util.Map;

public interface BaseParam
{
    static Map<String, Object> getBaseMap(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("author", "nanshouxiao");
        paramMap.put("packageName", "com.feiyu");
        paramMap.put("basePackageName", "com.base");
        
        return paramMap;
    }
}
