package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Ruta relativa dentro de test-output
        String relativePath = "screenshots/" + testName + "_" + timestamp + ".png";
        String fullPath = "test-output/" + relativePath;

        try {
            FileUtils.copyFile(src, new File(fullPath));
            System.out.println("Captura guardada en: " + fullPath);
        } catch (IOException e) {
            System.err.println("Error al guardar captura: " + e.getMessage());
        }

        // Regresamos la ruta RELATIVA para ExtentReports
        return relativePath;
    }
}
