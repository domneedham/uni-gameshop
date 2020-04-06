package GameShop.java.models;

public class Basket extends Order {
    private final int MAX_GAMES = Rental.getMaxGames();
    private boolean consoleRequired = false;

    public Basket() {
        super();
    }

    public boolean isBasketPopulated() {
        return customer != null && dateRented != null && (consoleRequired && console != null || games.size() > 0);
    }

    public boolean isConsoleRequired() {
        return consoleRequired;
    }

    public void setConsoleRequired(boolean required) {
        this.consoleRequired = required;
    }

    public void clearBasket() {
        customer = null;
        console = null;
        games.clear();
        dateRented = null;
        consoleRequired = false;
    }

    public boolean gameInBasket(Game game) {
        return games.contains(game);
    }

    public boolean isMaxGamesInBasket() {
        return games.size() >= MAX_GAMES;
    }

    public int getNumberOfGamesInBasket() {
        return games.size();
    }

    public void clearGames() {
        games.clear();
    }
    
    @Override
    public String toString() {
        return "Customer: " + customer +
                ", Console Required: " + consoleRequired +
                ", Console: " + console +
                ", Games: " + games +
                ", Date: " + dateRented;
    }
}
