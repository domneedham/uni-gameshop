package GameShop.java.models;

public class Game extends Product {
    private final Console console;

    public Game(String name, Console console, double cost, boolean inForRepair) {
        super(name, cost, inForRepair);
        this.console = console;
    }

    public Console getConsole() {
        return console;
    }
}
