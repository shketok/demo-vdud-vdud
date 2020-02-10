package ru.vdudvdud.adaptors.selenide.utils;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.ElementShouldNot;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebElement;
import ru.vdudvdud.adaptors.selenide.driver.DriverFactory;

/**
 * Класс с умными ожиданиями.
 * Класс имеет смысл использовать в случае негативных проверок, когда мы знаем, что чего-то не должно появиться,
 * так как классическое ожидание в случае, если элемент не видим в данный момент, сразу вернет истину, хотя он может появиться
 * спустя некоторое время.
 *
 * Класс также имеет место быть для остальных сложных ожиданий, например ожидание прогрузки всех элементов одного типа на странице.
 *
 * Использование подобных ожиданий уместно только в случае негативных проверок, когда мы ожидаем, что следующая страница не открыта.
 * В случае позитивной проверки следует дожидаться как раз элементов со следующей страницы.
 */
public class SmartWait {

    /**
     * Функция ожидания, что элемент присутствует в течении длительного времени.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Тип ожидания.
     * @return True - элемент присутствует в течении длительного времени. False - элемент не появился на протяжении времени, хотя ожидали присутствие.
     */
    public static boolean isElement(SelenideElement selenideElement, Condition condition) {
        try {
            selenideElement.shouldNotBe(condition);
        } catch (ElementShouldNot ex) {
            Logger.getInstance().info(String.format("Element %s is %s", selenideElement.toString(), condition.toString()));
        }
        return selenideElement.is(condition);
    }
}

