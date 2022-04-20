package cucumber;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testng.Locators.*;


public class MyStepdefs {
    @Given("user on homepage")
    public void userOnHomepage() {
        Configuration.headless = true;
        open(url);
    }

    @And("user clicks My accpunt")
    public void userClicksMyAccpunt() {
        $(lMyAccount).shouldBe(Condition.visible).click();
    }

    @And("user clicks loginLink")
    public void userClicksLoginLink() {
        $(lLoginLink).shouldBe(Condition.visible).click();
    }

    @When("user fill the form as follows")
    public void userFillTheFormAsFollows(DataTable table) {
        Map<String, String> map = table.asMap();
        $(lemail).shouldBe(Condition.visible).setValue(map.get("username"));
        $(lInputPassword).shouldBe(Condition.visible).setValue(map.get("password"));
    }

    @And("user clicks submit button")
    public void userClicksSubmitButton() {
        $(lSubmitButton).click();
    }

    @Then("login should be successful")
    public void loginShouldBeSuccessful() {
        Assert.assertEquals(Selenide.title(), "My Account");
    }
}
