package com.ua.fishingforum.marks.util;

public class UrlUtils {
    public static String validateParam(String address) {
        if (address.contains(" ")) {
            return address.replace(" ", "%20");
        }
        return address;
    }
}
