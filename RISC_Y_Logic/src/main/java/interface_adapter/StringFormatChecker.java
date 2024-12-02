package interface_adapter;

import java.util.Map;

public class StringFormatChecker {
    private static final Map<String, String> fromats = Map.of(
            "date", "^[0-9]{4}-[0-9]{2}-[0-9]{2}$",
            "time_slot", "^[0-9]{2}:[0-9]{2}~[0-9]{2}:[0-9]{2}$",
            "time", "^[0-9]{2}:[0-9]{2}$"
    );

    public static boolean checkString(String format, String input) {
        return !input.matches(fromats.get(format));
    }
}
