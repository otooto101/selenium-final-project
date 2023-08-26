package finalproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class FirstMoviePage extends GeneralPage{
    public String firstMovieName;
    public String movieDate;
    public String movieTime;
    public String cinemaName;

    public void firstMoviePage(WebDriver webDriver, JavascriptExecutor js){
        String cinemaTypeButtonXpath = "//ul[contains(@class,'cinema-tabs')]//li//a[contains(@href,'#384933')]";
        WebElement cinemaTypeButton = webDriver.findElement(By.xpath(cinemaTypeButtonXpath));
        cinemaName = cinemaTypeButton.getText();

        String movieNameXpath = "//div[@class='info']/p[@class='name']";
        firstMovieName = webDriver.findElement(By.xpath(movieNameXpath)).getText();

        WebElement cinemaTabs = webDriver.findElement(By.xpath("//ul[contains(@class,'cinema-tabs')]"));
        scrollToElement(webDriver, js, cinemaTabs, cinemaTypeButton);
        scrollWithPixels(js, 0, -120); //-120 is head size of page
        cinemaTypeButton.click();

        String calendarTabsXpath = "//*[@id='384933']/div/ul/li";
        List<WebElement> dates = webDriver.findElements(By.xpath(calendarTabsXpath));
        WebElement lastOption = null;

        for(WebElement date : dates) {
            date.click();
            String optionsXpath = "//*[@id='384933']/div/div[@aria-hidden='false']";
            List<WebElement> optionsOnDate = webDriver.findElements(By.xpath(optionsXpath));
            // აქ ყველაფერი მიწერია დინამიურად მაგიტომ გამოდის გრძელი კოდი ბოლო სეანსი რომ დააბრუნოს და იმისი ფილმის სახელი, თარიღი, საათი
            for (int i = 1; i <= optionsOnDate.size(); i++) {
                String optionTextXpath = optionsXpath + "[" + i + "]/a/p[@class='cinema-title']";
                WebElement optionText = webDriver.findElement(By.xpath(optionTextXpath));
                Assert.assertEquals(optionText.getText(), cinemaTypeButton.getText());
                String optionTimeXpath = optionsXpath + "[" + i + "]/a/p[@style]";
                WebElement optionTime = webDriver.findElement(By.xpath(optionTimeXpath));
                movieTime = optionTime.getText();
                String optionXpath = optionsXpath + "[" + i + "]";
                WebElement option = webDriver.findElement(By.xpath(optionXpath));
                movieDate = option.getAttribute("id");
                lastOption = optionText;
            }
        }
        lastOption.click();
    }

    public void scrollToElement(WebDriver webDriver,JavascriptExecutor js, WebElement tab, WebElement target){
        Actions builder = new Actions(webDriver);
        builder.moveToElement(tab).perform();
        js.executeScript("arguments[0].scrollIntoView()", target);
    }

    public void scrollWithPixels(JavascriptExecutor js, int x, int y){
        js.executeScript("window.scrollBy("+ x + ", " + y + ")");
    }
}
