package Tests.services;

import GameShop.java.models.Console;
import GameShop.java.models.Game;
import GameShop.java.services.GameService;
import Tests.TestData;
import org.junit.jupiter.api.*;

class GameServiceTest {
    final TestData testData = new TestData();

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

        Assertions.assertNull(GameService.getById(wrongId));
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
        Assertions.assertTrue(GameService.getById(this.game1.getId()).isInForRepair());
    }
}