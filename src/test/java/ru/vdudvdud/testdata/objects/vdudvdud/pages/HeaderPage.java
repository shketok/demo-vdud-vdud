package ru.vdudvdud.testdata.objects.vdudvdud.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.objects.vdudvdud.forms.PersonalAreaDropdownForm;

import static com.codeborne.selenide.Selenide.$;

public class HeaderPage extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $("a.logo");

    private static final SelenideElement PERSONAL_AREA = $("[class*='top-bar'] .info-settings div > .icon-user");

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public PersonalAreaDropdownForm getPersonalAreaDropDownForm() {
        return new PersonalAreaDropdownForm();
    }


    public void hoverPersonalArea() {
        PERSONAL_AREA.shouldBe(Condition.visible).hover();
    }
}
