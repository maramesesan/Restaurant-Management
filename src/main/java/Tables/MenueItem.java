package Tables;

public class MenueItem {
    private int id_item;
    private String name;
    private float price;
    private String meal_type;

    public MenueItem(int id_item, String name, float price, String meal_type) {
        this.id_item = id_item;
        this.name = name;
        this.price = price;
        this.meal_type = meal_type;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public int getId_item() {
        return id_item;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
