package pages;

import annotation.Product;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.VirtualCart;
import java.util.List;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//div[contains(text(), 'Цена')]/parent::div//input[@qa-id = 'range-to']")
    @Product(nameElement = "максимальная цена")
    public WebElement maxPrice;

    @FindBy(xpath = "//div[@value = 'Высокий рейтинг']//div[@class = 'ui-at4']")
    @Product(nameElement = "высокий рейтинг")
    public WebElement highRate;

    @FindBy(xpath = "//span[contains(text(),'NFC')]/parent::div/parent::label//div[@class='ui-at4']")
    @Product(nameElement = "NFC")
    public WebElement NFC;

    @FindBy(xpath = "//div[@data-widget='searchResultsV2']/child::div/child::div")
    public List<WebElement> searchResults;

    @FindBy(xpath = "//div[@data-widget='searchResultsV2']/child::div/child::div//div[contains(text(), 'В корзину')]")
    public List<WebElement> addToCart;

    @FindBy(xpath = "//div[contains(@style,'grid-column-start')]/div/div/div[2]/div/a")
    public List<WebElement> resultName;

    @FindBy(xpath = "//div[contains(@style,'grid-column-start')]/div/div/div[3]//span[contains(text(), '\u20BD')]")
    public List<WebElement> resultPrice;

    @FindBy(xpath = "//span[contains(text(),'Корзина')]")
    @Product(nameElement = "корзина")
    public WebElement cart;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/parent::div//span[contains(text(),'Beats')]")
    @Product(nameElement = "Beats")
    public WebElement beats;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/parent::div//span[contains(text(),'Samsung')]")
    @Product(nameElement = "Samsung")
    public WebElement samsung;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/parent::div//span[contains(text(),'Xiaomi')]")
    @Product(nameElement = "Xiaomi")
    public WebElement xiaomi;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/parent::div//span[contains(text(),'Посмотреть все')]")
    @Product(nameElement = "Посмотреть все")
    public WebElement expand;




    public void addNPurchases (String  numberOfPurchase, String name) {
        int oddEven = 0;
        if(name.equals("четных")||name.equals("четные")||name.equals("четный")) {
            oddEven = 1;
        }
        int number = 0;
        if (numberOfPurchase.equals("все")) {
            number = resultName.size()/2;
        }
        else number = Integer.parseInt(numberOfPurchase);


        int numberOfResult = oddEven;
        for(int i = 0; i < number; i++) {
            WebElement thisAddToCart = addToCart.get(numberOfResult-i);
            WebElement thisResultName = resultName.get(numberOfResult);
            WebElement thisResultPrice = resultPrice.get(numberOfResult);
            click(thisAddToCart);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String nameVirtual = thisResultName.getText();
            double priceVirtual = getValue(thisResultPrice);
            VirtualCart.addPurchase(nameVirtual, priceVirtual);
            numberOfResult = numberOfResult + 2;
        }
    }

    public void toCart(){
        click(cart);
    }

    @Override
    public WebElement getField(String name) throws Exception {
        return getField(name, "pages.SearchPage");
    }

}