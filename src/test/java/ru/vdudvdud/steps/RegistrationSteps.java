package ru.vdudvdud.steps;

import io.qameta.allure.Step;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.objects.vdudvdud.pages.SignUpPage;

public class RegistrationSteps {
    private SignUpPage signUpPage;


    public RegistrationSteps() {
        signUpPage = new SignUpPage();
    }

    @Step("Заполнение данных о пользователе на странице регистрации")
    public void fillRegistrationData(User user) {
        signUpPage.fillName(user.getFirstName());
        signUpPage.fillSurname(user.getSurname());
        signUpPage.fillPassword(user.getPassword());
        signUpPage.fillRepeatPassword(user.getPassword());
        signUpPage.fillEmail(user.getEmail());
    }

    @Step("Подтверждения введенных данных в форму регистрации")
    public void sendRegistrationData() {
        signUpPage.confirmSignUp();
    }

    @Step("Ожидание, пока кнопка регистрации не пропадет с экрана")
    public void waitUntilSignUpBtnInvisible() {
        signUpPage.waitUntilSignUpBtnInvisible();
    }

    @Step("Ожидание, присутсвия кнопки регистрации на странице")
    public void waitUntilSignUpBtnVisible() {
        signUpPage.waitUntilSignUpBtnVisible();
    }
}
