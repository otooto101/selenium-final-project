package finalproject.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationPage extends GeneralPage{

    public void registrationPage(WebDriver webDriver, JavascriptExecutor js){
        String personalRegistrationXpath = "//p[text()='ფიზიკური პირი']";
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(personalRegistrationXpath)));
        WebElement personalRegistrationButton = webDriver.findElement(By.xpath(personalRegistrationXpath));
        personalRegistrationButton.click();

        String name = "Oto";
        String lastName = "Katsadze";
        String email = "katsadzeoto!gmail.com";
        String phoneNumber = "597017652";
        String password = "Qwerty123";

        WebElement firstNameInput = webDriver.findElement(By.id("pFirstName"));
        WebElement lastNameInput = webDriver.findElement(By.id("pLastName"));
        WebElement emailInput = webDriver.findElement(By.id("pEmail"));
        WebElement phoneInput = webDriver.findElement(By.id("pPhone"));
        WebElement dateBirth = webDriver.findElement(By.id("pDateBirth"));
        Select drpGender = new Select(webDriver.findElement(By.id("pGender")));
        WebElement passwordInput = webDriver.findElement(By.id("pPassword"));
        WebElement confirmPasswordInput = webDriver.findElement(By.id("pConfirmPassword"));

        firstNameInput.sendKeys(name);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phoneNumber);

        jsScrollToElement(js, dateBirth);
        dateBirthSubmit("06", "28", "2003", dateBirth);

        drpGender.selectByVisibleText("კაცი");
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);

        WebElement agreementButton = webDriver.findElement(By.id("pIsAgreeTerns"));
        jsScrollToElement(js, agreementButton);
        agreementButton.click();

        WebElement finishButton = webDriver.findElement(By.xpath("//a[@onclick='checkPhysicalFormSubmit()']"));
        jsScrollToElement(js, finishButton);
        finishButton.click();

        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("physicalInfoMassage")));
        WebElement errorText = webDriver.findElement(By.id("physicalInfoMassage"));
        Assert.assertEquals(errorText.getText(), "მეილის ფორმატი არასწორია!");

    }

    public void dateBirthSubmit(String month, String day, String year, WebElement dateBirth){
        dateBirth.click();

        dateBirth.sendKeys(month);
        dateBirth.sendKeys(day);
        dateBirth.sendKeys(year);
    }
}
