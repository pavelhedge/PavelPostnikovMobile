package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebPageObject {

    @FindBy(xpath = "//input[@name=\"q\"]")
    WebElement queryField;

    @FindBy(xpath = "//*[@id=\"rso\"]/div")
    List<WebElement> searchResults;

    @FindBy(xpath = "//*[@id=\"rso\"]//*[contains(text(), \"www\")]")
    List<WebElement> searchResultsUrls;


    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }
}
