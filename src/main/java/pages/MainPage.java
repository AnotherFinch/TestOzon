package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Искать на Ozon']")
    public WebElement searchField;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement searchButton;

    public void search(String searchText) {
        enter(searchField, searchText);
        click(searchButton);
    }

    @Override
    public WebElement getField(String name) throws Exception {
        return getField(name, "pages.MainPage");
    }
}