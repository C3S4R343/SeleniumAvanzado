package stepdefinitions;

import Pages.InventoryPage;
import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BaseTest;

public class LoginSteps extends BaseTest {
    WebDriver driver = getDriver();
    LoginPage loginPage;
    InventoryPage inventoryPage;
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
    }
    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) throws InterruptedException {
        loginPage.login(username, password);
        Thread.sleep(3000);
    }
    @When("I click on the login button")
    public void i_click_on_the_login_button() {

    }
    @Then("the login result should be correct for {string}")
    public void the_login_result_should_be_correct_for(String username) {
        inventoryPage = new InventoryPage(driver);

        boolean loginSuccess = inventoryPage.isOnInventoryPage();

        boolean shouldFail = username.equals("locked_out_user");


        if (shouldFail) {
            Assert.assertFalse(loginSuccess, "El login debería fallar para: " + username);
        } else {
            Assert.assertTrue(loginSuccess, "El login debería funcionar para: " + username);
        }

        BaseTest.quitDriver();
    }
    }

