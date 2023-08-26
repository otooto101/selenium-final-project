package finalproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class MainPage extends GeneralPage{

    public void mainPageTest(WebDriver webDriver){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
        webDriver.navigate().to("https://www.swoop.ge/");
        String movieMenuXpath = "//li[@class='MoreCategories']//a[contains(@href, '/events')]";
        WebElement movieMenu = webDriver.findElement(By.xpath(movieMenuXpath));
        movieMenu.click();
    }
}
