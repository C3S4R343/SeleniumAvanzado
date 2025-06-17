package test;

import Pages.CheckoutPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.cartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class E2ETest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private cartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
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
