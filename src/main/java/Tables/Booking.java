package Tables;

import java.sql.Date;

public class Booking {
    private int id_booking;
    private int id_customer;
    private int table_nr;
    private int nr_persons;
    private String date;

    public Booking(int id_booking, int id_customer, int table_nr, int nr_persons,  String date) {
        this.id_booking = id_booking;
        this.id_customer = id_customer;
        this.table_nr = table_nr;
        this.nr_persons = nr_persons;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getId_booking() {
        return id_booking;
    }

    public int getId_customer() {
        return id_customer;
    }

    public int getTable_nr() {
        return table_nr;
    }

    public int getNr_persons() {
        return nr_persons;
    }
}
