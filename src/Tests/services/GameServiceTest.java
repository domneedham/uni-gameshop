package Tests.services;

import GameShop.java.models.Console;
import GameShop.java.models.Game;
import GameShop.java.models.enums.ConsoleForm;
import GameShop.java.services.GameService;
import org.junit.jupiter.api.*;

class GameServiceTest {
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
        GameService.getAllGames().clear();
        // test clear worked on consoles before running other tests
        Assertions.assertEquals(0, GameService.getAllGames().size());
    }

    @Test
    void getAllGamesReturnsCorrectSize() {
        Assertions.assertEquals(0, GameService.getAllGames().size());

        GameService.addGame(this.game1);
        Assertions.assertEquals(1, GameService.getAllGames().size());
    }

    @Test
    void getAvailableGamesReturnsCorrectSize() {
        Assertions.assertEquals(0, GameService.getAvailableGames().size());

        GameService.addGame(this.game1);
        // unavailable game
        GameService.addGame(this.game3);
        Assertions.assertEquals(1, GameService.getAvailableGames().size());
    }

    @Test
    void getAllGamesForConsoleWithGamesFromDifferentConsolesReturnsCorrectSize() {
        // non testing console games
        GameService.addGame(this.game1);
        GameService.addGame(this.game2);

        // testing console games
        GameService.addGame(this.game4);
        GameService.addGame(this.game5);

        Assertions.assertEquals(2, GameService.getAllGamesForConsole(this.console2).size());
    }

    @Test
    void getAvailableGamesForConsoleReturnsCorrectSize() {
        GameService.addGame(this.game4);
        GameService.addGame(this.game5);

        Assertions.assertEquals(1, GameService.getAvailableGamesForConsole(this.console2).size());
    }

    @Test
    void getAvailableGamesForConsoleWithGamesFromDifferentConsolesReturnsCorrectSize() {
        // non testing console games
        GameService.addGame(this.game1);
        GameService.addGame(this.game2);

        // testing console games
        GameService.addGame(this.game4);
        GameService.addGame(this.game5);

        Assertions.assertEquals(1, GameService.getAvailableGamesForConsole(this.console2).size());
    }

    @Test
    void getByIdReturnsCorrectGame() {
        GameService.addGame(this.game1);

        String gameId = this.game1.getId();
        Assertions.assertEquals(GameService.getById(gameId), this.game1);
    }

    @Test
    void getByIdReturnsNullIfWrongId() {
        GameService.addGame(this.game1);

        String wrongId = this.game2.getId();

        Assertions.assertEquals(null, GameService.getById(wrongId));
    }

    @Test
    void idExistsReturnsTrueIfCorrect() {
        GameService.addGame(this.game1);

        boolean result = GameService.idExists(this.game1.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void idExistsReturnsFalseIfNotCorrect() {
        GameService.addGame(this.game1);

        boolean result = GameService.idExists(this.game2.getId());
        Assertions.assertFalse(result);
    }

    @Test
    void addGameWorks() {
        GameService.addGame(this.game1);
        Assertions.assertEquals(1, GameService.getAllGames().size());
    }

    @Test
    void removeGameWorks() {
        GameService.addGame(this.game1);
        GameService.addGame(this.game2);
        // ensure both games got added
        Assertions.assertEquals(2, GameService.getAllGames().size());

        GameService.removeGame(this.game1);
        String expectedGameId = this.game2.getId();
        Assertions.assertEquals(GameService.getById(expectedGameId), this.game2);
    }

    @Test
    void changingAGameNameWorks() {
        GameService.addGame(this.game1);

        GameService.modifyGame(this.game1, "new name", false);
        Assertions.assertEquals("new name", GameService.getById(this.game1.getId()).getName());
    }

    @Test
    void changingAConsoleRepairStatusWorks() {
        GameService.addGame(this.game1);

        GameService.modifyGame(this.game1, "new name", true);
        Assertions.assertEquals(true, GameService.getById(this.game1.getId()).isInForRepair());
    }
}