package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import src.CartPage;
import src.MainPage;
import src.Product;

import java.util.ArrayList;
import java.util.List;

public class TestWildberries {
    private WebDriver driver;
    private MainPage mainPage;
    private CartPage cartPage;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        driver.get("https://www.wildberries.ru/");
        mainPage.waitForLoaded();
    }

    @Test
    public void testNameProduct() throws InterruptedException {
        mainPage.searchForProduct("Ноутбук");
        List<Product> products = new ArrayList<>();
        int [] productsIndexes = {1,2};
        for (int index: productsIndexes) {
            WebElement card = mainPage.getCardByIndex(index);
            products.add(mainPage.getCardData(card));
            mainPage.addToCart(card);
        }
        mainPage.goToCart();
        List<Product> cartProducts = cartPage.getAllProductsData();
        System.out.println(products);
        System.out.println(cartProducts);

        Assertions.assertEquals(products.size(), cartProducts.size(),"Proper amount of items should be added to the cart");
//        for (Product product : products) {
//
//        }

        Assertions.assertEquals(products,cartProducts);
    }
}
