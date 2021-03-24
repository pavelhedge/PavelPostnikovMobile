package scenarios;

import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.*;
import setup.BaseTest;
import setup.IPageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.testng.Assert.assertTrue;

public class nativeMobileTests extends BaseTest {

    @AfterMethod(alwaysRun = true)
    public void tearDownTest() {
        getDriver().resetApp();
    }

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("signInBtn").click();
        System.out.println("Simplest Android native test done");
    }

    public void assertAppPageTitle(String title) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InterruptedException {
        getWait().ignoring(StaleElementReferenceException.class).until(
                ExpectedConditions.visibilityOf(getPo().getWelement("title"))
        );
        assertTrue(getPo().getWelement("title").getText().contains(title), "App page title is not correct");
        System.out.printf("On a %s page\n", title);
    }

    @Parameters({"username","mail","password"})
    @Test(groups = {"native", "register"}, description = "Register Form Test")
    public void registerNativeTest(String username, String mail, String password) throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {

        IPageObject po = getPo();
        assertAppPageTitle("EPAM Test App");

        po.getWelement("regBtn").click();
        assertAppPageTitle("Registration");

        po.getWelement("regUsername").sendKeys(username);
        po.getWelement("regMail").sendKeys(mail);
        po.getWelement("regPassword").sendKeys(password);
        po.getWelement("regConfirmPassword").sendKeys(password);
        po.getWelement("regAgreeCheckbox").click();
        po.getWelement("regNewAccountBtn").click();
        assertAppPageTitle("EPAM Test App");

        po.getWelement("loginField").sendKeys(mail);
        po.getWelement("passwordField").sendKeys(password);
        po.getWelement("signInBtn").click();
        //assertAppTitle("BudgetActivity"); //It calls just "Budget" in iOS app
        assertAppPageTitle("Budget");
    }
}