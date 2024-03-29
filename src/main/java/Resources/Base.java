
package main.java.Resources;

import java.time.Duration;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.qameta.allure.Allure;
import main.java.AppMethods.AppMainMethods;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class Base {

    private static ChromeDriver driver;

    private AppMainMethods appMainMethods;

//    private final Properties initialProperties = new Properties();

    private final static String url = "https://" + System.getProperty("url");
//    private final static String url = "https://academybugs.com";

    public ChromeDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(Boolean.parseBoolean(System.getProperty("setHeadless")));
        options.addArguments("--no-sandbox");
        options.addArguments("start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    @BeforeSuite
    public void logIn() {

        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(url);

        appMainMethods = new AppMainMethods();

    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
        System.out.println("Stop browser");
    }

    @AfterMethod
    public void failedTestsResults(ITestResult result) throws IOException {
        if (result.getStatus() != ITestResult.SUCCESS) {
            File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Thumbnails.of(screenshotAs).size(500, 400).outputQuality(0.2).toFile(screenshotAs);
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);

            StringBuilder logs = new StringBuilder();

            for (org.openqa.selenium.logging.LogEntry entry : logEntries) {
                logs.append(new Date(entry.getTimestamp()) + " "
                        + entry.getLevel() + " " + entry.getMessage());
                logs.append(System.lineSeparator());

            }
            System.out.println(logs);
            Allure.addAttachment("Console log: ", String.valueOf(logs));
        }
    }

    public static ChromeDriver getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public AppMainMethods getAppMainMethods() {
        return appMainMethods;
    }
}
