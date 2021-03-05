package scenarios;

import org.testng.annotations.*;
import setup.BaseTest;
import setup.IPageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public void assertAppTitle(String title) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InterruptedException{
        getWait().until(ExpectedConditions.textToBePresentInElement(
                getPo().getWelement("title"), title));
        System.out.printf("On a %s page\n", title);
    }

    @Parameters({"username","mail","password"})
    @Test(groups = {"native", "register"}, description = "Register Form Test")
    public void registerNativeTest(String username, String mail, String password) throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {

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