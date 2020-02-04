package ru.vdudvdud.testdata.enums;

public enum PhoneTemplates {
    RUSSIAN("+7", 10);

    private String code;
    private Integer phoneLength;

    PhoneTemplates(String code, Integer phoneLength) {
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
