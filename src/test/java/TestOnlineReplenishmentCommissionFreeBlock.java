import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TestOnlineReplenishmentCommissionFreeBlock {

     private WebDriver driver;
     private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = Driver.getDriver();
        driver.get("https://www.mts.by/");
    }


//    @AfterEach
//    public void tearDown() {
//        Driver.quitDriver();
//    }



    private final String title = "//h2[contains(.,'Онлайн пополнение без комиссии')]";

    private final String logotypeVisa = "//div [@class='pay__partners']//img[@alt='Visa']";
    private final String logotypeVerifiedByVisa = "//div [@class='pay__partners']//img[@alt='Verified By Visa']";
    private final String logotypeMasterCard = "//div [@class='pay__partners']//img[@alt='MasterCard']";
    private final String logotypeMasterCardSecureCode = "//div [@class='pay__partners']//img[@alt='MasterCard Secure Code']";
    private final String logotypeBelcart = "//div [@class='pay__partners']//img[@alt='Белкарт']";

    private final String linkServes = "//a[contains(.,'Подробнее о сервисе')]";
    private final String phoneConnection = "#connection-phone";
    private final String sumInput = "#connection-sum";
    private final String email = "#connection-email";

    private final String submitButton = "//button[@type='submit']";

    @Test
        public void testTitleBlock () {
            WebElement blockTitle = driver.findElement(By.xpath(title));
            String textOfBlock = blockTitle.getText();
            Assertions.assertEquals("Онлайн пополнение\nбез комиссии",textOfBlock);
        }

    @Test
    public void testLogotype () {
        List <WebElement> logotypes = driver.findElements(By.cssSelector(".pay__partners ul"));
        Assertions.assertTrue(logotypes.size() > 0, "Элементы не найдены на странице");

    }

    @Test
    public void testMoreInfoLinkWorks () {
        driver.findElement(By.xpath("//a[contains(.,'Подробнее о сервисе')]")).click();
//        wait.until(ExpectedCondition.)

//        WebElement breadcrumbs = wait.until(ExpectedCondition("//span[@itemprop='name'][contains(.,'Порядок оплаты')]"));
//        breadcrumbs.getText();
//        Assertions.assertEquals("Порядок оплаты и безопасность интернет платежей",breadcrumbs);
    }

    @Test
    public void form ()  {
        WebElement phone = driver.findElement(By.id("connection-phone"));
        phone.clear();
        phone.sendKeys("297777777");
        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        sumInput.sendKeys("10");
        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("karamelev@tut.by");

//       new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedCondition.elementToBeClickable());

//        driver.findElement(By.xpath("//button[@class='cookie__close']")).click();
        driver.findElement(By.xpath("//form[@class='pay-form opened']/button[contains(.,'Продолжить')]")).click();
////               new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.switchTo().frame("allowpaymentrequest");
////
//        WebElement popap = driver.findElement(By.xpath("//div[@class='app-wrapper']"));
//        Assertions.assertTrue(popap.isDisplayed());
    }


}
