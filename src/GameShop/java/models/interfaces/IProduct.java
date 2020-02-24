package GameShop.java.models.interfaces;

public interface IProduct {
    String getId();
    String getName();
    double getCost();
    boolean isInForRepair();

    void setName(String name);
    void setCost(double cost);
    void setInForRepair(boolean inForRepair);
}
