package Tables;

public class MenueItemOnOrder {

    private int menue_item_on_order_id;
    private int id_ored;
    private int manue_item;
    private int quantity;

    public MenueItemOnOrder(int menue_item_on_order_id, int id_ored, int manue_item, int quantity) {
        this.menue_item_on_order_id = menue_item_on_order_id;
        this.id_ored = id_ored;
        this.manue_item = manue_item;
        this.quantity = quantity;
    }

    public int getMenue_item_on_order_id() {
        return menue_item_on_order_id;
    }

    public int getId_ored() {
        return id_ored;
    }

    public int getManue_item() {
        return manue_item;
    }

    public int getQuantity() {
        return quantity;
    }
}
