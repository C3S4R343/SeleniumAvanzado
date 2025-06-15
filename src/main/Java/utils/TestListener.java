package utils;

// Importaciones necesarias para manejar reportes y eventos de prueba
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.*;

import org.openqa.selenium.WebDriver;
import test.loginPageTest; // Clase de prueba desde donde se extrae el WebDriver

/**
 * Clase que implementa ITestListener para reaccionar a eventos del ciclo de vida de TestNG.
 *
 * Funciones:
 * - Crea y actualiza el reporte de pruebas usando ExtentReports
 * - Toma capturas de pantalla cuando una prueba falla
 * - Marca los resultados (pasado, fallido, omitido) en el reporte
 */
public class TestListener implements ITestListener {

    // Obtenemos la única instancia de ExtentReports configurada previamente
    private static ExtentReports extent = ExtentReportManager.getInstance();

    // Usamos ThreadLocal para manejar pruebas en paralelo sin conflictos
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // Este método se ejecuta justo antes de que inicie cada prueba
    @Override
    public void onTestStart(ITestResult result) {
        // Creamos un nuevo bloque de prueba en el reporte con el nombre del método
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        // Lo almacenamos en el hilo actual
        testThread.set(test);
    }

    // Este método se ejecuta si la prueba finaliza exitosamente
    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Prueba pasada");
    }

    // Este método se ejecuta si la prueba falla
    @Override
    public void onTestFailure(ITestResult result) {
        // Registramos el error y su causa en el reporte
        testThread.get().log(Status.FAIL, "Prueba fallida: " + result.getThrowable());

        // Obtenemos la instancia del test para extraer el WebDriver
        Object testClass = result.getInstance();
        WebDriver driver = ((loginPageTest) testClass).getDriver();

        // Tomamos una captura de pantalla con el nombre del test
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());

        try {
            // Agregamos la imagen capturada al reporte visual
            testThread.get().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            testThread.get().log(Status.WARNING, "No se pudo adjuntar captura: " + e.getMessage());
        }
    }

    // Este método se ejecuta si la prueba fue saltada
    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Prueba saltada");
    }

    // Este método se ejecuta una vez al final de todas las pruebas del suite
    @Override
    public void onFinish(ITestContext context) {
        // Muy importante: guarda el reporte generado (lo escribe en el archivo HTML)
        extent.flush();
    }
}
