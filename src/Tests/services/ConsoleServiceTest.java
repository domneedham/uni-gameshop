package Tests.services;

import GameShop.java.models.Console;
import GameShop.java.models.enums.ConsoleForm;
import GameShop.java.services.ConsoleService;
import org.junit.jupiter.api.*;

class ConsoleServiceTest {
    Console console1 = new Console("Test 1", ConsoleForm.STANDARD, 15, 8, false);
    Console console2 = new Console("Test 2", ConsoleForm.STANDARD, 15, 8, false);
    Console console3 = new Console("Test 3", ConsoleForm.STANDARD, 15, 8, true);

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        ConsoleService.getAllConsoles().clear();
        // test clear worked on consoles before running other tests
        Assertions.assertEquals(0, ConsoleService.getAllConsoles().size());
    }

    @Test
    void getAllConsolesReturnsCorrectNumberOfConsoles() {
        Assertions.assertEquals(0, ConsoleService.getAllConsoles().size());

        ConsoleService.addConsole(this.console1);
        Assertions.assertEquals(1, ConsoleService.getAllConsoles().size());
    }

    @Test
    void getAvailableConsolesReturnsCorrectNumberOfConsoles() {
        Assertions.assertEquals(0, ConsoleService.getAvailableConsoles().size());

        ConsoleService.addConsole(this.console1);
        // unavailable console
        ConsoleService.addConsole(this.console3);
        Assertions.assertEquals(1, ConsoleService.getAvailableConsoles().size());
    }

    @Test
    void getByIdReturnsCorrectConsole() {
        ConsoleService.addConsole(this.console1);

        String consoleId = this.console1.getId();
        Assertions.assertEquals(ConsoleService.getById(consoleId), this.console1);
    }

    @Test
    void getByIdReturnsNullIfWrongId() {
        ConsoleService.addConsole(this.console1);

        String wrongId = this.console2.getId();

        Assertions.assertEquals(null, ConsoleService.getById(wrongId));
    }

    @Test
    void idExistsReturnsTrueIfCorrect() {
        ConsoleService.addConsole(this.console1);

        boolean result = ConsoleService.idExists(this.console1.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void idExistsReturnsFalseIfNotCorrect() {
        ConsoleService.addConsole(this.console1);

        boolean result = ConsoleService.idExists(this.console2.getId());
        Assertions.assertFalse(result);
    }

    @Test
    void addConsoleWorks() {
        ConsoleService.addConsole(this.console1);
        Assertions.assertEquals(1, ConsoleService.getAllConsoles().size());
    }

    @Test
    void removeConsoleWorks() {
        ConsoleService.addConsole(this.console1);
        ConsoleService.addConsole(this.console2);
        // ensure both consoles got added
        Assertions.assertEquals(2, ConsoleService.getAllConsoles().size());

        ConsoleService.removeConsole(this.console1);
        String expectedConsoleId = this.console2.getId();
        Assertions.assertEquals(ConsoleService.getById(expectedConsoleId), this.console2);
    }

    @Test
    void changingAConsoleNameWorks() {
        ConsoleService.addConsole(this.console1);

        ConsoleService.modifyConsole(this.console1, "new name", false);
        Assertions.assertEquals("new name", ConsoleService.getById(this.console1.getId()).getName());
    }

    @Test
    void changingAConsoleRepairStatusWorks() {
        ConsoleService.addConsole(this.console1);

        ConsoleService.modifyConsole(this.console1, "new name", true);
        Assertions.assertEquals(true, ConsoleService.getById(this.console1.getId()).isInForRepair());
    }
}