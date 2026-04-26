package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.WindowType;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        // ✅ Use real Firefox binary (non-snap)
        // ✅ Firefox setup for Ubuntu / Jenkins
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox");   // IMPORTANT
        options.setBinary("/usr/bin/firefox");

        // 🔥 IMPORTANT FIXES
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();

        // ✅ Use size instead of maximize (headless safe)
        driver.manage().window().setSize(new Dimension(1920, 1080));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            // -------- Tab 1 : SauceDemo --------
            driver.get("https://www.saucedemo.com/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                    .sendKeys("standard_user");

            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // -------- Tab 2 --------
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://automationexercise.com/");

            Actions actions = new Actions(driver);

            WebElement product = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//div[@class='product-image-wrapper'])[1]")
                    )
            );

            actions.moveToElement(product).perform();

            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("(//a[contains(text(),'Add to cart')])[1]")
                    )
            );

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", addToCart);

            addToCart.click();

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[text()='Continue Shopping']")
                    )
            );

            continueBtn.click();

            // -------- Tab 3 --------
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://practicetestautomation.com/practice-test-login/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                    .sendKeys("student");

            driver.findElement(By.id("password")).sendKeys("Password123");
            driver.findElement(By.id("submit")).click();

            // Optional validation
            // ✅ Validation
            wait.until(ExpectedConditions.urlContains("logged-in-successfully"));

            System.out.println("✅ Test executed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
