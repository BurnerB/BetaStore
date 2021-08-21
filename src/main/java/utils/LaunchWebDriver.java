package utils;


import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.naming.ConfigurationException;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LaunchWebDriver {

    public static Properties prop = new Properties();
    public static WebDriver driver;

    public static WebDriver launchWebDriver() {


        System.out.println("Launching Browser..........");
        String user_dir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", user_dir + "/Browsers/chromedriver_92");
        driver =  new ChromeDriver();
        return driver;
    }
    public static Properties loadProp() throws Exception {
        Properties prop = new Properties();
        FileInputStream fls = new FileInputStream("./src/test/resources/Objects/object.properties");
        prop.load(fls);

        return prop;
    }

    public static void byPassPrivateConnCheck(WebDriver obj) {
        obj.findElement(By.xpath("/html/body/div/div[2]/button[3]")).click();
        obj.findElement(By.xpath("/html/body/div/div[3]/p[2]/a")).click();
    }

    //
    public static String returnPropertyValue(String propertyName) {
        try {
            prop.load(new FileInputStream("./src/main/config/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }

    public static String returnPropertyXpathValues(String propertyName) {
        try {
            prop.load(new FileInputStream("./src/main/config/configFileWithXpaths.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }

    public static String setPropertyXpathValues(String propertyName, String propertyValue) {
        try {
            OutputStream outputStream = new FileOutputStream("./src/main/config/setRandomNames.properties");
            prop.setProperty(propertyName, propertyValue);
            prop.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setPropertyValues(String name, String value) throws ConfigurationException {

        // You have to create config.properties file under resources folder or anywhere you want :)
        // Here I'm updating file which is already exist under /Documents
//        PropertiesConfiguration config = new PropertiesConfiguration("./src/main/config/setRandomNames.properties");
//        config.setProperty(name, value);
//        config.save();

        System.out.println("Config Property Successfully Updated..");
    }

    public static String returnPropertyXpathTopOrgValues(String propertyName) {
        try {
            prop.load(new FileInputStream("./src/main/config/setRandomNames.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }

    public static String returnPropertyXpathLogsValues(String propertyName) {
        try {
            prop.load(new FileInputStream("./src/main/config/logsOnAllRunData.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }


    public static void setPropertyValuesLogs(String name, String value) throws ConfigurationException {

        // You have to create config.properties file under resources folder or anywhere you want :)
        // Here I'm updating file which is already exist under /Documents
//        PropertiesConfiguration config = new PropertiesConfiguration("./src/main/config/logsOnAllRunData.properties");
//        config.setProperty(name, value);
//        config.save();

        System.out.println("Config Property Successfully Updated..");
    }


    public static String returnBillManagerPropertyValue(String propertyName) {
        try {
            prop.load(new FileInputStream("./src/main/config/billManagerConfigs.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }

    public static String returnBillManagerSchoolsPropertyValue(String propertyName) {
        try {
            prop.load(new FileInputStream("./src/main/config/billManagerSchoolsConfigs.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }
}
