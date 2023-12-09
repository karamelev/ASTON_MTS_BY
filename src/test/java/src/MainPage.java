package src;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    protected WebDriver driver;
    private Actions actions;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        driver.manage().window().maximize();
    }
    private Actions getActions(){
        if (actions==null) {
            actions = new Actions(driver);
        }
        return actions;
    }

    @FindBy(css = "#app > .general-preloader")
    private WebElement preloader;
    @FindBy(id ="searchInput")
    private WebElement searchInput;
    @FindBy(id = "applySearchBtn")
    private WebElement buttonSearch;
    @FindBy(xpath = "//a[@data-wba-header-name='Cart']/span")
    private WebElement buttonGoToBasket;
    @FindBy(css = ".product-card__add-basket")
    private WebElement buttonAddToBasket;
    @FindBy(css = ".product-card")
    private WebElement productCard;
    @FindBy(css = ".product-card__name")
    private WebElement productName;
    @FindBy(css = ".price__lower-price")
    private WebElement productPrice;

    public void waitForLoaded(){
        new WebDriverWait (driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(preloader));
    }
    public void searchForProduct(String productName) {
        searchInput.sendKeys(productName);
        buttonSearch.click();
    }
    public WebElement getCardByIndex(int index) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> productCards = driver.findElements(By.cssSelector(".product-card"));
        //        List<WebElement> productCards = productCard.;
        return productCards.get(index);
    }
    public Product getCardData(WebElement card) {
        String name = card.findElement(By.cssSelector(".product-card__name")).getText().replace("/ ", "");
//        String name = productName.getText();
        Integer price = Integer.parseInt(card.findElement(By.cssSelector(".price__lower-price")).getText()
                .replaceAll(" ", "")
                .replaceAll("₽", ""));
//        System.out.println(name);
//        System.out.println(price);
        return new Product(name,price);
    }
    public void addToCart(WebElement card) throws InterruptedException {
        getActions().moveToElement(card).perform();
        card.findElement(By.cssSelector(".product-card__add-basket")).click();
    }
    public CartPage goToCart() {
        buttonGoToBasket.click();
        return new CartPage(driver);
    }

}
