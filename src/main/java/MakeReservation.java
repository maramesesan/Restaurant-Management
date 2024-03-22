import Tables.Booking;
import Tables.Customer;

import java.util.Scanner;

public class MakeReservation {
    public static void main(String[] args){

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234567890";

        DBConnection dbc = new DBConnection(url,user,password);
        Scanner scanner = new Scanner(System.in);
        int id_customer;
        String name;
        String surname;
        int id_booking;
        int table_nr;
        int nr_persons;
        int day;
        int month;
        int year;

        System.out.println("Make an account");
        System.out.print("unique id: ");
        id_customer = scanner.nextInt();
        if(dbc.customerExists(id_customer))
        {
            System.out.println("Customer already registered: ");
            dbc.selectCustomerById(id_customer);
        }

        else {
            System.out.println("Name and Surname: ");
            name = scanner.next();
            surname = scanner.next();
            Customer c = new Customer(id_customer,name,surname);
            dbc.addCustomer(c);
        }

       // System.out.println("Chose a date and see availability: ");
        System.out.println("Make reservation: ");
        System.out.println("Reservation id: ");
        id_booking = scanner.nextInt();

        System.out.println("Nr of persons: ");
        nr_persons = scanner.nextInt();
        dbc.selectBookingBySeats(nr_persons);

        System.out.println("Date of the reservation (DD-MM-YYYY): ");
        /*day = scanner.nextInt();
        month = scanner.nextInt();
        year = scanner.nextInt();

        java.util.Date date = new java.util.Date(year,month,day);
        java.sql.Date bDate = new java.sql.Date(date.getTime());*/
        String date = scanner.next();

        dbc.selectBookingByDate(date);

        System.out.println("Chose the table nr: ");
        table_nr = scanner.nextInt();

        //java.sql.Date bDate = new java.sql.Date(date.getTime());
        Booking b = new Booking(id_booking, id_customer, table_nr,nr_persons,date);
        dbc.makeReservation(b);
    }


}
