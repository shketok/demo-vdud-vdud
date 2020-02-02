package ru.vdudvdud.testdata.builders;

import ru.vdudvdud.testdata.enums.Phones;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.utils.TestDataProvider;

public class Users {

    public static User getRandomUser(User user) {
        final int randomStringLength = 9;
        user.setFirstName(TestDataProvider.generateRandomString(randomStringLength));
        user.setSurname(TestDataProvider.generateRandomString(randomStringLength));
        user.setPassword(TestDataProvider.generateRandomString(randomStringLength));
        user.setEmail(TestDataProvider.generateRandomYopmailEmail());
        user.setPhone(TestDataProvider.generateRandomPhone(Phones.RUSSIAN));
        return user;
    }
}
