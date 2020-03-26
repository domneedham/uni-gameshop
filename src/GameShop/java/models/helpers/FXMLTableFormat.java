package GameShop.java.models.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FXMLTableFormat {
    public static String formatInt(int x) {
        return String.valueOf(x);
    }

    public static String formatString(String x) {
        if (x != null) {
            return x;
        }
        return "Unavailable";
    }

    public static String formatBoolean(boolean x) {
        return x ? "Yes" : "No";
    }

    public static String formatEnum(Enum x) {
        // Capitalises first word only instead of all caps like from enum
        String form = x.toString().toLowerCase();
        return form.substring(0, 1).toUpperCase() + form.substring(1);
    }

    public static String formatCost(double x) {
        return String.format("£%.2f", x);
    }

    public static String formatDate(LocalDate x) {
        return x.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
    }

    public static String formatArrayList(ArrayList x) {
        return x.toString();
    }
}
