package ru.vdudvdud;

import java.util.Collections;
import ru.vdudvdud.localization.YopmailLocalizationProvider;


public class Test {
    private static final YopmailLocalizationProvider yomail = new YopmailLocalizationProvider();
    public static void main(String[] args) {
        yomail.getLocalization("labelTest"); // todo to enum ?

        try {
            test();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void test()
    {
        Collections.singletonList(new Object()).forEach(t -> {
            throw new RuntimeException();
        });
    }
}
