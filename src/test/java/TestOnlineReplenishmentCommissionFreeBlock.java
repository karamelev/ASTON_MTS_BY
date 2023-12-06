import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestOnlineReplenishmentCommissionFreeBlock {

     private WebDriver driver;
     private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = Driver.getDriver();
        driver.get("https://www.mts.by/");
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        Driver.quitDriver();
    }

    @Test
    public void testTitleBlock() {
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(.,'Онлайн пополнение без комиссии')]"));
        String textOfBlock = blockTitle.getText();
        Assertions.assertEquals("Онлайн пополнение\nбез комиссии",textOfBlock);
    }

    @Test
    public void testLogotype() {
        List <WebElement> logotypes = driver.findElements(By.cssSelector(".pay__partners ul"));
        Assertions.assertTrue(!logotypes.isEmpty(), "Элементы не найдены на странице");

    }

    @Test
    public void testMoreInfoLinkWorks() {
        driver.findElement(By.xpath("//a[contains(.,'Подробнее о сервисе')]")).click();
        WebElement breadcrumbs = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@itemprop='name'][contains(.,'Порядок оплаты')]")));
        Assertions.assertNotNull(breadcrumbs);
    }

    @Test
    public void testForm() {
        WebElement phone = driver.findElement(By.id("connection-phone"));
        phone.clear();
        phone.sendKeys("297777777");
        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        sumInput.sendKeys("10");
        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("karamelev@tut.by");

        WebElement cookieClose = driver.findElement(By.xpath("//button[@class='cookie__close']"));
        if (cookieClose.isDisplayed()) {
            cookieClose.click();
        }

        driver.findElement(By.xpath("//form[@class='pay-form opened']/button[contains(.,'Продолжить')]")).click();

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/iframe")));
        Assertions.assertTrue(driver.findElement(By.xpath("//div/iframe")).isDisplayed());
    }
}
