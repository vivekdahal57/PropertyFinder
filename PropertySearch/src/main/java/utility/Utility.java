/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alex
 */
public class Utility {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public int pageCalculator(int numerator, int denomenator) {
        int result = numerator / denomenator;
        if (numerator % denomenator == 0) {
            return result;
        }
        return (result + 1);
    }

    public int remainingPageCalculator(int numerator, int denomenator) {
        if (numerator % denomenator == 0) {
            return 25;
        }
        return numerator % denomenator;
    }

    public List<Map> getParametersFromPropertiesFile() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String filename = "dataDriven.properties";
            input = Utility.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                logger.info("Sorry, unable to find " + filename);
                return null;
            }
            //load a properties file from class path
            prop.load(input);
            //get the property value
            List<Map> list = new ArrayList<>();
            int i = 0;
            while (true) {
                Map<String, String> dataParameter = new HashMap<>();
                int blank = 0;
                for (Object key : prop.keySet()) {
                    if (prop.getProperty(key.toString()).split(",").length > i) {
                        dataParameter.put(key.toString(), prop.getProperty(key.toString()).split(",")[i]);
                    } else {
                        dataParameter.put(key.toString(), "");
                        blank++;
                    }
                }
                i++;
                if (blank != prop.keySet().size() && blank < prop.keySet().size()) {
                    list.add(dataParameter);
                } else {
                    break;
                }
            }
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
