package ru.vdudvdud.testdata.utils;

import org.apache.commons.lang3.RandomStringUtils;
import ru.vdudvdud.adaptors.selenide.Configuration;
import ru.vdudvdud.testdata.constants.DateTimePatterns;
import ru.vdudvdud.testdata.constants.StringConstants;
import ru.vdudvdud.testdata.enums.PhoneTemplates;

import static java.lang.String.format;

public class TestDataProvider {

    public static String generateRandomYopmailEmail() {
        return generateRandomEmailInternal(Configuration.getInstance().getProperty("yopmail.email.template"));
    }

    private static String generateRandomEmailInternal(String emailTemplate) {
        return format(emailTemplate, generateRandomString(StringConstants.BASE_RANDOM_STRING_LENGTH));
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length).toLowerCase();
    }

    public static String generateRandomString() {
        return generateRandomString(StringConstants.BASE_RANDOM_STRING_LENGTH);
    }

    public static String generateCurrentTimeStamp() {
        return new java.text.SimpleDateFormat(DateTimePatterns.TIMESTAMP_PATTERN).format(new java.util.Date());
    }

    public static String generateRandomPhone(PhoneTemplates phone) {
        return phone.getCode() + TestDataProvider.generateRandomString(phone.getPhoneLength());
    }
}
