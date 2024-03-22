package Tables;

public class Seats {

    private int table_nr;
    private String details;
    private int nr_seats;

    public Seats(int table_nr, String details, int nr_seats) {
        this.table_nr = table_nr;
        this.details = details;
        this.nr_seats=nr_seats;
    }

    public int getNr_seats() {
        return nr_seats;
    }

    public int getTable_nr() {
        return table_nr;
    }

    public String getDetails() {
        return details;
    }
}
