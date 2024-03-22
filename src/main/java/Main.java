
import Tables.Booking;
import Tables.Customer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234567890";

        DBConnection dbc = new DBConnection(url,user,password);

        Scanner scanner = new Scanner(System.in);

        String name;
        String surname;

        int id_booking;
        int id_customer;
        int table_nr;
        int nr_persons;
        int day;
        int month;
        int year;

       int command=-1;

        while(command!=0) {

            System.out.println("Enter command:");
            System.out.println("1. Add customer\n"+"2. Delete customer with id\n"+"3. Make a reservation\n"+"4. Select customer by name");
            command = scanner.nextInt();

            if(command==1){
                System.out.println("Add new customer: ");
                System.out.print("Customer's id: ");
                id_customer = scanner.nextInt();
                System.out.print("Customer's name and surname: ");
                name = scanner.next();
                surname = scanner.next();
                Customer c = new Customer(id_customer,name,surname);
                dbc.addCustomer(c);
            }

            else if(command == 2){

                int give_id;
                System.out.println("delete person with id");
                give_id =  scanner.nextInt();
                dbc.deleteCustomer(give_id);
            }

            else if (command == 3){

                System.out.println("Reservation id: ");
                id_booking = scanner.nextInt();
                System.out.println("Customer id: ");
                id_customer = scanner.nextInt();
                System.out.println("Table nr: ");
                table_nr = scanner.nextInt();
                System.out.println("Nr of persons: ");
                nr_persons = scanner.nextInt();
                System.out.println("Date of the reservation: ");
                /*day = scanner.nextInt();
                month = scanner.nextInt();
                year = scanner.nextInt();*/
                String date = scanner.next();

                Booking b = new Booking(id_booking,id_customer,table_nr,nr_persons,date);
                dbc.makeReservation(b);
            }

            else if(command == 4){

                System.out.println("Name of customer: ");
                name = scanner.next();
                dbc.selectCustomerByName(name);
            }
        }
    }

}
