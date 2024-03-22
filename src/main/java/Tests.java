import java.util.Scanner;

public class Tests {

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234567890";

        DBConnection dbc = new DBConnection(url,user,password);

        Scanner scanner = new Scanner(System.in);

        String meal = scanner.next();
       // dbc.selectBookingByDate(meal);

    dbc.choseFoodByMeal(meal);

    if(meal!="o") System.out.println("da");


    }
}
