package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.collections.CollectionUtils;
import src.CartPage;
import src.MainPage;
import src.Product;

import java.util.ArrayList;
import java.util.Comparator;
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
        products.sort(Comparator.comparing(Product::getName));

        mainPage.goToCart();

        Thread.sleep(2000);
        List<Product> cartProducts = cartPage.getAllProductsData();
        System.out.println(products);
        System.out.println(cartProducts);

        Assertions.assertEquals(products.size(),cartProducts.size());

        Assertions.assertEquals(cartPage.getTotalPrice(), Product.getTotalPrice(products));

        for (int i = 0; i < cartProducts.size(); i++) {
           Product product =  products.get(i);
           Product cartProduct = cartProducts.get(i);
           Assertions.assertEquals(product.getName(),cartProduct.getName());
           Assertions.assertEquals(product.getPrice(),cartProduct.getPrice());
//            Assertions.assertTrue(product.equals(cartProduct)); //TODO
        }


    }
}
