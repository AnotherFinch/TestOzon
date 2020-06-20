package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.BasketPage;
import java.util.List;
import static steps.BaseSteps.pageObject;

public class BasketSteps {
    BasketPage basketPage = new BasketPage();

    @Then("корзина {string} загружена")
    public void pageLoadedCart(String name) throws Exception {
        Class example = Class.forName("pages." + name);
        pageObject = (BasePage)example.getDeclaredConstructor().newInstance();
    }

    @Then("проверяем соответствие записанных значений фактическим в корзине:")
    public void checkPurchases() {
        basketPage.checkPurchases();
    }

    @Then("проверяем соответствие общего количества купленного товара ожидаемому {string}")
    public void checkNumbers(String expected) {
        basketPage.checkNumbers(expected);
    }

    @When("в корзине выполнено нажатие на:")
    public void click(List<String> arg) throws Exception {
        for (String name: arg) {
            pageObject.click(name);
        }
    }

    @Then("проверяем, что корзина пуста:")
    public void checkEmty() {
        basketPage.checkEmpty();
    }

}