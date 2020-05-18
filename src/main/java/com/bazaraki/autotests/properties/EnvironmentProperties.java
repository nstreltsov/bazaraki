package com.bazaraki.autotests.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Nikolay Streltsov on 18.05.2020
 */
public class EnvironmentProperties {

    private final Properties properties = new Properties();

    private static EnvironmentProperties INSTANCE = null;

    private EnvironmentProperties(){
        try {
            properties.load(new FileInputStream(new File("./environment.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EnvironmentProperties getInstance() {
        if (INSTANCE == null){
            INSTANCE = new EnvironmentProperties();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }


}
