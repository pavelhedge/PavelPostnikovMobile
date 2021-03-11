package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.PageObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    IPageObject po;
    WebDriverWait wait;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    @Parameters({"execution", "platformName", "appType", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String execution,
                      String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId
    ) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(execution, platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
        wait = new WebDriverWait(getDriver(), 10);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String execution, String platformName, String deviceName, String udid, String browserName, String app, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        capabilities.setCapability("bundleId", bundleId);
        //if (platformName.equals("iOS")) capabilities.setCapability("automationName", "XCUITest");

        System.out.printf("Connecting to %s device ", platformName);
        if (deviceName != null && !deviceName.equals("")) System.out.printf("with name \'%s\'\n", deviceName);
        else if (udid != null && !udid.equals("")) System.out.printf("with id \'%s\'\n", udid);

        String url;
        if (execution.equals("local")) url = System.getProperty("appium.local");
        else if (execution.equals("cloud"))
        {
            String token = new TokenManager().getToken();
            url = System.getProperty("appium.cloud.host").
                    replaceFirst("(?<=://)",
                            String.format("%s:%s@",
                                    System.getProperty("appium.cloud.project"),
                                    token)
                    );
        }
        else throw new IllegalStateException("Cannot start appium session. Wrong testng parameter execution, must be local or cloud");

        System.out.println("Starting appium session at " + url);

        try {
            appiumDriver = new AppiumDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }
}
