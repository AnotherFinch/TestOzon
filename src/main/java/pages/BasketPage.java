package pages;

import annotation.Product;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.VirtualCart;
import java.util.*;
import static junit.framework.TestCase.assertTrue;

public class BasketPage extends BasePage {

    @FindBy(xpath = "//div[@select_button_name ='Выбрать все']/label/label")
    @Product(nameElement = "выбрать все")
    public WebElement selectAll;

    @FindBy(xpath = "//div[contains(text(), 'Удалить')]")
    @Product(nameElement = "подтвердить удаление")
    public WebElement confirmDel;

    @FindBy(xpath = "//span[contains(text(),'Удалить выбранные')]")
    @Product(nameElement = "удалить все")
    public WebElement deleteAll;

    @FindBy(xpath = "//div[@data-widget='split']//a[contains(@class,'')]/span")
    public List<WebElement> listOfPurchase;

    @FindBy(xpath = "//section[@data-widget='total']/div[2]/div[1]/span[2]")
    public WebElement totalNumber;

    @FindBy(xpath = "//h1[contains(text(), 'Корзина пуста')]")
    public WebElement emtyCart;


    public void checkPurchases() {
        Set<String> virtualCart = VirtualCart.getPurchase().keySet();
        Set<String> realCart = new TreeSet<>();
        for (WebElement element: listOfPurchase) {
            realCart.add(element.getText());
        }
        VirtualCart.getPurchase().forEach((name, price) -> {
            for (WebElement purch : listOfPurchase) {
                if (name.equals(purch.getText())) {
                    System.out.println(name + " соответствует " + purch.getText());;
                    break;
                }
            }
        });
        assertTrue("Записанные значения не соответствуют реальным", virtualCart.equals(realCart));
    }


    public void checkNumbers(String expected) {
        int expectedD = Integer.parseInt(expected);
        String[] tmp = totalNumber.getText().split(" ");
        int totalActual = Integer.parseInt(tmp[0]);
        assertTrue("Фактическое количество покупок " + totalActual +
                " не соответствует ожидаемому " + expected, expectedD == totalActual);
    }

    public void checkEmpty() {
        assertTrue("Корзина не пуста", emtyCart.isDisplayed());
    }

    @Override
    public WebElement getField(String name) throws Exception {
        return getField(name, "pages.CartPage");
    }
}