package Tests.models.helpers;

import GameShop.java.models.helpers.FXMLTableFormat;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class FXMLTableFormatTest {
    enum HelloWorld {
        HELLO
    }

    @Test
    void formatInt() {
        String result = FXMLTableFormat.formatInt(5);
        Assertions.assertEquals("5", result);
    }

    @Test
    void formatString() {
        String result = FXMLTableFormat.formatString("Hello world");
        Assertions.assertEquals("Hello world", result);
    }

    @Test
    void formatBooleanTrue() {
        String result = FXMLTableFormat.formatBoolean(true);
        Assertions.assertEquals("Yes", result);
    }

    @Test
    void formatBooleanFalse() {
        String result = FXMLTableFormat.formatBoolean(false);
        Assertions.assertEquals("No", result);
    }

    @Test
    void formatEnum() {
        String result = FXMLTableFormat.formatEnum(HelloWorld.HELLO);
        Assertions.assertEquals("Hello", result);
    }

    @Test
    void formatCostNoDecimals() {
        String result = FXMLTableFormat.formatCost(15);
        Assertions.assertEquals("£15.00", result);
    }

    @Test
    void formatCostOneDecimal() {
        String result = FXMLTableFormat.formatCost(15.1);
        Assertions.assertEquals("£15.10", result);
    }

    @Test
    void formatCostTwoDecimals() {
        String result = FXMLTableFormat.formatCost(15.15);
        Assertions.assertEquals("£15.15", result);
    }

    @Test
    void formatCostThreeDecimalsLB() {
        String result = FXMLTableFormat.formatCost(15.153);
        Assertions.assertEquals("£15.15", result);
    }

    @Test
    void formatCostThreeDecimalsMB() {
        String result = FXMLTableFormat.formatCost(15.155);
        Assertions.assertEquals("£15.16", result);
    }

    @Test
    void formatCostThreeDecimalsUB() {
        String result = FXMLTableFormat.formatCost(15.158);
        Assertions.assertEquals("£15.16", result);
    }

    @Test
    void formatDate() {
        LocalDate date = LocalDate.of(2020, 3, 26);
        String result = FXMLTableFormat.formatDate(date);
        Assertions.assertEquals("26-03-20", result);
    }

    @Test
    void formatArrayListInt() {
        ArrayList<Integer> list = new ArrayList<>(List.of(1, 3));
        String result = FXMLTableFormat.formatArrayList(list);
        Assertions.assertEquals("[1, 3]", result);
    }

    @Test
    void formatArrayListString() {
        ArrayList<String> list = new ArrayList<>(List.of("Hello", "World"));
        String result = FXMLTableFormat.formatArrayList(list);
        Assertions.assertEquals("[Hello, World]", result);
    }
}