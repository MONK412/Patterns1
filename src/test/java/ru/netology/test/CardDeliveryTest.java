package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.MainPage;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        var mainPage = open("http://localhost:9999", MainPage.class);
        mainPage.register();
        mainPage.successNotification.shouldBe(visible)
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));
        mainPage.changeDate();
        mainPage.plan();
        mainPage.replanNotification.shouldBe(visible);
        mainPage.replanButton.click();
        mainPage.successNotification.shouldBe(visible)
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));
    }
}
