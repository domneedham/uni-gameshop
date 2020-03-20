package GameShop.java.models.adaptors;

import GameShop.java.controllers.EditConsoleController;
import GameShop.java.models.Console;

public class EditConsoleAdaptor {
    public static void getConsole(Console console, EditConsoleController controller) {
        controller.consoleDetailsToEdit(console.getId(), console.getName(), console.isInForRepair());
    }
}
