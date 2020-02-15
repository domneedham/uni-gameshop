package GameShop.java.models;

public class Game implements ProductInterface {
    private final String idPrefix = "GA";
    private static int idSeed = 1000;
    private String id;
    private String name;
    private Console console;
    private double cost;
    private boolean inForRepair;

    public Game(String name, Console console, double cost, boolean inForRepair) {
        id = String.format("%s%d", idPrefix, idSeed);
        this.name = name;
        this.console = console;
        this.cost = cost;
        this.inForRepair = inForRepair;
        idSeed++;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean isInForRepair() {
        return inForRepair;
    }

    @Override
    public void setInForRepair(boolean inForRepair) {
        this.inForRepair = inForRepair;
    }

    public Console getConsole() {
        return console;
    }

    // used for JAVA FXML tables to show formatted data
    public String getFXMLConsoleName() { return console.getName(); }
    public String getFXMLAvailable() { return inForRepair ? "No" : "Yes" ;}
    public String getFXMLFormattedCost() { return String.format("£%.2f", cost); }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", console='" + console + '\'' +
                ", cost=" + cost +
                ", inForRepair=" + inForRepair +
                '}';
    }
}
