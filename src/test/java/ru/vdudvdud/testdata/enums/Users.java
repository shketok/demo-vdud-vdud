package ru.vdudvdud.testdata.enums;

public enum Users {
    USER_INCORRECT_NAME("userIncorrectName"),
    USER_INCORRECT_SURNAME("userIncorrectSurname"),
    USER_INCORRECT_EMAIL("userIncorrectEmail"),
    USER_SO_LONG_EMAIL("userSoLongEmail"),
    USER_SHORT_PASSWORD("userShortPassword"),
    USER_EMPTY_PASSWORD("userEmptyPassword");

    private String value;

    Users(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
