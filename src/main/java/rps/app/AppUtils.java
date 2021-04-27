package rps.app;

import java.util.UUID;

public class AppUtils {

    public static String generateRandomID() {
        return UUID.randomUUID().toString();
    }
}
