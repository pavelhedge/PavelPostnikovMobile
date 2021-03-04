package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.IPageObject;
import setup.PropertiesManager;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.*;

public class nativeMobileTests extends BaseTest {

    PropertiesManager properties;
    String mail;
    String password;
    String username;

    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        properties = new PropertiesManager("src/test/resources/user.properties");
        mail = properties.get("mail");
        username = properties.get("username");
        password = properties.get("password");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest() {
        getDriver().resetApp();
    }

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("signInBtn").click();
        System.out.println("Simplest Android native test done");

    }


    public void assertAppTitle(String title) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InterruptedException{
        getWait().until(ExpectedConditions.textToBePresentInElement(
                getPo().getWelement("title"), title));
        System.out.printf("On a %s page\n", title);
    }

    @Test(groups = {"native", "register"}, description = "Register Form Test")
    public void registerNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {

        IPageObject po = getPo();
        assertAppTitle("EPAM Test App");

        po.getWelement("regBtn").click();
        assertAppTitle("Registration");

        po.getWelement("regUsername").sendKeys(username);
        po.getWelement("regMail").sendKeys(mail);
        po.getWelement("regPassword").sendKeys(password);
        po.getWelement("regConfirmPassword").sendKeys(password);
        po.getWelement("regAgreeCheckbox").click();
        po.getWelement("regNewAccountBtn").click();
        assertAppTitle("EPAM Test App");

        po.getWelement("loginField").sendKeys(mail);
        po.getWelement("passwordField").sendKeys(password);
        po.getWelement("signInBtn").click();
        assertAppTitle("BudgetActivity");
    }
}