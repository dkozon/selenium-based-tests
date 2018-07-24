package net.kozon.selenium.framework.tools.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Configuration dedicated to cover nullPointerExceptions for missing Property
 */
public class Configuration {

    private static final String FRAMEWORK_PROPERTIES = "src/main/resources/framework.properties";
    private static Logger logger = LoggerFactory.getLogger(Configuration.class);

    public static String getProperty(String key) {
        final String property = System.getProperty(key);
        if (property == null) {
            throw new InvalidParameterException(MessageFormat.format("Missing value for key '{0}'!", key));
        }
        return property;
    }

    public static String getPropertyFromFile(String key) {
        Properties properties = new Properties();
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(FRAMEWORK_PROPERTIES);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Error while loading properties file!", e);
        }

        return properties.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        System.setProperty(key, value);
    }
}
