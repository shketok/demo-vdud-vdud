package ru.vdudvdud.testdata.builders;

import ru.vdudvdud.adaptors.selenide.Configuration;
import ru.vdudvdud.adaptors.selenide.utils.JSONUtils;
import ru.vdudvdud.testdata.enums.Phones;
import ru.vdudvdud.testdata.enums.Users;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.utils.TestDataProvider;

public class UsersBuilder {

    private static final String USERS_JSON = "users.json";

    public static User getRandomUser(User user) {
        final int randomStringLength = 9;
        user.setFirstName(TestDataProvider.generateRandomString(randomStringLength));
        user.setSurname(TestDataProvider.generateRandomString(randomStringLength));
        user.setPassword(TestDataProvider.generateRandomString(randomStringLength));
        user.setEmail(TestDataProvider.generateRandomYopmailEmail());
        user.setPhone(TestDataProvider.generateRandomPhone(Phones.RUSSIAN));
        return user;
    }

    public static User getUser(Users userName) {
        return JSONUtils.mapToObject(Configuration.getInstance().getTestDataPath(USERS_JSON), userName.getValue(), User.class);
    }


}
