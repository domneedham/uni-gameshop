package Tests.services;

import GameShop.java.models.Console;
import GameShop.java.models.Game;
import GameShop.java.services.GameService;
import Tests.TestData;
import org.junit.jupiter.api.*;

class GameServiceTest {
    final TestData testData = new TestData();
    final GameService gameService = new GameService();

    Console console1 = testData.standardConsole1;
    final Game game1 = testData.consoleOneGame1;
    final Game game2 = testData.consoleOneGame2;
    final Game game3 = testData.consoleOneRepairGame1;

    final Console console2 = testData.standardConsole2;
    final Game game4 = testData.consoleTwoGame1;
    final Game game5 = testData.consoleTwoRepairGame1;


    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        gameService.getAllGames().clear();
        // test clear worked on consoles before running other tests
        Assertions.assertEquals(0, gameService.getAllGames().size());
    }

    @Test
    void getAllGamesReturnsCorrectSize() {
        Assertions.assertEquals(0, gameService.getAllGames().size());

        gameService.addGame(this.game1);
        Assertions.assertEquals(1, gameService.getAllGames().size());
    }

    @Test
    void getAvailableGamesReturnsCorrectSize() {
        Assertions.assertEquals(0, gameService.getAvailableGames().size());

        gameService.addGame(this.game1);
        // unavailable game
        gameService.addGame(this.game3);
        Assertions.assertEquals(1, gameService.getAvailableGames().size());
    }

    @Test
    void getAllGamesForConsoleWithGamesFromDifferentConsolesReturnsCorrectSize() {
        // non testing console games
        gameService.addGame(this.game1);
        gameService.addGame(this.game2);

        // testing console games
        gameService.addGame(this.game4);
        gameService.addGame(this.game5);

        Assertions.assertEquals(2, gameService.getAllGamesForConsole(this.console2).size());
    }

    @Test
    void getAvailableGamesForConsoleReturnsCorrectSize() {
        gameService.addGame(this.game4);
        gameService.addGame(this.game5);

        Assertions.assertEquals(1, gameService.getAvailableGamesForConsole(this.console2).size());
    }

    @Test
    void getAvailableGamesForConsoleWithGamesFromDifferentConsolesReturnsCorrectSize() {
        // non testing console games
        gameService.addGame(this.game1);
        gameService.addGame(this.game2);

        // testing console games
        gameService.addGame(this.game4);
        gameService.addGame(this.game5);

        Assertions.assertEquals(1, gameService.getAvailableGamesForConsole(this.console2).size());
    }

    @Test
    void getByIdReturnsCorrectGame() {
        gameService.addGame(this.game1);

        String gameId = this.game1.getId();
        Assertions.assertEquals(gameService.getById(gameId), this.game1);
    }

    @Test
    void getByIdReturnsNullIfWrongId() {
        gameService.addGame(this.game1);

        String wrongId = this.game2.getId();

        Assertions.assertNull(gameService.getById(wrongId));
    }

    @Test
    void idExistsReturnsTrueIfCorrect() {
        gameService.addGame(this.game1);

        boolean result = gameService.idExists(this.game1.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void idExistsReturnsFalseIfNotCorrect() {
        gameService.addGame(this.game1);

        boolean result = gameService.idExists(this.game2.getId());
        Assertions.assertFalse(result);
    }

    @Test
    void addGameWorks() {
        gameService.addGame(this.game1);
        Assertions.assertEquals(1, gameService.getAllGames().size());
    }

    @Test
    void removeGameWorks() {
        gameService.addGame(this.game1);
        gameService.addGame(this.game2);
        // ensure both games got added
        Assertions.assertEquals(2, gameService.getAllGames().size());

        gameService.removeGame(this.game1);
        String expectedGameId = this.game2.getId();
        Assertions.assertEquals(gameService.getById(expectedGameId), this.game2);
    }

    @Test
    void changingAGameNameWorks() {
        gameService.addGame(this.game1);

        gameService.modifyGame(this.game1, "new name", false);
        Assertions.assertEquals("new name", gameService.getById(this.game1.getId()).getName());
    }

    @Test
    void changingAConsoleRepairStatusWorks() {
        gameService.addGame(this.game1);

        gameService.modifyGame(this.game1, "new name", true);
        Assertions.assertTrue(gameService.getById(this.game1.getId()).isInForRepair());
    }
}