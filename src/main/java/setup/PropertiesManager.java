package setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private static Properties properties;

    public PropertiesManager(String path)  {
         File file = new File(path);
         FileInputStream fileInput = null;
         try {
             fileInput = new FileInputStream(file);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         properties = new Properties();
         //load properties file
         try {
             properties.load(fileInput);
             fileInput.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

        public String get(String propName){
            return properties.getProperty(propName);
        }
    }
