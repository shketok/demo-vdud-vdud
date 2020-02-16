package ru.vdudvdud.adaptors.selenide.conditions.collection;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ex.ListSizeMismatch;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StopAppearNew extends CollectionCondition {
    private long currentCountOfElements = 0;

    @Override
    public void fail(WebElementsCollection webElementsCollection, List<WebElement> elements, Exception lastError, long timeoutMs) {
        throw new ListSizeMismatch(webElementsCollection.driver(), ">=", 0, this.explanation, webElementsCollection, elements, lastError, timeoutMs);
    }

    @Override
    public boolean applyNull() {
        return false;
    }

    @Override
    public boolean apply(List<WebElement> webElements) {
        boolean isCountOfElementsNotChanged;
        isCountOfElementsNotChanged = currentCountOfElements == webElements.size();
        currentCountOfElements = webElements.size();
        return isCountOfElementsNotChanged;
    }

}
