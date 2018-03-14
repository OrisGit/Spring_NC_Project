package com.nc.netcracker_project.desktop_rmi_client.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private final Logger LOG = Logger.getLogger(PropertyLoader.class);

    private static PropertyLoader propertyLoader = null;
    private Properties prop = null;

    private PropertyLoader(){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("src/main/resources/client.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (java.io.IOException e) {
            LOG.error("Не найден ресурс по пути: src/main/resources/client.properties");
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    LOG.error("Ошибка при закрытии ресурса.");
                }
            }
        }
    }

    public static String getProperty(String name){
       if(propertyLoader==null)
           propertyLoader = new PropertyLoader();
        return propertyLoader.prop.getProperty(name);
    }
}
