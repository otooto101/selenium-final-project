package finalproject.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BuyTicketPage extends GeneralPage{
    public String firstMovieName;
    public String movieDate;
    public String movieTime;
    public String cinemaName;

    public BuyTicketPage(String firstMovieName, String movieDate, String movieTime, String cinemaName) {
        this.firstMovieName = firstMovieName;
        this.movieDate = movieDate;
        this.movieTime = movieTime;
        this.cinemaName = cinemaName;
    }

    public void buyTicketPage(WebDriver webDriver, JavascriptExecutor js){
        String zoomOutButtonXpath = "//button[@class='zoomOut']";
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(zoomOutButtonXpath)));
        WebElement zoomOutButton = webDriver.findElement(By.xpath(zoomOutButtonXpath));

        clickMultipleTimes(2, zoomOutButton);

        String currentMovieTitleXpath = "//div[@class='content-header']/p[@class='movie-title']";
        WebElement currentMovieTitle = webDriver.findElement(By.xpath(currentMovieTitleXpath));
        checkNameIsValid(currentMovieTitle.getText());

        String currentMovieCinemaXpath = "//div[@class='content-header']/p[@class='movie-cinema']";
        WebElement currentMovieCinema = webDriver.findElement(By.xpath(currentMovieCinemaXpath));
        checkCinemaIsValid(currentMovieCinema.getText());

        String currentMovieDateTimeXpath = "//div[@class='content-header']/p[@class='movie-cinema'][2]";
        WebElement currentMovieDateTime = webDriver.findElement(By.xpath(currentMovieDateTimeXpath));
        checkDateTimeIsValid(currentMovieDateTime.getText());

        String freeSeatXpath = "//*[@id='cinema-tickets']/div/div/div[contains(@class,'container')]/div[@class='seat free']/div";
        try {
            WebElement freeSeat = webDriver.findElement(By.xpath(freeSeatXpath));
            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(freeSeat));

            jsScrollToElement(js, freeSeat);
            jsClick(js, freeSeat);

            String registrationXpath = "//p[@class='register']";
            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(registrationXpath)));
            WebElement registrationButton = webDriver.findElement(By.xpath(registrationXpath));
            registrationButton.click();
        } catch (InvalidSelectorException e) {
            System.out.println("there is no free seats");
        }
    }

    public void clickMultipleTimes(int time, WebElement button){
        for (int i = 0; i <= time; i++) {
            button.click();
        }
    }

    public void checkNameIsValid(String movieTitle){
        Assert.assertEquals(movieTitle, firstMovieName);
    }

    public void checkCinemaIsValid(String cinemaTitle){
        Assert.assertEquals(cinemaTitle, cinemaName);
    }

    public void checkDateTimeIsValid(String movieDateTime){
        // movieDateTime format here will be '14 აგვისტო 13:00' for example so we must transform
        Map<String, String> monthsMap = new HashMap<>();
        monthsMap.put("01", "იანვარი");
        monthsMap.put("02", "თებერვალი");
        monthsMap.put("03", "მარტი");
        monthsMap.put("04", "აპრილი");
        monthsMap.put("05", "მაისი");
        monthsMap.put("06", "ივნისი");
        monthsMap.put("07", "ივლისი");
        monthsMap.put("08", "აგვისტო");
        monthsMap.put("09", "სექტმებერი");
        monthsMap.put("10", "ოქტომბერი");
        monthsMap.put("11", "ნოემბერი");
        monthsMap.put("12", "დეკემბერი");

        String[] dateTimeElements = movieDateTime.split(" ");
        String day = dateTimeElements[0];
        String month = dateTimeElements[1];
        String time = dateTimeElements[2];

        String dateToCompare = movieDate;
        String timeToCompare = movieTime;
        // my string format from firstMoviePage will be 'day-choose-28.04.2003' for example so i must transform
        dateToCompare = dateToCompare.split("-")[2];
        // now format is 28.04.2003 so split for .
        String[] dayMonthToCompare = dateToCompare.split("\\.");
        String dayToCompare = dayMonthToCompare[0];
        String monthToCompare = dayMonthToCompare[1];
        String monthToCompareInGeo = monthsMap.get(monthToCompare);
        Assert.assertEquals(day, dayToCompare);
        Assert.assertEquals(month, monthToCompareInGeo);
        Assert.assertEquals(time, timeToCompare);

    }
}
