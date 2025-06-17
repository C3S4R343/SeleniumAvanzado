package test;

import Pages.CheckoutPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.cartPage;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class E2ETest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private cartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

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

        // Inicializar páginas
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new cartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void testEndToEndPurchaseFlow() {
        // Paso 1: Login
        loginPage.login("standard_user", "secret_sauce");

        // Paso 2: Agregar productos al carrito
        inventoryPage.enlistarElementos();  // Método que agrega uno o más productos
        inventoryPage.verifyCart();         // Hace clic en ícono del carrito
        try{
            Thread.sleep(3000);
            Alert aler = driver.switchTo().alert();
            aler.accept();
        } catch (NoAlertPresentException e){
            System.out.println("No alert");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Paso 3: Proceder al checkout
        cartPage.checkOut();               // Hace clic en el botón “Checkout”

        // Paso 4: Llenar formulario
        checkoutPage.checkElements();      // Valida que los campos estén visibles


        checkoutPage.fillInformation("Cesar", "Gonzalez", "37800");  // Llenado
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
