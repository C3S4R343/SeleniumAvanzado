package utils;

// Librerías necesarias para trabajar con archivos, fechas y Selenium
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase utilitaria para capturar pantallas (screenshots) durante la ejecución de pruebas.
 *
 * Se usa comúnmente en pruebas fallidas para adjuntar evidencia visual al reporte.
 * La imagen se guarda como archivo .png dentro de "test-output/screenshots/" con nombre
 * basado en el nombre del test y un timestamp para evitar sobreescritura.
 */
public class ScreenshotUtil {

    /**
     * Captura una imagen del navegador actual y la guarda como archivo PNG.
     /
     // @param driver    El WebDriver activo en la prueba.
     // @param testName  El nombre del método de prueba (se usa en el nombre del archivo).
     //@return          Ruta relativa al archivo de imagen (para incrustarla en el reporte).
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        // Captura la pantalla como archivo temporal
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Genera un timestamp para identificar el momento de la prueba
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Define la ruta relativa (para el HTML del reporte) y la ruta completa (para guardarla físicamente)
        String relativePath = "screenshots/" + testName + "_" + timestamp + ".png";
        String fullPath = "test-output/" + relativePath;

        try {
            // Guarda el archivo capturado en la ruta definida
            FileUtils.copyFile(src, new File(fullPath));
            System.out.println("Captura guardada en: " + fullPath);
        } catch (IOException e) {
            // Si hay un error al guardar la imagen, lo muestra en consola
            System.err.println("Error al guardar captura: " + e.getMessage());
        }

        // Devuelve la ruta relativa (necesaria para que ExtentReports pueda mostrarla en el HTML)
        return relativePath;
    }
}
