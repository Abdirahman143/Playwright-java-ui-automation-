package com.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SignUpPage {
    private Page page;
    private Playwright playwright;
    private final String homePageIcon = "a[href='/'] i.fa.fa-home";
    private final String signUpButton = "//a[@href='/login' and contains(text(), 'Signup / Login')]";
    private final String signUpHeader = "//h2[text()='New User Signup!']";
    private final String userNameTxt = "[data-qa='signup-name']";
    private final String emailTxt = "[data-qa='signup-email']";
    private final String submitBtn = "[data-qa='signup-button']";

    public SignUpPage(Page page) {
        this.page = page;
    }

    public boolean isHomePageVisible() {
        return page.isVisible(homePageIcon);
    }

    public void clickSignUpButton() {
        page.click(signUpButton);
    }

    public String getSignUpPageHeader() {
        return page.textContent(signUpHeader);
    }

    public void enterUserNameAndEmail(String userName, String email) {
        page.fill(userNameTxt, userName);
        page.fill(emailTxt, email);
    }

    public void clickSubmitButton() {
        page.click(submitBtn);
    }
}
