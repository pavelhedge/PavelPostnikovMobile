package scenarios;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import setup.BaseTest;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class webMobileTests extends BaseTest {

    @DataProvider(name = "googleTestData")
    public Object[][] googleTestData() {
        return new Object[][] {
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

    @Test(groups = {"google"},
            description = "Make sure that Google can find EPAM",
            dataProvider = "googleTestData")
    public void googleTest(String query, String resultText) throws IllegalAccessException, NoSuchFieldException, InstantiationException {

        WebDriver driver = getDriver();
        driver.get("http://google.com"); // open IANA homepage
        driver.findElement(By.xpath("//input[@name=\"q\"]")).
                sendKeys(query, Keys.ENTER);

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 60).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        List<WebElement> searchResults = driver.findElements(By.xpath(
                String.format("//*[@id=\"rso\"]//*[contains(text(), '%s')]", resultText)));
        assertTrue(!searchResults.isEmpty());
    }
}
