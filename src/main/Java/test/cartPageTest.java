package test;

import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.cartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class cartPageTest {
    private WebDriver driver;
    private LoginPage logPage;
    private InventoryPage invPage;
    private cartPage cart;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe"); // Sin .exe en macOS/Linux
        // Solo se declara una vez
        ChromeOptions options = new ChromeOptions();

        // Desactiva el administrador de contraseñas
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Opcional: evitar alertas y extensiones
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        logPage = new LoginPage(driver);
        invPage = new InventoryPage(driver);
        cart = new cartPage(driver);
        logPage.login("standard_user", "secret_sauce");
        invPage.enlistarElementos();
        invPage.verifyCart();
    }

    @Test
    public void carritoElementos(){
        cart.checkOut();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
