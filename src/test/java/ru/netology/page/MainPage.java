package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public SelenideElement city = $("[data-test-id='city'] input");
    public SelenideElement date = $("[data-test-id='date'] input");
    public SelenideElement name = $("[data-test-id='name'] input");
    public SelenideElement phone = $("[data-test-id='phone'] input");
    public SelenideElement agreement = $("[data-test-id='agreement']");
    public SelenideElement planningButton = $x("//span[@class='button__text']");
    public SelenideElement successNotification = $("[data-test-id='success-notification']");
    public SelenideElement replanNotification = $("[data-test-id='replan-notification'");
    public SelenideElement replanButton = $(byText("Перепланировать"));

    public void register() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        city.setValue(validUser.getCity());
        registerDate();
        name.setValue(validUser.getName());
        phone.setValue(validUser.getPhone());
        agreement.click();
        plan();
    }

    public void plan() {
        planningButton.click();
    }

    public void registerDate() {
        date.sendKeys(Keys.CONTROL + "A");
        date.sendKeys(Keys.BACK_SPACE);
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        date.setValue(firstMeetingDate);
    }

    public void changeDate() {
        date.sendKeys(Keys.CONTROL + "A");
        date.sendKeys(Keys.BACK_SPACE);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        date.setValue(secondMeetingDate);
    }
}