package Tests.repositories;

import GameShop.java.models.Console;
import GameShop.java.models.enums.ConsoleForm;
import GameShop.java.repositories.ConsoleRepository;
import org.junit.jupiter.api.*;

class ConsoleRepositoryTest {
    ConsoleRepository repo = new ConsoleRepository();
    Console console1 = new Console("Test 1", ConsoleForm.STANDARD, 15, 8, false);
    Console console2 = new Console("Test 2", ConsoleForm.STANDARD, 15, 8, false);
    Console console3 = new Console("Test 3", ConsoleForm.STANDARD, 15, 8, true);

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        this.repo.getAllConsoles().clear();
        // test clear worked on consoles before running other tests
        Assertions.assertEquals(0, repo.getAllConsoles().size());
    }

    @Test
    void getAllConsolesReturnsCorrectNumberOfConsoles() {
        Assertions.assertEquals(0, repo.getAllConsoles().size());

        this.repo.addConsole(this.console1);
        Assertions.assertEquals(1, repo.getAllConsoles().size());
    }

    @Test
    void getAvailableConsolesReturnsCorrectNumberOfConsoles() {
        Assertions.assertEquals(0, repo.getAvailableConsoles().size());

        this.repo.addConsole(this.console1);
        // unavailable console
        this.repo.addConsole(this.console3);
        Assertions.assertEquals(1, repo.getAvailableConsoles().size());
    }

    @Test
    void getByIdReturnsCorrectConsole() {
        this.repo.addConsole(this.console1);

        String consoleId = this.console1.getId();
        Assertions.assertEquals(this.repo.getById(consoleId), this.console1);
    }

    @Test
    void getByIdReturnsNullIfWrongId() {
        this.repo.addConsole(this.console1);

        String wrongId = this.console2.getId();

        Assertions.assertEquals(null, this.repo.getById(wrongId));
    }

    @Test
    void addConsoleWorks() {
        this.repo.addConsole(this.console1);
        Assertions.assertEquals(1, this.repo.getAllConsoles().size());
    }

    @Test
    void removeConsoleWorks() {
        this.repo.addConsole(this.console1);
        this.repo.addConsole(this.console2);
        // ensure both consoles got added
        Assertions.assertEquals(2, this.repo.getAllConsoles().size());

        this.repo.removeConsole(this.console1);
        String expectedConsoleId = this.console2.getId();
        Assertions.assertEquals(this.repo.getById(expectedConsoleId), this.console2);
    }

    @Test
    void changingAConsoleNameWorks() {
        this.repo.addConsole(this.console1);
        this.console1.setName("new name");

        this.repo.modifyConsole(this.console1);
        Assertions.assertEquals("new name", this.repo.getById(this.console1.getId()).getName());
    }

    @Test
    void changingAConsoleRepairStatusWorks() {
        this.repo.addConsole(this.console1);
        this.console1.setInForRepair(true);

        this.repo.modifyConsole(this.console1);
        Assertions.assertEquals(true, this.repo.getById(this.console1.getId()).isInForRepair());
    }
}