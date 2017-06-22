package net.kozon.selenium.example.test.framework.common.utils;

import java.security.InvalidParameterException;
import java.text.MessageFormat;

/**
 * Created by Dariusz Kozon on 22.06.2017.
 */
public class Configuration {

    public static String getProperty(String key) {
        final String property = System.getProperty(key);
        if(property == null) {
            throw new InvalidParameterException(MessageFormat.format("Missing value for key '{0}'!", key));
        }
        return property;
    }
}
