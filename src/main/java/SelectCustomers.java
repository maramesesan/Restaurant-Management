import java.util.Scanner;

public class SelectCustomers {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234567890";

        DBConnection dbc = new DBConnection(url,user,password);

        Scanner scanner = new Scanner(System.in);

       // int give_id = scanner.nextInt();
        String name = scanner.next();
        //dbc.selectCustomerByName(name);
       // dbc.showReservation(name);
    }



}
