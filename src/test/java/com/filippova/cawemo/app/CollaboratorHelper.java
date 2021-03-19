package com.filippova.cawemo.app;

import com.filippova.cawemo.entity.Collaborator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CollaboratorHelper extends BaseHelper {

    public CollaboratorHelper(WebDriver wd) {
        super(wd);
    }

    public void addCollaborator(Collaborator collaborator) {
        clickOnButton(By.xpath("//*[@id=\"collaborator-sidebar\"]/div/header/div[1]/span/button"));
        clickOnButton(By.xpath("/html/body/div[2]/div/div/div[1]/button"));

        List<WebElement> listOfCollaborators = getElementsByLocator(By.xpath("/html/body/ul/*"));
        listOfCollaborators.get(collaborator.getRole().getIndex()).click();

        fillInInputData(By.xpath("/html/body/div[2]/div/div/div[1]/div/div/input"), collaborator.getEmail());

        clickOutsideOfForm();
        clickOnButton(By.xpath("/html/body/div[2]/div/footer/button"));
    }
}
