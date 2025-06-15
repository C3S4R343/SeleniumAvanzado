package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.*;

import org.openqa.selenium.WebDriver;
import test.loginPageTest;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Prueba pasada");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().log(Status.FAIL, " Prueba fallida: " + result.getThrowable());
        Object testClass = result.getInstance();
        WebDriver driver = ((loginPageTest) testClass).getDriver();
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
        testThread.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "⏭ Prueba saltada");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Muy importante: guarda el reporte
    }
}
