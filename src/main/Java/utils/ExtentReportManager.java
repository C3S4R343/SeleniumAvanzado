package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Clase responsable de configurar y proporcionar una única instancia de ExtentReports.
 *
 * Su función principal es generar un reporte HTML visual de los resultados de las pruebas,
 * utilizando el framework ExtentReports. Este reporte se guarda en la carpeta "test-output"
 * y contiene información como:
 * - Casos pasados, fallidos o ignorados
 * - Capturas de pantalla si hay errores
 * - Detalles del sistema como el SO o navegador
 *
 * Esta clase usa el patrón Singleton para que el reporte se cree solo una vez por ejecución.
 */
public class ExtentReportManager {

    // Variable estática que mantiene una sola instancia de ExtentReports
    private static ExtentReports extent;

    /**
     * Devuelve la instancia existente de ExtentReports o la crea si no existe.
     * Se asegura de que el reporte se configure correctamente solo una vez.
     *
     * @return instancia única de ExtentReports
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            // Crea el reporte HTML usando ExtentSparkReporter, y define la ruta de salida
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");

            // Inicializa el objeto ExtentReports
            extent = new ExtentReports();

            // Conecta el reporter visual (HTML) con el motor de reportes
            extent.attachReporter(spark);

            // Agrega información del sistema al reporte (opcional, pero útil)
            extent.setSystemInfo("OS", System.getProperty("os.name"));      // Sistema operativo actual
            extent.setSystemInfo("Browser", "Chrome");                      // Navegador utilizado (puede hacerse dinámico)
        }

        // Devuelve la instancia configurada
        return extent;
    }
}
