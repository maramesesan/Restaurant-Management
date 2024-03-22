package Tables;

public class Order {

    private int id_order;
    private float price;
    private int booking_id;

    public Order(int id_order, float price, int id_booking) {
        this.id_order = id_order;
        this.price = price;
        this.booking_id = id_booking;
    }

    public int getId_order() {
        return id_order;
    }

    public float getPrice() {
        return price;
    }

    public int getBooking_id() {
        return booking_id;
    }
}
