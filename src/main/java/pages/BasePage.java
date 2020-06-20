package pages;
import annotation.Product;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import steps.BaseSteps;
import utils.DriverManager;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void clickWithScroll(WebElement element){
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        //   ((JavascriptExecutor)DriverManager.getDriver()).executeScript("scroll(0,200)");
        BaseSteps.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void click(WebElement element){
        BaseSteps.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    public void switchFrame(WebElement element){
        BaseSteps.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void clear(WebElement element){
        BaseSteps.wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
    }

    public void enter(WebElement nameField, String value) {
        clear(nameField);
        nameField.sendKeys(value);
    }

    public Double getValue(WebElement nameField) {
        BaseSteps.wait.until(ExpectedConditions.elementToBeClickable(nameField));
        String result1 = nameField.getText().replaceAll("[\\u20BD%\\s\\u2009]+","");
        String result2 = result1.replaceAll("\\,", "\\.");
        return Double.parseDouble(result2);
    }

    public void fillField(WebElement field, String value){
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", field);
        field.clear();
        field.sendKeys(value);
    }

    public void fillField(String name, String value) throws Exception {
        WebElement element = getField(name);
        clear(element);
        fillField(element, value);
    }

    public void click(String name) throws Exception {
        WebElement element = getField(name);
        click(element);
    }

    public WebElement getField(String name, String className) throws Exception {
        Class example = Class.forName(className);
        List<Field> fields = Arrays.asList(example.getFields());
        for (Field field : fields){
            if(field.isAnnotationPresent(Product.class)) {
                if (field.getAnnotation(Product.class).nameElement().equals(name)) {
                    return DriverManager.getDriver().findElement(By.xpath(field.getAnnotation(FindBy.class).xpath()));
                }
            }
        }
        Assert.fail("Не объявлен элемент с наименованием " + name);
        return null;
    }

    public abstract WebElement getField(String name) throws Exception;
}
