package ru.vdudvdud.testdata.enums;

public enum CommonDataNames {
    LOGIN("login"),
    PASSWORD("password"),
    AUTH("auth");

    private String name;

    CommonDataNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
