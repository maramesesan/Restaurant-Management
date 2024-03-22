import Tables.Bill;
import Tables.MenueItemOnOrder;

import java.util.Scanner;

public class MakeAnOrder {

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234567890";

        DBConnection dbc = new DBConnection(url,user,password);
        Scanner scanner = new Scanner(System.in);

        String meal;

        //start order
        int order_id = dbc.increseIdOrder();
        System.out.println("Name of the booking");
        String name = scanner.next();
        String date = scanner.next();
        int id_b = dbc.selectBookingByName(name, date,"");
        dbc.createOrder(order_id,id_b);

        ///add item to order

        System.out.println("MEAL");
        meal = scanner.next();
        dbc.choseFoodByMeal(meal);

        System.out.println("The food");
        String food = scanner.next();
        int item;
        item = dbc.idFoodByName(food);

        int id_itemorder = dbc.increseIdMIOO();
        System.out.println("Quantity");
        int quantity = scanner.nextInt();

        MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder,order_id,item,quantity);
        dbc.insertInOrder(mo);

        System.out.println(item + " " + order_id);
        ///make a bill
        int bill = dbc.calculateBill(order_id);
        int id_bill = dbc.increseIdBill();

        Bill b = new Bill(id_bill,order_id,bill);
        dbc.insertBill(b);
    }

}
