package Tests.repositories;

import GameShop.java.models.Console;
import GameShop.java.models.Game;
import GameShop.java.models.enums.ConsoleForm;
import GameShop.java.repositories.GameRepository;
import org.junit.jupiter.api.*;

class GameRepositoryTest {
    GameRepository repo = new GameRepository();
    Console console1 = new Console("Test Console", ConsoleForm.STANDARD, 15, 8, false);
    Game game1 = new Game("Test 1", this.console1, 10, false);
    Game game2 = new Game("Test 2", this.console1, 10, false);
    Game game3 = new Game("Test 3", this.console1, 10, true);

    Console console2 = new Console("Test Console 2", ConsoleForm.STANDARD, 15, 8, false);
    Game game4 = new Game("Test 4", this.console2, 10, false);
    Game game5 = new Game("Test 5", this.console2, 10, true);

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        this.repo.getAllGames().clear();
        // test clear worked on games before running other tests
        Assertions.assertEquals(0, repo.getAllGames().size());
    }

    @Test
    void getAllGamesReturnsCorrectNumberOfGames() {
        Assertions.assertEquals(0, repo.getAllGames().size());

        this.repo.addGame(this.game1);
        Assertions.assertEquals(1, repo.getAllGames().size());
    }

    @Test
    void getAvailableGamesReturnsCorrectNumberOfGames() {
        Assertions.assertEquals(0, repo.getAvailableGames().size());

        this.repo.addGame(this.game1);
        // unavailable console
        this.repo.addGame(this.game3);
        Assertions.assertEquals(1, repo.getAvailableGames().size());
    }

    @Test
    void getAllGamesForConsoleReturnsCorrectSize() {
        this.repo.addGame(this.game4);
        this.repo.addGame(this.game5);

        Assertions.assertEquals(2, this.repo.getAllGamesForConsole(this.console2).size());
    }

    @Test
    void getAllGamesForConsoleWithGamesFromDifferentConsolesReturnsCorrectSize() {
        // non testing console games
        this.repo.addGame(this.game1);
        this.repo.addGame(this.game2);

        // testing console games
        this.repo.addGame(this.game4);
        this.repo.addGame(this.game5);

        Assertions.assertEquals(2, this.repo.getAllGamesForConsole(this.console2).size());
    }

    @Test
    void getAvailableGamesForConsoleReturnsCorrectSize() {
        this.repo.addGame(this.game4);
        this.repo.addGame(this.game5);

        Assertions.assertEquals(1, this.repo.getAvailableGamesForConsole(this.console2).size());
    }

    @Test
    void getAvailableGamesForConsoleWithGamesFromDifferentConsolesReturnsCorrectSize() {
        // non testing console games
        this.repo.addGame(this.game1);
        this.repo.addGame(this.game2);

        // testing console games
        this.repo.addGame(this.game4);
        this.repo.addGame(this.game5);

        Assertions.assertEquals(1, this.repo.getAvailableGamesForConsole(this.console2).size());
    }

    @Test
    void getByIdReturnsCorrectGame() {
        this.repo.addGame(this.game1);

        String gameId = this.game1.getId();
        Assertions.assertEquals(this.repo.getById(gameId), this.game1);
    }

    @Test
    void getByIdReturnsNullIfWrongId() {
        this.repo.addGame(this.game1);

        String wrongId = this.game2.getId();

        Assertions.assertEquals(null, this.repo.getById(wrongId));
    }

    @Test
    void addGameWorks() {
        this.repo.addGame(this.game1);
        Assertions.assertEquals(1, this.repo.getAllGames().size());
    }

    @Test
    void removeGameWorks() {
        this.repo.addGame(this.game1);
        this.repo.addGame(this.game2);
        // ensure both games got added
        Assertions.assertEquals(2, this.repo.getAllGames().size());

        this.repo.removeGame(this.game1);
        String expectedGameId = this.game2.getId();
        Assertions.assertEquals(this.repo.getById(expectedGameId), this.game2);
    }

    @Test
    void changingAGameNameWorks() {
        this.repo.addGame(this.game1);
        this.game1.setName("new name");

        this.repo.modifyGame(this.game1);
        Assertions.assertEquals("new name", this.repo.getById(this.game1.getId()).getName());
    }

    @Test
    void changingAGameRepairStatusWorks() {
        this.repo.addGame(this.game1);
        this.game1.setInForRepair(true);

        this.repo.modifyGame(this.game1);
        Assertions.assertEquals(true, this.repo.getById(this.game1.getId()).isInForRepair());
    }
}