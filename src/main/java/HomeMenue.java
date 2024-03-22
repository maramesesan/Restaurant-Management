import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;


public class HomeMenue implements ActionListener {


    public static JFrame frame = new JFrame("Home");
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "1234567890";

    public static int idBook, idOrder;
    public static JLabel error, lName, lSurname, lDate;
    public static JTextField tName, tSurname, tDate;
    public static JButton b, cart,b2,b5;
    public static ImageIcon Morder = new ImageIcon("MakeanOrder.png");
    public static ImageIcon Mreserv = new ImageIcon("MakeReserv.png");
    public static ImageIcon cartI = new ImageIcon("Cart.png");

    public static String cName, cSurname, date;

    private enum Action{
        RESERVATION,
        ORDER,
        FINDREZ
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() == HomeMenue.Action.RESERVATION.name()){

        }
        if(e.getActionCommand() == Action.ORDER.name())
        {
            DBConnection dbc = new DBConnection(url, user, password);

            AddCustomer ac = new AddCustomer();
            Reservation r = new Reservation();



            if(r.rDate==null ||  ac.name.getText()==null || ac.surname==null)
            {
                error.setVisible(true);
                lName.setVisible(true);
                tName.setVisible(true);
                lSurname.setVisible(true);
                tSurname.setVisible(true);
                lDate.setVisible(true);
                tDate.setVisible(true);
                b5.setVisible(true);

            }
            else{
                cName = ac.name.getText();
                cSurname = ac.surname.getText();
                //System.out.println(c_name);
                date = r.rDate;
                System.out.println(date);

               idBook = dbc.selectBookingByName(cName, date, cSurname);
                idOrder = dbc.increseIdOrder();
                dbc.createOrder(idOrder, idBook);
                System.out.println(idOrder);
                b2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);

                    }
                });
                MakeOrder mo = new MakeOrder();
                mo.Order();
            }
        }
        if(e.getActionCommand() == Action.FINDREZ.name())
        {
            DBConnection dbc = new DBConnection(url, user, password);

            cName = tName.getText();
            cSurname = tSurname.getText();
            //System.out.println(c_name);
            date = tDate.getText();

            idBook = dbc.selectBookingByName(cName, date, cSurname);
            idOrder = dbc.increseIdOrder();
            dbc.createOrder(idOrder, idBook);
            System.out.println(idOrder);

        }


    }


public void HomeM(){
   //  public static void main (String args[]){

    error = new JLabel("Make a reservation first");
   error.setVisible(false);
    error.setBounds(450,400, 200,30);
    error.setFont(new Font("Tahoma", Font.PLAIN, 12));
    // error.setForeground(red);
    frame.add(error);

        lName = new JLabel("Name of reservation");
         lName.setVisible(false);
        lName.setBounds(240,500, 200,30);
        lName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        // error.setForeground(red);
        frame.add(lName);

         tName=new JTextField("");  //text box
         tName.setBounds(240,530, 200,30);
         frame.add(tName);
         tName.setVisible(false);
         //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
         tName.setColumns(20);

         lSurname = new JLabel("Surname of reservation");
          lSurname.setVisible(false);
         lSurname.setBounds(460,500, 200,30);
         lSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
         // error.setForeground(red);
         frame.add( lSurname);

         tSurname=new JTextField("");  //text box
         tSurname.setVisible(false);
         tSurname.setBounds(460,530, 200,30);
         frame.add(tSurname);
         //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
         tSurname.setColumns(20);

         lDate = new JLabel("Date of reservation");
          lDate.setVisible(false);
         lDate.setBounds(680,500, 200,30);
         lDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
         // error.setForeground(red);
         frame.add(lDate);

         tDate=new JTextField("");  //text box
         tDate.setVisible(false);
         tDate.setBounds(680,530, 200,30);
         frame.add(tDate);
         //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
         tDate.setColumns(20);

         b5=new JButton("CONTINUE");
         b5.setVisible(false);
         b5.setBounds(700,200,200,200);
         HomeMenue instance5=new HomeMenue();//pentru a apela functia la click
         b5.setActionCommand(HomeMenue.Action.FINDREZ.name());
         b5.addActionListener(instance5);
         frame.add(b5);
         b5.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e) {
                 frame.setVisible(false);
                 MakeOrder mo = new MakeOrder();
                 mo.Order();
             }
         });


         b=new JButton("Make reservation");
         b.setBounds(700,200,200,200);
         HomeMenue instance=new HomeMenue();//pentru a apela functia la click
         b.setActionCommand(HomeMenue.Action.RESERVATION.name());
         b.setIcon(Mreserv);
         b.addActionListener(instance);
         frame.add(b);
         b.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 frame.setVisible(false);
                 Reservation r = new Reservation();
               r.Reserv();
             }
         });

        b2=new JButton("Make an order");
         b2.setBounds(400,200,200,200);
         HomeMenue instance2=new HomeMenue();//pentru a apela functia la click
         b2.setActionCommand(HomeMenue.Action.ORDER.name());
         b2.setIcon(Morder);
         b2.addActionListener(instance2);
         frame.add(b2);


    cart=new JButton("");
    cart.setBounds(1000,90,40,40);
    HomeMenue instance3=new HomeMenue();
         cart.setIcon(cartI);
    cart.addActionListener(instance3);
    frame.add(cart);
    cart.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
           Cart c = new Cart();
           c.Cart();
        }
    });

        frame.setSize(1600,850);
        frame.setLayout(null);
        frame.setVisible(true);
      frame.setBackground(Color.BLUE);

    }
}
