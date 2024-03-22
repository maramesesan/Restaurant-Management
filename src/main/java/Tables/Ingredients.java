package Tables;

public class Ingredients {
    private int id_ingredient;
    private String name;

    public Ingredients(int id_ingredient, String name) {
        this.id_ingredient = id_ingredient;
        this.name = name;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public String getName() {
        return name;
    }
}
