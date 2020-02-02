package ru.vdudvdud.testdata.enums;

import ru.vdudvdud.testdata.utils.TestDataProvider;

public enum Phones {
    RUSSIAN("+7", 10);

    private String code;
    private Integer phoneLength;

    Phones(String code, Integer phoneLength) {
        this.code = code;
        this.phoneLength = phoneLength;
    }

    public String getCode() {
        return code;
    }

    public Integer getPhoneLength() {
        return phoneLength;
    }

}
