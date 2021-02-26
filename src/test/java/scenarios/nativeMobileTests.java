package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.IPageObject;
import setup.PropertiesManager;

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

    public void assertTitle(String title) {
        String titleLocator = "//*[contains(@text,'%s')]";
        try {
            By titleBy = By.xpath(String.format(titleLocator, title));
            assertTrue(getDriver().findElement(titleBy).isDisplayed());
            System.out.printf("On a %s page\n", title);
        } catch (NoSuchElementException e) {
            Assert.fail("Wrong page title");
        }

        /* More good-looking version, throws StaleElementException
        @AndroidFindBy(xpath = "//*[contains(@resource-id,'action_bar')]/android.widget.TextView")
        WebElement pageTitle;
        String expectedTitle = getPo().getWelement("pageTitle").getText();
        assertEquals(expectedTitle, title);
        */
    }

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("signInBtn").click();
        System.out.println("Simplest Android native test done");

    }

    @Test(groups = {"native", "register"}, description = "Register Form Test")
    public void registerNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {

        IPageObject po = getPo();
        po.getWelement("regBtn").click();
        assertTitle("Registration");
        po.getWelement("regUsername").sendKeys(username);
        po.getWelement("regMail").sendKeys(mail);
        po.getWelement("regPassword").sendKeys(password);
        po.getWelement("regConfirmPassword").sendKeys(password);
        po.getWelement("regAgreeCheckbox").click();
        po.getWelement("regNewAccountBtn").click();

        po.getWelement("loginField").sendKeys(mail);
        po.getWelement("passwordField").sendKeys(password);
        po.getWelement("signInBtn").click();
        assertTitle("BudgetActivity");
    }
}