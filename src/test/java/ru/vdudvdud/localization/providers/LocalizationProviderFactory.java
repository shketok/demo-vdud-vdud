package ru.vdudvdud.localization.providers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalizationProviderFactory {

    private static Map<String, AbstractLocalizationProvider> providers = new ConcurrentHashMap<>();

    public static AbstractLocalizationProvider getProvider(String fileName) {
        return providers.computeIfAbsent(fileName, v -> new AbstractLocalizationProvider(v) {
            // default
        });
    }
}
