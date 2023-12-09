package src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".list-item")
    private WebElement listItem;
    @FindBy(css = ".list-item__price-new")
    private WebElement itemPrice;
    @FindBy(css = ".good-info__good-name")
    private WebElement itemName;

    @FindBy(xpath = "//div[@data-jsv='#2551^/2551^']")
    private WebElement priceLaptopTwo;
    @FindBy(css = "[data-jsv='#2515^/2515^']")
    private WebElement sumProduct;
    @FindBy(css = "[type='jsv#2505^']")
    private WebElement countProduct;



    public List<Product> getAllProductsData() {
        List<WebElement> cards = driver.findElements(By.cssSelector(".list-item"));
        List<Product> products = new ArrayList<>();
        for (WebElement card: cards) {
            products.add(new Product(this.getProductName(card),this.getProductPrice(card)));
        }
//        System.out.println(products);
        return products;
    }
    public String getProductName(WebElement card){
        return card.findElement(By.cssSelector(".good-info__good-name")).getText();
    }

    public Integer getProductPrice(WebElement card){
        Integer price = Integer.parseInt(card.findElement(By.cssSelector(".list-item__price-new")).getText()
                .replaceAll(" ", "")
                .replaceAll("₽", ""));
        return price;
    }

    public WebElement getSumProduct() {
        return sumProduct;
    }

    public WebElement getCountProduct() {
        return countProduct;
    }
}
