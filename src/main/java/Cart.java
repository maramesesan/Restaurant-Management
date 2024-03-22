import Tables.Bill;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cart implements ActionListener {
    public static JFrame frame = new JFrame("Cart");
    public static JTable table;
    public static JButton order;
    public static JTextField tBill;
    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String password = "1234567890";

    public static int bill, id_bill;
    DBConnection dbc = new DBConnection(url,user,password);
    @Override
    public void actionPerformed(ActionEvent e) {
        DBConnection dbc = new DBConnection(url,user,password);


        HomeMenue hm = new HomeMenue();
        bill = dbc.calculateBill(hm.idOrder);
        id_bill = dbc.increseIdBill();
        System.out.println(bill);
        System.out.println(id_bill);
        Bill b = new Bill(id_bill,hm.idOrder,bill);
        dbc.insertBill(b);

        tBill=new JTextField(bill);  //text box
        tBill.setBounds(680,200, 50,30);
        frame.add(tBill);
        //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tBill.setColumns(20);

    }


   public void Cart(){
      // public static void main(String[] args){
            DBConnection dbc = new DBConnection(url,user,password);


        HomeMenue hm = new HomeMenue();

        int bookingId = hm.idBook;
        dbc.selectFoodByOrder(bookingId);
        System.out.println("id book " + hm.idBook);
        String[][] rec=new String[][]{
                {dbc.rto[1].getName(), String.valueOf(dbc.rto[1].getPrice()), String.valueOf(dbc.rto[1].getQuantity())},
                {dbc.rto[2].getName(), String.valueOf(dbc.rto[2].getPrice()), String.valueOf(dbc.rto[2].getQuantity())},
                {dbc.rto[3].getName(), String.valueOf(dbc.rto[3].getPrice()), String.valueOf(dbc.rto[3].getQuantity())},
                {dbc.rto[4].getName(), String.valueOf(dbc.rto[4].getPrice()), String.valueOf(dbc.rto[4].getQuantity())},
                {dbc.rto[5].getName(), String.valueOf(dbc.rto[5].getPrice()), String.valueOf(dbc.rto[5].getQuantity())},
                {dbc.rto[6].getName(), String.valueOf(dbc.rto[6].getPrice()), String.valueOf(dbc.rto[6].getQuantity())},
                {dbc.rto[7].getName(), String.valueOf(dbc.rto[7].getPrice()), String.valueOf(dbc.rto[7].getQuantity())},
                {dbc.rto[8].getName(), String.valueOf(dbc.rto[8].getPrice()), String.valueOf(dbc.rto[8].getQuantity())},
                {dbc.rto[9].getName(), String.valueOf(dbc.rto[9].getPrice()), String.valueOf(dbc.rto[9].getQuantity())},

        };

        String []title={"Food", "Price", "Quantity"};

        table=new JTable(rec, title);
        table.setBounds(900,250,200,100);
        frame.add(table);


        order = new JButton("ADD ORDER");
        order.setBounds(200,200,100,30);
        Cart instancec=new Cart();
        order.setActionCommand(MakeOrder.Actions.CART.name());
        order.addActionListener(instancec);
        frame.add(order);


        frame.setSize(1600,850);                //frame
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
