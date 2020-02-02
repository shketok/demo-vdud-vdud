package ru.vdudvdud.testdata.utils;

import org.apache.commons.lang3.RandomStringUtils;
import ru.vdudvdud.adaptors.selenide.Configuration;
import ru.vdudvdud.testdata.constants.DateTimePatterns;
import ru.vdudvdud.testdata.enums.Phones;

import static java.lang.String.format;

public class TestDataProvider {

    public static String generateRandomYopmailEmail() {
        return generateRandomEmailInternal(Configuration.getInstance().getProperty("yopmail.email.template"));
    }

    private static String generateRandomEmailInternal(String emailTemplate) {
        final int randomPartLength = 9;
        return format(emailTemplate, generateRandomString(randomPartLength));
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length).toLowerCase();
    }

    public static String generateTimeStamp() {
        return new java.text.SimpleDateFormat(DateTimePatterns.TIMESTAMP_PATTERN).format(new java.util.Date());
    }

    public static String generateRandomPhone(Phones phone) {
        return phone.getCode() + TestDataProvider.generateRandomString(phone.getPhoneLength());
    }
}
