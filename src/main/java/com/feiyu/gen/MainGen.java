package com.feiyu.gen;

import java.util.List;
import java.util.Map;

import com.feiyu.util.FreeMarkerUtil;
import com.feiyu.util.jdbcUtils;

public class MainGen
{
    private static final String daoTemplate = "daoTemplate.ftl";
    
    private static final String beanTemplate = "beanTemplate.ftl";
    
    private static final String xmlTemplate = "xmlTemplate.ftl";
    
    public static void main(String[] args)
    {
        Map<String, String> tableInfo = jdbcUtils.getTableInfo("tickets", "t_sys_user");
        List<Map<String, String>> columnList = jdbcUtils.getTableColumnInfo("tickets", "t_sys_user");
        Object dataModel = BaseParam.initData(tableInfo, columnList);
        FreeMarkerUtil.processConsole(daoTemplate, dataModel);
        FreeMarkerUtil.processConsole(beanTemplate, dataModel);
        FreeMarkerUtil.processConsole(xmlTemplate, dataModel);
    }
}
