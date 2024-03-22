package Tables;

public class Bill {
    private int id_bill;
    private int id_order;
    private float price;

    public Bill(int id_bill, int id_order, float price) {
        this.id_bill = id_bill;
        this.id_order = id_order;
        this.price = price;
    }

    public int getId_bill() {
        return id_bill;
    }

    public int getId_order() {
        return id_order;
    }

    public float getPrice() {
        return price;
    }
}
