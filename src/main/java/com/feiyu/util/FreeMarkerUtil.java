package com.feiyu.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil
{
    private static final String TEMPLATE_DIR_PATH = FreeMarkerUtil.class.getResource("/").getPath() + "/com/feiyu/template";
    private static Configuration cfg; // 模版配置对象
    
    static{
        init();
    }
    
    public static void init()
    {
        cfg = new Configuration(Configuration.VERSION_2_3_26);
        try
        {
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR_PATH));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void processConsole(String templateFileName, Object dataModel)
    {
        try
        {
            Template t = cfg.getTemplate(templateFileName);
            t.process(dataModel, new OutputStreamWriter(System.out));
        }
        catch (TemplateException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
