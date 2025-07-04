package test;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class loginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;

    public WebDriver getDriver() {
        return driver;
    }

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
        loginPage = new LoginPage(driver); // Inicialización correcta de LoginPage
    }

    @Test
    public void verifyElementsLogin(){
        loginPage.elementsVisible();
    }

//    @DataProvider(name = "users")
//    public Object[][] userProvider(){
//        return new Object[][]{
//                {"standard_user", "secret_sauce"},
//                {"locked_out_user", "secret_sauce"},
//                {"problem_user", "secret_sauce"}
//        };
//    }
//
//
//    @Test(dataProvider = "users")
//    public void testLogin(String username, String password){
//        loginPage.login(username, password);
//        Assert.assertFalse(username.equals("locked_out_user") || username.equals("problem_user"), "El login fallo para " + username);
//    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit(); // Cierra el navegador después de la prueba
        }
    }
}
