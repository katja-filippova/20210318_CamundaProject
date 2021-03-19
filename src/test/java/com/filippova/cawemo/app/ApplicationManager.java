package com.filippova.cawemo.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public final static Logger LOGGER = LoggerFactory.getLogger(ApplicationManager.class);

    private final static String HOMEPAGE_URL = "https://cawemo.com/";

    private EventFiringWebDriver wd;
    private final String browser;

    private UserHelper userHelper;
    private CollaboratorHelper collaboratorHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        String webdriverLocation = getProperties().getProperty(browser);

        switch (browser) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", webdriverLocation);
                wd = new EventFiringWebDriver(new ChromeDriver());
                break;
            case BrowserType.FIREFOX:
                System.setProperty("webdriver.gecko.driver", webdriverLocation);
                wd = new EventFiringWebDriver(new FirefoxDriver());
                break;
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        userHelper = new UserHelper(wd);
        collaboratorHelper = new CollaboratorHelper(wd);

        wd.register(new LoggingWebDriverEventListener());
    }

    public void stop() {
        wd.quit();
    }

    public void navigateToHomePage() {
        if (wd != null) {
            wd.navigate().to(HOMEPAGE_URL);
        }
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public CollaboratorHelper getCollaboratorHelper() {
        return collaboratorHelper;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("webdriver.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Can't load webdriver properties", e);
        }
        return properties;
    }

    public static class LoggingWebDriverEventListener extends AbstractWebDriverEventListener {

        public final static Logger LOGGER = LoggerFactory.getLogger(LoggingWebDriverEventListener.class);

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            LOGGER.info("Start search " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            LOGGER.info(by + " found.");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            LOGGER.error(throwable.toString());
        }
    }
}
