package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",        // Ruta a tus archivos .feature
        glue = "stepdefinitions",                   // Paquete donde están tus step definitions
        plugin = {
                "pretty",                               // Salida legible en consola
                "html:target/cucumber-reports.html",    // Reporte HTML
                "json:target/cucumber.json",            // Reporte JSON
                "timeline:target/cucumber-timeline"     // Reporte visual de ejecución
        },
        monochrome = true,                          // Limpia la salida en consola
        dryRun = false                              // true para validar steps sin ejecutarlos
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
