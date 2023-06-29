package com.badbones69.crazycrates.api.utils;

public class MiscUtils {

    public static String convertLegacyPlaceholders(String message) {
        return message
                .replaceAll("%command%", "{command}")
                .replaceAll("%crate%", "{crate}")
                .replaceAll("%key%", "{key}")
                .replaceAll("%usage%", "{usage}")
                .replaceAll("%prize%", "{prize}")
                .replaceAll("%player%", "{player}")
                .replaceAll("%amount%", "{amount}")
                .replaceAll("%world%", "{world}")
                .replaceAll("%id%", "{id}")
                .replaceAll("%number%", "{number}")
                .replaceAll("%cratetype%", "{cratetype}")
                .replaceAll("%keys%", "{keys}");
    }
}