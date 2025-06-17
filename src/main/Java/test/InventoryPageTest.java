package test;

import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class InventoryPageTest {
    /*
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {
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
        loginPage = new LoginPage(driver);// Inicialización correcta de LoginPage
        inventoryPage = new InventoryPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void veirfyAllElements() {
        inventoryPage.verifyCorrectPage("https://www.saucedemo.com/inventory.html");
        inventoryPage.verifyMenu();
    }

    @Test
    public void procesoCompra(){
        inventoryPage.enlistarElementos();
        inventoryPage.verifyCart();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Cierra el navegador después de la prueba
        }
    }
    */

}
