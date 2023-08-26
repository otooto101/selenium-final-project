package finalproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MoviePage extends GeneralPage{

    public void moviePageTest(WebDriver webDriver){
        try {
            String MoviesXpath = "//div[contains(@class, 'cinema_container')]//div[@class='movies-deal']";
            List<WebElement> movies = webDriver.findElements(By.xpath(MoviesXpath));
            String firstMovieWithEastPointXpath = "";
            for(int i = 1; i <= movies.size(); i++){
                String iteratedMovieXpath = "//div[contains(@class, 'cinema_container')]//div[@class='movies-deal'][" + i + "]";
                String eastPointXpath = iteratedMovieXpath + "//div[@class='movie-logos']//a//div//img[contains(@src,'istFointi.svg')]";
                List<WebElement> matchingElements = webDriver.findElements(By.xpath(eastPointXpath));
                if (matchingElements.size() > 0){
                    firstMovieWithEastPointXpath = iteratedMovieXpath;
                    break;
                }
            }
            WebElement firstMovieWithEastPoint= webDriver.findElement(By.xpath(firstMovieWithEastPointXpath));
            hover(firstMovieWithEastPoint, webDriver);
            String buyButtonXpath = firstMovieWithEastPointXpath + "//div[@class='info-cinema-ticket']";
            WebElement buyButton = firstMovieWithEastPoint.findElement(By.xpath(buyButtonXpath));
            waitUntilClickable(buyButton, 5, webDriver);
            buyButton.click();
        } catch (InvalidSelectorException e) {
            System.out.println("There is no movies in cinema");
        }
    }

    public void hover(WebElement webElement, WebDriver webDriver){
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement).perform();
    }

    public void waitUntilClickable(WebElement webElement, long seconds, WebDriver webDriver){
        new WebDriverWait(webDriver, Duration.ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(webElement));
    }

}
