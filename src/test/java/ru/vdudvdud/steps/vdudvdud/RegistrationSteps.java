package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.Assert;
import ru.vdudvdud.page.objects.vdudvdud.pages.SignUpPage;
import ru.vdudvdud.testdata.models.essences.User;

public class RegistrationSteps extends BaseSteps {
    private SignUpPage signUpPage = new SignUpPage();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        signUpPage.checkThatFirstNameInState(Condition.visible);
        signUpPage.checkThatLastNameInState(Condition.visible);
        signUpPage.checkThatPasswordInState(Condition.visible);
        signUpPage.checkThatConfirmPasswordInState(Condition.visible);
        signUpPage.checkThatEmailInState(Condition.visible);
        signUpPage.checkThatSignUpButtonInState(Condition.visible);
    }

    @Step("Заполнение данных о пользователе на странице регистрации")
    public void fillRegistrationData(User user) {
        signUpPage.fillName(user.getFirstName());
        signUpPage.fillSurname(user.getSurname());
        signUpPage.fillPassword(user.getPassword());
        signUpPage.fillRepeatPassword(user.getPassword());
        signUpPage.fillEmail(user.getEmail());
    }

    @Step("Заполнение поля повтора пароля")
    public void fillRepeatPassword(String userPassword) {
        signUpPage.fillRepeatPassword(userPassword);
    }

    @Step("Подтверждения введенных данных в форму регистрации")
    public void sendRegistrationData() {
        signUpPage.confirmSignUp();
    }

    @Step("Ожидание, присутсвия кнопки регистрации на странице")
    public void waitUntilSignUpBtnVisible() {
        Assert.assertTrue(signUpPage.isSignUpBtnVisible(), "Кнопка регистрации не видна");
    }
}
