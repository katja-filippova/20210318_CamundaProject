package com.filippova.cawemo.tests;

import com.filippova.cawemo.app.ApplicationManager;
import com.filippova.cawemo.app.CollaboratorHelper;
import com.filippova.cawemo.app.UserHelper;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

public abstract class TestBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);

    private static final String browserType = BrowserType.CHROME;
    //private static final String browserType = BrowserType.FIREFOX;

    private final ApplicationManager app = new ApplicationManager(System.getProperty("browser", browserType));

    protected UserHelper userHelper;
    protected CollaboratorHelper collaboratorHelper;

    @BeforeMethod
    public void beforeMethod(Method m, Object[] objects) {
        LOGGER.info("Start test " + m.getName());

        app.init();
        app.navigateToHomePage();

        userHelper = app.getUserHelper();
        collaboratorHelper = app.getCollaboratorHelper();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws IOException {
        if (result.isSuccess()) {
            LOGGER.info("PASSED: test method " + result.getMethod().getMethodName());
        } else {
            LOGGER.error("FAILED: test method " + result.getMethod().getMethodName() + "\n" + "Screenshot: " + userHelper.takeScreenshot());
        }
        app.stop();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
}
