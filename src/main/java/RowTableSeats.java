public class RowTableSeats {

    int tabele_nr;
    String details;

    public RowTableSeats(int tabele_nr, String details) {

            this.tabele_nr = tabele_nr;
            this.details = details;

    }

    public void setTabele_nr(int tabele_nr) {
        this.tabele_nr = tabele_nr;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getTabele_nr() {
        return tabele_nr;
    }

    public String getDetails() {
        return details;
    }
}
