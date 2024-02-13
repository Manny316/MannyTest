package test.folder.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;


public class AmazonTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass()
    public void openBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(1000);
        driver.navigate().to("https://www.amazon.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test(alwaysRun = true)
    public void amazonTest(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@aria-label,'Search Amazon')]")));
        driver.findElement(By.xpath("//input[contains(@aria-label,'Search Amazon')]")).sendKeys("lg tv 65");

        driver.findElement(By.xpath("//input[contains(@value,'Go')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@data-image-index='3']")));
        driver.findElement(By.xpath("//img[@data-image-index='3']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@alt,'Customer Support')]")));
        String text = driver.findElement(By.xpath("//span[contains(.,'65 Inches')]")).getText();
        assertTrue(text.contains("65"));

        String text1 = driver.findElement(By.xpath("//span[@class='a-size-base po-break-word'][contains(.,'Sony')]")).getText();
        assertTrue(text1.contains("Lg"));
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser(){
        driver.close();
    }
}
