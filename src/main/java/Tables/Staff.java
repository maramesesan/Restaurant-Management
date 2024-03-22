package Tables;

public class Staff {

    private int id_staff;
    private int role_code;
    private String name;
    private String surname;
    private int payment;
    private int age;
    private int table_nr;

    public Staff(int id_staff, int role_code, String name, String surname, int payment, int age, int table_nr) {
        this.id_staff = id_staff;
        this.role_code = role_code;
        this.name = name;
        this.surname = surname;
        this.payment = payment;
        this.age = age;
        this.table_nr = table_nr;
    }

    public int getId_staff() {
        return id_staff;
    }

    public int getRole_code() {
        return role_code;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPayment() {
        return payment;
    }

    public int getAge() {
        return age;
    }

    public int getTable_nr() {
        return table_nr;
    }
}
