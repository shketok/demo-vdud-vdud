package ru.vdudvdud.testdata.builders;

import ru.vdudvdud.adaptors.selenide.Configuration;
import ru.vdudvdud.adaptors.selenide.utils.JSONUtils;
import ru.vdudvdud.testdata.constants.StringConstants;
import ru.vdudvdud.testdata.enums.PhoneTemplates;
import ru.vdudvdud.testdata.enums.UserAliases;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.utils.TestDataProvider;

public class UsersFactory {

    private static final String USERS_JSON = "users.json";

    public static User getRandomUser(User user) {
        user.setFirstName(TestDataProvider.generateRandomString(StringConstants.BASE_RANDOM_STRING_LENGTH));
        user.setSurname(TestDataProvider.generateRandomString(StringConstants.BASE_RANDOM_STRING_LENGTH));
        user.setPassword(TestDataProvider.generateRandomString(StringConstants.BASE_RANDOM_STRING_LENGTH));
        user.setEmail(TestDataProvider.generateRandomYopmailEmail());
        user.setPhone(TestDataProvider.generateRandomPhone(PhoneTemplates.RUSSIAN));
        return user;
    }

    public static User getUser(UserAliases userName) {
        return JSONUtils.mapToObject(Configuration.getInstance().getTestDataPath(USERS_JSON), userName.getValue(), User.class);
    }


}
