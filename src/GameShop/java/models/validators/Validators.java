package GameShop.java.models.validators;

public class Validators {
    public static boolean isNull(String value) {
        return value == null;
    }

    public static boolean isBlank(String value) {
        return value.isBlank();
    }
}
