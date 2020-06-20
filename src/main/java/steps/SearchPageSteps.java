package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.SearchPage;
import utils.AllureUtils;

import java.io.IOException;
import java.util.List;

import static steps.BaseSteps.pageObject;

public class SearchPageSteps {
    SearchPage searchPage = new SearchPage();

    @Then("страница {string} загружена")
    public void pageLoaded(String name) throws Exception {
        Class example = Class.forName("pages." + name);
        pageObject = (BasePage)example.getDeclaredConstructor().newInstance();
    }

    @When("поле {string} заполняется значением {string}:")
    public void fillField(String name, String value) throws Exception {
        pageObject.fillField(name, value);
    }

    @When("добавить в корзину {string} {string} товаров:")
    public void addNPurchasesString  (String numberOfPurchase, String name) {
        searchPage.addNPurchases(numberOfPurchase, name);
    }

    @When("выполнено нажатие на:")
    public void click(List<String> arg) throws Exception {
        for (String name: arg) {
            pageObject.click(name);
        }
    }

    @When("выполнен переход в корзину:")
    public void toCart() {
        searchPage.toCart();
    }

}