package wearmedicinebasictest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class WaermedicineFirstTest {

    private WebDriver driver;
    private static final Logger LOG = Logger.getLogger("LOG");
    private static final String WEARMEDICINE_HER_PAGE = "https://wearmedicine.com/ona";

    private By promotionElement = By.cssSelector("h1.anim.animated.fadeInUp");

    @BeforeClass
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/driver_to_test/chromedriver.exe");
        driver = new ChromeDriver();
    }

    private void goToWearmedicinePage() {
        LOG.info("Open page: " + WEARMEDICINE_HER_PAGE);
        driver.manage().window().maximize();
        driver.get(WEARMEDICINE_HER_PAGE);
    }

    @Test
    public void wearMedicineFirstTest() {
        LOG.info("Open " + WEARMEDICINE_HER_PAGE);
        goToWearmedicinePage();
        waitUntilPageIsRendered();
        assertPromotionTextAfterOpeningPage("-25% na całą kolekcję !");
    }

    private void assertPromotionTextAfterOpeningPage(String expectedText) {
        LOG.info("Check whether text about promotion is displayed: " + expectedText);
        String actualText = getTextFromWebElement(promotionElement).replace("\n", " ");
        assertEquals(actualText, expectedText);
    }

    private String getTextFromWebElement(By element) {
        return driver.findElement(element).getText();
    }

    private void waitUntilPageIsRendered() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void quitBrowser() {
        driver.quit();
    }
}