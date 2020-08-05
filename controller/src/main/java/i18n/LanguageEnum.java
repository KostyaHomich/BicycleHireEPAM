package i18n;

import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

public enum LanguageEnum {
    BY,
    RU,
    EN;

    public static Optional<LanguageEnum> fromString(String value) {
        return Stream.of(values()).filter(e -> e.name().equalsIgnoreCase(value)).findFirst();
    }

    public static Locale getLocaleByEnum(LanguageEnum value) {
        switch (value) {
            case BY:
                return new Locale("by", "BY");

            case RU:
                return new Locale("ru", "RU");

            default:
                return new Locale("en", "US");
        }
    }

    public static String getResourceBundleNameFromEnum(LanguageEnum value) {
        switch (value) {
            case BY:
                return "text_by_BY";

            case RU:
                return "text_ru_RU";

            default:
                return "text_en_US";
        }
    }
}