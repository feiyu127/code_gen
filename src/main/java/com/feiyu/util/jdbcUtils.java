package com.feiyu.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class jdbcUtils
{
    private static Properties prop = new Properties();
    
    private static final String CONFIG_FILE = "config.properties";
    
    private static final String PROJECT_PATH = jdbcUtils.class.getResource("/").getPath();
    
    private static String DRIVER;
    
    private static String URL;
    
    private static String USERNAME;
    
    private static String PASSWORD;
    
    static
    {
         loadPropertiesFile();
    }
    
    public static void main(String[] args)
    {
        getTableColumnInfo("tickets", "t_sys_user").forEach(System.out::println);
        System.out.println(getTableInfo("tickets", "t_sys_user"));
    }
    
    private static void loadPropertiesFile()
    {
        try
        {
            
            prop.load(new FileReader(PROJECT_PATH + CONFIG_FILE));
            DRIVER = prop.getProperty("jdbc_driver");
            URL = prop.getProperty("jdbc_url");
            USERNAME = prop.getProperty("jdbc_user");
            PASSWORD = prop.getProperty("jdbc_password");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static Map<String, String> getTableInfo(String tableSchema, String tableName)
    {
        Connection connection = null;
        Map<String, String> tableInfo = null;
        try
        {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String tableInfoSql = "SELECT * FROM information_schema.tables where table_schema = ? AND table_name = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(tableInfoSql);
            prepareStatement.setString(1, tableSchema);
            prepareStatement.setString(2, tableName);
            ResultSet executeQuery = prepareStatement.executeQuery();
            ResultSetMetaData resultRowInfo = null;
            
            while (executeQuery.next())
            {
                resultRowInfo = executeQuery.getMetaData();
                int tableInfoNum = resultRowInfo.getColumnCount();
                tableInfo = new HashMap<>();
                for (int i = 0; i < tableInfoNum; i++)
                {
                    tableInfo.put(resultRowInfo.getColumnName(i + 1), executeQuery.getString(i + 1));
                }
            }
            return tableInfo;
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return tableInfo;
    }
    
    public static List<Map<String, String>> getTableColumnInfo(String tableSchema, String tableName)
    {
        Connection connection = null;
        List<Map<String, String>> columnList = new ArrayList<>();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String tableInfoSql = "SELECT * FROM information_schema.columns where table_schema = ? AND table_name = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(tableInfoSql);
            prepareStatement.setString(1, tableSchema);
            prepareStatement.setString(2, tableName);
            ResultSet executeQuery = prepareStatement.executeQuery();
            ResultSetMetaData resultRowInfo = null;
            Map<String, String> columnInfo = null;
            while (executeQuery.next())
            {
                resultRowInfo = executeQuery.getMetaData();
                int columnNum = resultRowInfo.getColumnCount();
                columnInfo = new HashMap<>();
                for (int i = 0; i < columnNum; i++)
                {
                    columnInfo.put(resultRowInfo.getColumnName(i + 1), executeQuery.getString(i + 1));
                }
                columnList.add(columnInfo);
            }
            return columnList;
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return columnList;
    }
    
    public static String getHumName(String databaseName, boolean isFirstCase)
    {
        char[] charArray = databaseName.toCharArray();
        boolean isNeedCase = isFirstCase;
        StringBuilder humName = new StringBuilder();
        for (int i = 0; i < charArray.length; i++)
        {
            char c = charArray[i];
            if (charArray[i] == '_')
            {
                isNeedCase = true;
                continue;
            }
            humName.append(isNeedCase && c >= 'a' ? (char)(c - 32) : c);
            isNeedCase = false;
        }
        return humName.toString();
    }
    
    public static String getHumName(String databaseName)
    {
        return getHumName(databaseName, false);
    }
}
