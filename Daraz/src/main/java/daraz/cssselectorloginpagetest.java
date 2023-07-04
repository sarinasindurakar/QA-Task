package daraz;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class cssselectorloginpagetest {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();


        // Initialize Extent Report
        String reportPath = "TestReport_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();

        // Flush and close Extent Report
        extent.flush();

    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture screenshot on failure
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                String screenshotName = result.getMethod().getMethodName() + "_failure.png";
                FileHandler.copy(source, new File(screenshotName));
                System.out.println("Screenshot captured: " + screenshotName);

                // Add screenshot to Extent Report
                test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testInvalidUsername() {
        // Create Extent Test
        test = extent.createTest("testInvalidUsername", "Verify login with invalid username");

        navigateToLoginPage();
        enterCredentials("invalidusername", "sarina@33");
        clickLoginButton();
        verifyErrorMessage("Please enter a valid phone number.");
    }

    @Test
    public void testInvalidPassword() {
        // Create Extent Test
        test = extent.createTest("testInvalidPassword", "Verify login with invalid password");

        navigateToLoginPage();
        enterCredentials("9860831743", "invalidpassword");
        clickLoginButton();
        verifyErrorMessage("Incorrect username or password.");
    }
    @Test
    public void testInvalidEmailPassword() {
        // Create Extent Test
        test = extent.createTest("testInvalidEmailPassword", "Verify login with invalid email and  password");

        navigateToLoginPage();
        enterCredentials("invalidusername", "invalidpassword");
        clickLoginButton();
        verifyErrorMessage("Please enter a valid phone number.");
    }
    @Test
    public void testemptyEmail() {
        // Create Extent Test
        test = extent.createTest("testEmptyEmailPassword", "Verify login with empty email ");

        navigateToLoginPage();
        enterCredentials("", "123334343");
        clickLoginButton();
        verifyemptyMessage("you can't leave this empty.");
    }
    @Test
    public void testemptypassword() {
        // Create Extent Test
        test = extent.createTest("testEmptyPassword", "Verify login with empty password ");

        navigateToLoginPage();
        enterCredentials("sarina@gmail.com" , "");
        clickLoginButton();
        verifyemptypassword("you can't leave this empty.");
    }



    // Helper methods

    private void navigateToLoginPage() {
        driver.get("https://member.daraz.com.np/user/login");
        // Code to navigate to the login page
    }

    private void enterCredentials(String username, String password) {
        WebElement usernameInput = driver.findElement(By.cssSelector("input[placeholder = 'Please enter your Phone Number or Email']"));
        WebElement passwordInput = driver.findElement(By.cssSelector("input[placeholder = 'Please enter your password']"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    private void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.cssSelector("button[type = 'submit']"));;
        loginButton.click();
    }

    private void verifyErrorMessage(String expectedErrorMessage) {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebElement errorElement = driver.findElement(By.cssSelector("div.next-feedback-content"));
        String actualErrorMessage = errorElement.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
    private void verifyemptyMessage(String errorMessage){
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebElement errorElement = driver.findElement(By.cssSelector("input[@error = 'You can't leave this empty.']"));
        String actualErrorMessage = errorElement.getText();
        Assert.assertEquals(actualErrorMessage, errorMessage);

    }
    private void verifyemptypassword(String errorpMessage){
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebElement errorElement = driver.findElement(By.cssSelector("input[@error = 'You can't leave this empty.']"));
        String actualErrorMessage = errorElement.getText();
        Assert.assertEquals(actualErrorMessage, errorpMessage);

    }
}



