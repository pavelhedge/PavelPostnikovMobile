package scenarios;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import setup.BaseTest;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class webMobileTests extends BaseTest {

    By searchResultsLocator = By.xpath("//*[@id=\"rso\"]/div");
    By queryFieldLocator = By.xpath("//input[@name=\"q\"]");

    public void assertUrlFound(String expectedURL){
        List<WebElement> searchResults = getDriver().findElements(By.xpath(
                String.format("//*[@id=\"rso\"]//*[contains(text(), '%s')]", expectedURL)));
        assertTrue(!searchResults.isEmpty(), "Results don't contain expected URL: " + expectedURL);
    }

    @DataProvider(name = "googleTestData")
    public Object[][] googleTestData() {
        return new Object[][]{
                {"EPAM", "epam.com"}
        };
    }

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() throws InterruptedException {
        getDriver().get("http://iana.org"); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver()).getTitle().equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }

    @Test(groups = {"web", "google"},
            description = "Make sure that Google can find EPAM site",
            dataProvider = "googleTestData")
    public void googleTest(String query, String expectedURL) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        //getPo() == null. I can't understand why, but somewhere in between setPageObject() and this getPo() po becomes null
        WebDriver driver = getDriver();
        driver.get("http://google.com"); // open Google site
        driver.findElement(queryFieldLocator).sendKeys(query, Keys.ENTER);

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 60).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        //Assert search list is not empty
        assertFalse(driver.findElements(searchResultsLocator).isEmpty(), "Search results are empty");

        //Assert that relevant url is among the found urls
        assertUrlFound(expectedURL);
    }
}