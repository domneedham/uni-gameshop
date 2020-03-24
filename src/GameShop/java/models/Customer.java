package GameShop.java.models;

public class Customer {
    private static int idSeed = 1000;
    private final int id;
    private String forename;
    private String surname;
    private String email;
    private String telNumber;

    public Customer(String forename, String surname, String email, String telNumber) {
        id = idSeed;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.telNumber = telNumber;
        idSeed++;
    }

    public int getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return forename + " " + surname;
    }
}
