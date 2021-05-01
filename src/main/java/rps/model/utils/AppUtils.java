package rps.model.utils;

import java.util.UUID;

public class AppUtils {

    public static String createNewId() {
        return UUID.randomUUID().toString();
    }
}
