package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import utils.ExtentReportManager;

public class Hooks {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();

    @BeforeAll
    public static void setupReport() {
        extent = ExtentReportManager.getInstance();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        scenarioTest.set(test);
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            scenarioTest.get().fail("Step fallido");
        } else {
            scenarioTest.get().info("Step exitoso");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            scenarioTest.get().fail("Escenario fallido");
        } else {
            scenarioTest.get().pass("Escenario exitoso");
        }
    }

    @AfterAll
    public static void tearDownReport() {
        extent.flush();
    }
}
