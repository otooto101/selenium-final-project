package finalproject.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralPage {
    public void jsScrollToElement(JavascriptExecutor js, WebElement element){
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void jsClick(JavascriptExecutor js, WebElement button){
        js.executeScript("arguments[0].click();", button);
    }
}
