package ru.vdudvdud.localization;

public class YopmailLocalizationProvider extends AbstractLocalizationProvider {

    private static final String locFilename = "Labels.labels";

    public YopmailLocalizationProvider() {
        super(locFilename);
    }
    ////ResourceBundle.getBundle("localization.labels.Labels", Locale.US).getString("labelTest");


}
