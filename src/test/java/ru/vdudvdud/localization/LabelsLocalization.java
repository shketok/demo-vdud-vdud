package ru.vdudvdud.localization;

import java.util.Locale;
import lombok.NonNull;
import ru.vdudvdud.localization.providers.LabelsLocalizationProvider;

public enum LabelsLocalization implements LocalizedValue {
    CART_CONFIRMATION_FORM_PRODUCTS_COST("cart.confirmation_form.products_cost"),
    CART_CONFIRMATION_FORM_DELIVERY_COST("cart.confirmation_form.delivery_cost"),
    CART_CONFIRMATION_FORM_TOTAL_EXCLUDE_DELIVERY("cart.confirmation_form.total_exclude_delivery"),
    SIGN_IN_LOGIN_IS_MANDATORY_MESSAGE("sign_in.login_is_mandatory_message"),
    SIGN_IN_PASSWORD_IS_MANDATORY_MESSAGE("sign_in.password_is_mandatory_message"),
    SIGN_IN_EITHER_LOGIN_OR_PASSWORD_IS_INCORRECT("sign_in.either_login_or_password_is_incorrect"),
    RESTORE_PASSWORD_RESTORE_FORM_INVALID_EMAIL_MESSAGE("restore_password.restore_form.invalid_email_message"),
    RESTORE_PASSWORD_RESTORE_FORM_DIFFERENT_PASSWORD_MESSAGE(
        "restore_password.restore_form.different_passwords_message"),
    RESTORE_PASSWORD_RESTORE_FORM_EMPTY_PASSWORD_MESSAGE("restore_password.restore_form.empty_passwords_message");

    private static final LabelsLocalizationProvider labelsLocalizationProvider = new LabelsLocalizationProvider(
        "labels.Labels");

    private final String key;

    LabelsLocalization(@NonNull String key) {
        this.key = key;
    }

    @Override
    public String i18n() {
        return labelsLocalizationProvider.getLocalization(key);
    }

    @Override
    public String i18n(@NonNull Locale locale) {
        return labelsLocalizationProvider.getLocalization(key, locale);
    }
}
