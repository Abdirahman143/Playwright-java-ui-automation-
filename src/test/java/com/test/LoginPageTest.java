package com.test;

import com.microsoft.playwright.Page;
import com.pages.SignUpPage;
import com.utils.RandomUserEmailCreation;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.Base.BaseClass.closeBrowser;
import static com.Base.BaseClass.setUp;
import static com.utils.RandomUserEmailCreation.generateRandomEmail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class LoginPageTest {
   private Page page;

   SignUpPage signUpPage;
    @BeforeSuite
    public void setUpSuite() {
        setUp(); // Initialize Playwright and create the page instance
        this.page = com.Base.BaseClass.page; // Assign the initialized page to the local page variable
        signUpPage = new SignUpPage(page); // Now initialize the SignUpPage with the properly initialized page
    }

    @Test(priority = 0)
    public void verifyHomePage(){
        boolean actual = signUpPage.isHomePageVisible();
        assertTrue(actual);
    }

    @Test(priority = 1)
    public void clickSingUpButtonAndVerifySignUpPageHeader(){
       signUpPage.clickSignUpButton();
       String actual = signUpPage.getSignUpPageHeader();
       String expected ="New User Signup!";
       assertThat(actual).isEqualTo(expected);

    }

    @Test(priority = 2)
    public void enterUserNameAndEmail(){
       String userName ="Abdi";
       String email = generateRandomEmail();
       signUpPage.enterUserNameAndEmail(userName,email);
       signUpPage.clickSubmitButton();
    }

    @AfterSuite
    public void tearDown(){
       closeBrowser();
    }

}
