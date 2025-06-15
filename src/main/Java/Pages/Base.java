package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {

    protected WebDriver driver;
    protected WebDriverWait wait;


    //Constructor de la clase Base
    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

        //Metodo para esperar hasta que un elemnto sea visible
        private WebElement waitForElementVisible(WebElement element) {
            return wait.until(ExpectedConditions.visibilityOf(element));
        }

        //Metodo para esperar hasta que un elemento sea clickeable
        private WebElement waitForElementToBeClickeable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        //Metodo para hacer click en un elemento con manejo de errores
        protected void click(WebElement element){
        try{
            waitForElementToBeClickeable(element).click();
        }catch(Exception e){
            throw new RuntimeException("No se pude hacer click en el elemento");
        }
        }

        //Metodo para escribir texto en un campo con validacion previa
        protected void writeText(WebElement element, String text){
            WebElement el = waitForElementVisible(element);
            el.clear();
            el.sendKeys(text);
        }



}
