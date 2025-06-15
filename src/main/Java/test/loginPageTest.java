package test;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe"); // Sin .exe en macOS/Linux
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver); // Inicialización correcta de LoginPage
    }

    @Test
    public void verifyElementsLogin(){
        loginPage.elementsVisible();
    }

    @DataProvider(name = "users")
    public Object[][] userProvider(){
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"}
        };
    }


    @Test(dataProvider = "users")
    public void testLogin(String username, String password){
        loginPage.login(username, password);
        Assert.assertTrue(username.equals("locked_out_user"), "El login fallo para " + username);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit(); // Cierra el navegador después de la prueba
        }
    }
}
