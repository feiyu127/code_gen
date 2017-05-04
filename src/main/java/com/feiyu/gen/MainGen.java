package com.feiyu.gen;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.feiyu.util.FreeMarkerUtil;
import com.feiyu.util.jdbcUtils;

public class MainGen
{
    private static final String daoTemplate = "daoTemplate.ftl";
    
    private static final String beanTemplate = "beanTemplate.ftl";
    
    private static final String xmlTemplate = "xmlTemplate.ftl";
    
    private static final String javaFileDir = "E:/workspace/tickets/src/main/java/";
    
    private static final String xmlFileDir = "E:/workspace/tickets/src/main/resources/mapper/";
    
    public static void main(String[] args)
    {
        String tableSchema = "tickets";
        String tableName = "t_sys_role";
         Map<String, String> tableInfo = jdbcUtils.getTableInfo(tableSchema, tableName);
         List<Map<String, String>> columnList = jdbcUtils.getTableColumnInfo(tableSchema, tableName);
         Map<String, Object> dataModel = BaseParam.initData(tableInfo, columnList);
         generateFile("bean", dataModel);
         generateFile("dao", dataModel);
         generateFile("xml", dataModel);

    }
    
    public static void generateFile(String type, Map<String, Object> dataModel)
    {
        String templateName = null;
        String filePath = null;
        String beanClassName = (String)dataModel.get("className");
        String packageName = (String)dataModel.get("packageName");
        String packagePath = packageName.replace(".", File.separator) + "/";
        String xmlPackagePath = packageName.replace("com.tickets.", "").replace(".", File.separator) + "/";
        switch (type)
        {
            case "bean":
                templateName = beanTemplate;
                filePath = javaFileDir + packagePath + "bean/" + beanClassName + ".java";
                break;
            case "dao":
                filePath = javaFileDir + packagePath + "dao/" + beanClassName + "Mapper.java";
                templateName = daoTemplate;
                break;
            case "xml":
                filePath = xmlFileDir + xmlPackagePath + beanClassName.substring(0, 1).toLowerCase() + beanClassName.substring(1) + "Mapper.xml";
                templateName = xmlTemplate;
                break;
            default:
                break;
        }
        FreeMarkerUtil.processFile(templateName, dataModel, filePath);
        System.out.println("成功创建文件:" + filePath);
    }
}
