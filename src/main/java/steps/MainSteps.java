package steps;

import io.cucumber.java.en.When;
import pages.MainPage;

public class MainSteps {

    MainPage mainPage = new MainPage();

    @When("произведен поиск товара {string}:")
    public void search(String searchText){
        mainPage.search(searchText);
    }


}