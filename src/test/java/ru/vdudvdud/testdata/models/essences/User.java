package ru.vdudvdud.testdata.models.essences;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phone;

    public void fillUserEmailTemplate(String emailAppendablePart) {
        setEmail(String.format(getEmail(), emailAppendablePart));
    }
}
