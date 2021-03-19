package com.filippova.cawemo.app;

import com.filippova.cawemo.entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class UserHelper extends BaseHelper {

    public UserHelper(EventFiringWebDriver wd) {
        super(wd);
    }

    public void goToSignUpForm() {
        clickOnButton(By.xpath("/html/body/nav/div/a[2]"));
    }

    public void goToLoginForm() {
        clickOnButton(By.xpath("/html/body/nav/div/a[3]"));
    }

    public void fillInUserRegistrationForm(User user) throws InterruptedException {
        fillInInputData(By.xpath("//*[@id=\"root\"]/div/div/div/div/form/div[1]/input"), user.getUserName());
        fillInInputData(By.xpath("//*[@id=\"root\"]/div/div/div/div/form/div[2]/input"), user.getEmail());
        fillInInputData(By.xpath("//*[@id=\"password\"]"), user.getPassword());

        Thread.sleep(3000);

        clickOnButton(By.xpath("//*[@id=\"root\"]/div/div/div/div/form/div[4]/button"));
    }

    public void createNewProject() {
        clickOnButton(By.xpath("//*[@id=\"root\"]/main/header/div[1]/span/button"));
    }

    public void fillInLoginForm(User user) throws InterruptedException {
        fillInInputData(By.xpath("//*[@id=\"root\"]/div/div/div/div/form/div[1]/input"), user.getEmail());
        fillInInputData(By.xpath("//*[@id=\"root\"]/div/div/div/div/form/div[2]/input"), user.getPassword());

        Thread.sleep(3000);

        clickOnButton(By.xpath("//*[@id=\"root\"]/div/div/div/div/form/div[3]/button"));
    }
}
