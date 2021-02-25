package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;

import static org.testng.Assert.*;

public class nativeMobileTests extends BaseTest {

    String mail = "ph@mail.com";
    String password = "password1234";
    String username = "ph";


    public void assertTitle(String title) {
        String titleLocator = "//*[contains(@text,'%s')]";
        try {
            By titleBy = By.xpath(String.format(titleLocator, title));
            assertTrue(getDriver().findElement(titleBy).isDisplayed());
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
        assertTitle("EPAMTest App");
        getPo().getWelement("regBtn").click();
        assertTitle("Registration");
        getPo().getWelement("regUsername").sendKeys(username);
        getPo().getWelement("regMail").sendKeys(mail);
        getPo().getWelement("regPassword").sendKeys(password);
        getPo().getWelement("regConfirmPassword").sendKeys(password);
        getPo().getWelement("regAgreeCheckbox").click();
        getPo().getWelement("regNewAccountBtn").click();
        assertTitle("EPAM Test App");
        getPo().getWelement("loginField").sendKeys(mail);
        getPo().getWelement("passwordField").sendKeys(password);
        getPo().getWelement("signInBtn").click();
        assertTitle("BudgetActivity");
    }
}