package GameShop.java.models;

public class RentalItem {
    private static int idSeed = 1;
    private int id;
    private int rentalId;
    private String productId;

    public RentalItem(int rentalId, String productId) {
        id = idSeed;
        this.rentalId = rentalId;
        this.productId = productId;
        idSeed++;
    }

    public int getId() {
        return id;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "RentalItem{" +
                "idSeed=" + idSeed +
                ", id=" + id +
                ", rentalId=" + rentalId +
                ", productId='" + productId + '\'' +
                '}';
    }
}
