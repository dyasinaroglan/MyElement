package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyElement {

    private By locator;
    private WebElement element;
    WebDriver driver;
    WebDriverWait wait;


    private MyElement(){
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private MyElement(By locator){
        this();
        this.locator = locator;
        element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private MyElement(WebElement element){
        this();
        this.element = element;
    }

    public static MyElement $(By locator){
        return new MyElement(locator);
    }

    public static MyElement $(WebElement element){
        return new MyElement(element);
    }

    private void openSite(String url){
        driver = Driver.getDriver();
        driver.get(url);
    }

    public static void open(String url){
        new MyElement().openSite(url);
    }

    public void click(){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public MyElement sendkeys(String str){
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(str);
        return this;
    }

    public MyElement hover(){
        new Actions(driver).moveToElement(element).build().perform();
        return this;
    }

    public MyElement scrollIntoView(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public MyElement shouldbe(String waitCondition){
        switch (waitCondition){
            case "clickable":
                wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            case "visible":
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            default:
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                break;
        }
        return this;
    }


}
