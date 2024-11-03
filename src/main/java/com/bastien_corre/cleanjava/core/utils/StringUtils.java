package com.bastien_corre.cleanjava.core.utils;

import io.micrometer.common.lang.Nullable;

import java.util.function.Supplier;

public class StringUtils {

    public static String requireNonNullNorBlankElse(String string, String defaultValue) {
        return isBlank(string) ? defaultValue : string;
    }

    public static String requireNonNullNorBlankElseThrow(String string, Supplier<? extends RuntimeException> exceptionSupplier) {
        if (isBlank(string)) {
            throw exceptionSupplier.get();
        }
        return string;
    }

    public static boolean isBlank(@Nullable String string) {
        if (isEmpty(string)) {
            return true;
        }

        for(int i = 0; i < string.length(); ++i) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotBlank(@Nullable String string) {
        return !isBlank(string);
    }

    public static boolean isEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable String string) {
        return !isEmpty(string);
    }

    public static String truncate(String string, int maxLength) {
        return string.length() > maxLength ? string.substring(0, maxLength) : string;
    }

    public static String truncate(String string, int maxLength, String truncationIndicator) {
        if (truncationIndicator.length() >= maxLength) {
            throw new IllegalArgumentException("maxLength must be greater than length of truncationIndicator");
        } else if (string.length() > maxLength) {
            int remainingLength = maxLength - truncationIndicator.length();
            return string.substring(0, remainingLength) + truncationIndicator;
        } else {
            return string;
        }
    }

    private StringUtils() {
    }
}
