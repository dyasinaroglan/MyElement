package testng;

import org.openqa.selenium.By;

public interface Locators {

    String url = "http://opencart.abstracta.us/";
    By lMyAccount = By.cssSelector("a[title='My Account']");
    By lLoginLink = By.xpath("//a[text()='Login']");
    By lInputPassword = By.id("input-password");
    By lemail = By.id("input-email");
    By lSubmitButton = By.xpath("//input[@value='Login']");
}
