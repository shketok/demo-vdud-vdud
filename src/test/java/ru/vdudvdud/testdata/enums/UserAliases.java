package ru.vdudvdud.testdata.enums;

public enum UserAliases {
    USER_INCORRECT_NAME("userIncorrectName"),
    USER_INCORRECT_SURNAME("userIncorrectSurname"),
    USER_INCORRECT_EMAIL("userIncorrectEmail"),
    USER_SO_LONG_EMAIL("userSoLongEmail"),
    USER_SHORT_PASSWORD("userShortPassword"),
    USER_EMPTY_PASSWORD("userEmptyPassword"),
    USER_EMPTY_PASSWORD_AND_EMAIL("userEmptyEmailAndPassword");

    private final String value;

    UserAliases(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
