package ru.vdudvdud.testdata.enums;

/**
 * Шаблоны регулярных выражений.
 */
public enum RegexPatterns {
    DIGITS("\\d+"),
    NON_DIGITS("[^\\d]"),
    SPACES("\\s+"),
    ITEM_PRICE("X\\s([\\d\\s]+)");

    private String regex;

    RegexPatterns(String regex) {
        this.regex = regex;
    }

    public String toString() {
        return regex;
    }
}

