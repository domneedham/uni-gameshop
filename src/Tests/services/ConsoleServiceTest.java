package Tests.services;

import GameShop.java.models.Console;
import GameShop.java.services.ConsoleService;
import Tests.TestData;
import org.junit.jupiter.api.*;

class ConsoleServiceTest {
    final TestData testData = new TestData();
    final ConsoleService consoleService = new ConsoleService();

    final Console console1 = testData.standardConsole1;
    final Console console2 = testData.standardConsole2;
    final Console console3 = testData.nonStandardConsole1;

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        consoleService.getAllConsoles().clear();
        // test clear worked on consoles before running other tests
        Assertions.assertEquals(0, consoleService.getAllConsoles().size());
    }

    @Test
    void getAllConsolesReturnsCorrectNumberOfConsoles() {
        consoleService.addConsole(this.console1);
        Assertions.assertEquals(1, consoleService.getAllConsoles().size());
    }

    @Test
    void getAvailableConsolesReturnsCorrectNumberOfConsoles() {
        consoleService.addConsole(this.console1);
        // unavailable console
        consoleService.addConsole(this.console3);
        Assertions.assertEquals(1, consoleService.getAvailableConsoles().size());
    }

    @Test
    void getByIdReturnsCorrectConsole() {
        consoleService.addConsole(this.console1);

        String consoleId = this.console1.getId();
        Assertions.assertEquals(consoleService.getById(consoleId), this.console1);
    }

    @Test
    void getByIdReturnsNullIfWrongId() {
        consoleService.addConsole(this.console1);

        String wrongId = this.console2.getId();

        Assertions.assertNull(consoleService.getById(wrongId));
    }

    @Test
    void idExistsReturnsTrueIfCorrect() {
        consoleService.addConsole(this.console1);

        boolean result = consoleService.idExists(this.console1.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void idExistsReturnsFalseIfNotCorrect() {
        consoleService.addConsole(this.console1);

        boolean result = consoleService.idExists(this.console2.getId());
        Assertions.assertFalse(result);
    }

    @Test
    void addConsoleWorks() {
        consoleService.addConsole(this.console1);
        Assertions.assertEquals(1, consoleService.getAllConsoles().size());
    }

    @Test
    void removeConsoleWorks() {
        consoleService.addConsole(this.console1);
        consoleService.addConsole(this.console2);
        // ensure both consoles got added
        Assertions.assertEquals(2, consoleService.getAllConsoles().size());

        consoleService.removeConsole(this.console1);
        String expectedConsoleId = this.console2.getId();
        Assertions.assertEquals(consoleService.getById(expectedConsoleId), this.console2);
    }

    @Test
    void changingAConsoleNameWorks() {
        consoleService.addConsole(this.console1);

        consoleService.modifyConsole(this.console1, "new name", false);
        Assertions.assertEquals("new name", consoleService.getById(this.console1.getId()).getName());
    }

    @Test
    void changingAConsoleRepairStatusWorks() {
        consoleService.addConsole(this.console1);

        consoleService.modifyConsole(this.console1, "new name", true);
        Assertions.assertTrue(consoleService.getById(this.console1.getId()).isInForRepair());
    }
}