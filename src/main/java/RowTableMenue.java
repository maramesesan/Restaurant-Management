public class RowTableMenue {

    int id_item;
    String name;
    float price;


    public RowTableMenue( String name, float price, int id_item) {

        this.id_item=id_item;
        this.name = name;
        this.price = price;
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
