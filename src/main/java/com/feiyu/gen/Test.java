package com.feiyu.gen;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Test
{
    
    private Configuration cfg; // 模版配置对象
    
    public void init()
        throws Exception
    {
        // 初始化FreeMarker配置
        // 创建一个Configuration实例
        cfg = new Configuration(Configuration.VERSION_2_3_26);
        // 设置FreeMarker的模版文件夹位置
        String filePath = Test.class.getResource("/").getPath();
        cfg.setDirectoryForTemplateLoading(new File(filePath + "/com/feiyu/template"));
    }
    
    public void process() throws Exception
    {
        // 构造填充数据的Map
        Map map = new HashMap();
        map.put("packageName", "com.nsx.fee");
        map.put("className", "Test");
        // 创建模版对象
        Template t = cfg.getTemplate("daoTemplate.ftl");
        // 在模版上执行插值操作，并输出到制定的输出流中
        t.process(map, new OutputStreamWriter(System.out));
    }
    
    public static void main(String[] args)
        throws Exception
    {
        Test hf = new Test();
        hf.init();
        hf.process();
    }
}
