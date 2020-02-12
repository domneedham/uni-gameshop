package GameShop.java.models;

public interface ProductInterface {
    public String getId();
    public String getName();
    public double getCost();
    public boolean isInForRepair();

    public void setName(String name);
    public void setCost(double cost);
    public void setInForRepair(boolean inForRepair);
}
