package com.filippova.cawemo.tests;

import com.filippova.cawemo.entity.Collaborator;
import com.filippova.cawemo.entity.User;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.filippova.cawemo.entity.CollaboratorRole.COMMENTER;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddCollaboratorWithSignUpTests extends TestBase {

    @Test
    public void testThatCollaboratorIsAddedAfterSignUpAndCreateNewProject() throws InterruptedException {
        userHelper.acceptPopUp("#hs-eu-cookie-confirmation-inner", "#hs-eu-confirmation-button");
        userHelper.goToSignUpForm();
        userHelper.fillInUserRegistrationForm(new User(
                "Ivan Ivanov",
                "ivan" + userHelper.getRandomInt() + "@gmail.com",
                "12345678Kk"));
        userHelper.createNewProject();
        collaboratorHelper.addCollaborator(new Collaborator(
                "ivan123" + userHelper.getRandomInt() + "@gmail.com",
                COMMENTER));

        assertTrue(userHelper.isElementPresentByLocator(By.xpath("//*[@id=\"collaborator-sidebar\"]/div/ul/li[1]")));
    }

    @Test
    public void negativeTestThatUserWithInvalidEmailIsSignedUp() throws InterruptedException {
        userHelper.acceptPopUp("#hs-eu-cookie-confirmation-inner", "#hs-eu-confirmation-button");
        userHelper.goToSignUpForm();
        userHelper.fillInUserRegistrationForm(new User(
                "Ivan Ivanov",
                "ivan" + userHelper.getRandomInt(),
                "12345678Kk"));

        assertTrue(userHelper.isElementPresentByLocator(By.xpath("//*[@id=\"root\"]/div/div/div/div/form")));
    }

    @Test
    public void negativeTestThatCollaboratorWithInvalidEmailIsAddedAfterSignUpAndCreateNewProject() throws InterruptedException {
        userHelper.acceptPopUp("#hs-eu-cookie-confirmation-inner", "#hs-eu-confirmation-button");
        userHelper.goToSignUpForm();
        userHelper.fillInUserRegistrationForm(new User(
                "Ivan Ivanov",
                "ivan" + userHelper.getRandomInt() + "@gmail.com",
                "12345678Kk"));
        userHelper.createNewProject();
        collaboratorHelper.addCollaborator(new Collaborator(
                "ivan123" + userHelper.getRandomInt(),
                COMMENTER));

        assertFalse(userHelper.isElementPresentByLocator(By.xpath("//*[@id=\"collaborator-sidebar\"]/div/ul/li[2]")));
    }
}
