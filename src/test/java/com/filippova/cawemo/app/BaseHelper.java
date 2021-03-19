package com.filippova.cawemo.app;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public abstract class BaseHelper {

    private final WebDriver wd;
    private final Random randomGenerator = new Random();

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void fillInInputData(By locator, String inputInfo) {
        if (inputInfo != null) {
            clickOnButton(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(inputInfo);
        }
    }

    public void clickOnButton(By locator) {
        wd.findElement(locator).click();
    }

    public void acceptPopUp(String idOfPopUpContainer, String idOfPopUpButton) {
        if (isElementPresentByLocator(By.cssSelector(idOfPopUpContainer))) {
            wd.findElement(By.cssSelector(idOfPopUpButton)).click();
        }
    }

    public int getRandomInt() {
        return randomGenerator.nextInt(1000);
    }

    public void clickOutsideOfForm() {
        wd.findElement(By.xpath("//body")).click();
    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshot" + System.currentTimeMillis() + ".png");
        Files.copy(tmp.toPath(), screenshot.toPath());
        return screenshot.getAbsolutePath();
    }

    public List<WebElement> getElementsByLocator(By locator) {
        return wd.findElements(locator);
    }

    public boolean isElementPresentByLocator(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(wd, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException | NoSuchElementException ignored) {
            return false;
        }
        return true;
    }
}
