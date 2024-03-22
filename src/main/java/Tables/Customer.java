package Tables;

public class Customer {
    private int id_customer;
    private String name;
    private String surname;

    public Customer(int id_customer, String name, String surname) {
        this.id_customer = id_customer;
        this.name = name;
        this.surname = surname;
    }

    public int getId_customer() {
        return id_customer;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


}
