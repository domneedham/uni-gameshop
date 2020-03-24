package GameShop.java.models.interfaces;

public interface IProduct {
    String getId();
    String getName();
    double getCost();
    boolean isInForRepair();
    boolean isCurrentlyRented();
    boolean isAvailable();

    void setName(String name);
    void setCost(double cost);
    void setInForRepair(boolean inForRepair);
    void setIsCurrentlyRented(boolean status);
}
