package com.filippova.cawemo.tests;

import com.filippova.cawemo.entity.Collaborator;
import com.filippova.cawemo.entity.CollaboratorRole;
import com.filippova.cawemo.entity.User;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddCollaboratorWithLoginTests extends TestBase {

    @Test
    public void testThatCollaboratorIsAddedAfterLoginAndCreateNewProject() throws InterruptedException {
        userHelper.acceptPopUp("#hs-eu-cookie-confirmation-inner", "#hs-eu-confirmation-button");
        userHelper.goToLoginForm();
        userHelper.fillInLoginForm(new User("ivan208@gmail.com", "12345678Kk"));
        userHelper.createNewProject();
        collaboratorHelper.addCollaborator(new Collaborator("katja@gmail.com", CollaboratorRole.COMMENTER));

        assertTrue(collaboratorHelper.isElementPresentByLocator(By.xpath("//*[@id=\"collaborator-sidebar\"]/div/ul/li[2]")));
    }

    @Test
    public void negativeTestThatCollaboratorIsAddedAfterLoginAndCreateNewProject() throws InterruptedException {
        userHelper.acceptPopUp("#hs-eu-cookie-confirmation-inner", "#hs-eu-confirmation-button");
        userHelper.goToLoginForm();
        userHelper.fillInLoginForm(new User("ivan208@gmail.com", "12345678Kk"));
        userHelper.createNewProject();
        collaboratorHelper.addCollaborator(new Collaborator("vasja" + collaboratorHelper.getRandomInt(), CollaboratorRole.EDITOR));

        assertFalse(collaboratorHelper.isElementPresentByLocator(By.xpath("//*[@id=\"collaborator-sidebar\"]/div/ul/li[2]")));
    }
}
