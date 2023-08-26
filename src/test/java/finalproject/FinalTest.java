package finalproject;
import finalproject.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;

public class FinalTest {
    WebDriver webDriver;
    JavascriptExecutor js;

    @Test
    public void finalTest(){
        MainPage mainPageInstance = new MainPage();
        mainPageInstance.mainPageTest(webDriver);

        MoviePage moviePageInstance = new MoviePage();
        moviePageInstance.moviePageTest(webDriver);

        FirstMoviePage firstMoviePageInstance = new FirstMoviePage();
        firstMoviePageInstance.firstMoviePage(webDriver, js);

        BuyTicketPage buyTicketPageInstance = new BuyTicketPage(firstMoviePageInstance.firstMovieName
                , firstMoviePageInstance.movieDate
                , firstMoviePageInstance.movieTime
                , firstMoviePageInstance.cinemaName);
        buyTicketPageInstance.buyTicketPage(webDriver, js);

        RegistrationPage registrationPageInstance = new RegistrationPage();
        registrationPageInstance.registrationPage(webDriver, js);
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) webDriver = setUpChrome();
        else if (browser.equalsIgnoreCase("edge")) webDriver = setUpEdge();
        js = (JavascriptExecutor) webDriver;
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    private WebDriver setUpChrome(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        return new ChromeDriver(chromeOptions);
    }

    private WebDriver setUpEdge(){
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        return new EdgeDriver(edgeOptions);
    }

}
