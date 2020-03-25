package GameShop.java.models;

public class Game extends Product {
    private static int idSeed = 1000;
    private final String id;
    private Console console;

    public Game(String name, Console console, double cost, boolean inForRepair) {
        super(name, cost, inForRepair);
        String idPrefix = "GA";
        id = String.format("%s%d", idPrefix, idSeed);
        this.console = console;
        idSeed++;
    }

    public String getId() {
        return id;
    }

    public Console getConsole() {
        return console;
    }
}
