package Tables;

public class ItemIngredient {

    private int item_ingredient_id;
    private int id_ingredient;
    private int id_item;

    public ItemIngredient(int item_ingredient_id, int id_ingredient, int id_item) {
        this.item_ingredient_id = item_ingredient_id;
        this.id_ingredient = id_ingredient;
        this.id_item = id_item;
    }

    public int getItem_ingredient_id() {
        return item_ingredient_id;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public int getId_item() {
        return id_item;
    }
}
