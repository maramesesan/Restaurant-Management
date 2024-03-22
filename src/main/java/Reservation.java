import Tables.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reservation implements ActionListener {

    public static JFrame frame = new JFrame("Reservation");
   static String url = "jdbc:postgresql://localhost:5432/postgres";
   static String user = "postgres";
   static String password = "1234567890";

    private enum Actions{
        PLACES,
        DATE,
        TABLE,
        RESERV
    }

    public static JLabel places,ldate, ltable, lTableSeats, lTableDate;
    public static ImageIcon cartI = new ImageIcon("Cart.png");
    public static JTextField nrPlaces, date, seat;
    public static JTable table, table2, table3;
   public static JButton b4, back, cart;

    public static int nrSeats, nrTable;
    public static String rDate;
    DBConnection dbc = new DBConnection(url,user,password);
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() == Actions.PLACES.name())
        {
            lTableSeats.setVisible(true);
            String text = nrPlaces.getText();
            nrSeats = Integer.parseInt(text);

            dbc.selectBookingBySeats(nrSeats);

            String[][] rec=new String[][]{
                    {String.valueOf(dbc.rts[1].getTabele_nr()),dbc.rts[1].getDetails()},
                    {String.valueOf(dbc.rts[2].getTabele_nr()),dbc.rts[2].getDetails()},
                    {String.valueOf(dbc.rts[3].getTabele_nr()),dbc.rts[3].getDetails()},
                    {String.valueOf(dbc.rts[4].getTabele_nr()),dbc.rts[4].getDetails()},
                    {String.valueOf(dbc.rts[5].getTabele_nr()),dbc.rts[5].getDetails()},
                    // {String.valueOf(dbc.rts[6].getTabele_nr()),dbc.rts[6].getDetails()},
                    // {String.valueOf(dbc.rts[7].getTabele_nr()),dbc.rts[7].getDetails()},
                    ///{String.valueOf(dbc.rts[8].getTabele_nr()),dbc.rts[8].getDetails()},
                    ///{String.valueOf(dbc.rts[9].getTabele_nr()),dbc.rts[9].getDetails()},

            };
            String []title={"nume tabel", "detalii"};
            table=new JTable(rec, title);
            table.setBounds(150,550,300,100);
            frame.add(table);
        }
        if(e.getActionCommand() == Reservation.Actions.DATE.name()){
            lTableDate.setVisible(true);

             rDate = date.getText();
            System.out.println(rDate);
            dbc.selectBookingByDate(rDate);


            String[][] rec=new String[][]{
                    {String.valueOf(dbc.rts[1].getTabele_nr()),dbc.rts[1].getDetails()},
                    {String.valueOf(dbc.rts[2].getTabele_nr()),dbc.rts[2].getDetails()},
                    {String.valueOf(dbc.rts[3].getTabele_nr()),dbc.rts[3].getDetails()},
                    {String.valueOf(dbc.rts[4].getTabele_nr()),dbc.rts[4].getDetails()},
                     {String.valueOf(dbc.rts[5].getTabele_nr()),dbc.rts[5].getDetails()},
                    //{String.valueOf(dbc.rts[6].getTabele_nr()),dbc.rts[6].getDetails()},
                    //{String.valueOf(dbc.rts[7].getTabele_nr()),dbc.rts[7].getDetails()},
                    ///{String.valueOf(dbc.rts[8].getTabele_nr()),dbc.rts[8].getDetails()},
                    ///{String.valueOf(dbc.rts[9].getTabele_nr()),dbc.rts[9].getDetails()},

            };

            String []titluri={"nume tabel", "detalii"};
            table2=new JTable(rec, titluri);
            table2.setBounds(480,550,300,100);
            frame.add(table2);

            dbc.selectBookingByDate2(rDate);
            String[][] rec2=new String[][]{
                    {String.valueOf(dbc.rts2[1].getTabele_nr()),dbc.rts2[1].getDetails()},
                    {String.valueOf(dbc.rts2[2].getTabele_nr()),dbc.rts2[2].getDetails()},
                   // {String.valueOf(dbc.rts2[3].getTabele_nr()),dbc.rts2[3].getDetails()},
                   // {String.valueOf(dbc.rts2[4].getTabele_nr()),dbc.rts2[4].getDetails()},
                   // {String.valueOf(dbc.rts2[5].getTabele_nr()),dbc.rts2[5].getDetails()},
                    //{String.valueOf(dbc.rts[6].getTabele_nr()),dbc.rts[6].getDetails()},
                    //{String.valueOf(dbc.rts[7].getTabele_nr()),dbc.rts[7].getDetails()},
                    ///{String.valueOf(dbc.rts[8].getTabele_nr()),dbc.rts[8].getDetails()},
                    ///{String.valueOf(dbc.rts[9].getTabele_nr()),dbc.rts[9].getDetails()},

            };

            String []titluri2={"nume tabel", "detalii"};
            table3=new JTable(rec2, titluri2);
            table3.setBounds(800,550,300,100);
            frame.add(table3);

       }

        if(e.getActionCommand() == Reservation.Actions.TABLE.name())
        {
            String text3 = seat.getText();
            nrTable = Integer.parseInt(text3);
            System.out.println(nrTable);
            b4.setVisible(true);
        }

        if(e.getActionCommand() == Actions.RESERV.name())
        {
            AddCustomer ad = new AddCustomer();
            int id_booking=dbc.increseIdBooking(0);
            Booking b = new Booking(id_booking, ad.customer_id, nrTable , nrSeats, rDate);
            dbc.makeReservation(b);
        }

    }

    public void Reserv (){
   // public static void main (String args[]){

        DBConnection dbc = new DBConnection(url,user,password);

        back=new JButton("BACK");
        back.setBounds(100,100,95,30);
        Reservation instanceback=new Reservation();//pentru a apela functia la click
        back.setActionCommand(Actions.TABLE.name());
        back.addActionListener(instanceback);
        frame.add(back);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
               HomeMenue hm = new HomeMenue();
              hm.HomeM();
            }
        });


        //PLACES
        JButton b=new JButton("Give seats");
        b.setBounds(370,250,95,30);
        Reservation instance=new Reservation();//pentru a apela functia la click
        b.setActionCommand(Actions.PLACES.name());
       b.addActionListener(instance);
     frame.add(b);


        places = new JLabel("Chose seats");
        places.setBounds(500,250, 200,30);
        places.setFont(new Font("Tahoma", Font.PLAIN, 20));
       // places.setBackground(navy);
        frame.add(places);

        nrPlaces=new JTextField("");  //text box
        nrPlaces.setBounds(650,250, 200,30);
        frame.add(nrPlaces);
        //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        nrPlaces.setColumns(20);

        lTableSeats = new JLabel("Available Seats");
        lTableSeats.setVisible(false);
        lTableSeats.setBounds(150,500, 200,30);
        lTableSeats.setFont(new Font("Tahoma", Font.PLAIN, 15));
        //lTableSeats.setVisible(false);
        // places.setBackground(navy);
        frame.add(lTableSeats);

        //DATE
        JButton b2=new JButton("Give date");
        b2.setBounds(370,300,95,30);
        Reservation instance2=new Reservation();//pentru a apela functia la click
        b2.setActionCommand(Actions.DATE.name());
        b2.addActionListener(instance2);
        frame.add(b2);

        ldate = new JLabel("Chose date");
        ldate.setBounds(500,300, 200,30);
        ldate.setFont(new Font("Tahoma", Font.PLAIN, 20));

        // places.setBackground(navy);
        frame.add(ldate);

        date=new JTextField("");  //text box
        date.setBounds(650,300, 200,30);
        frame.add(date);
        //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        date.setColumns(20);

        lTableDate = new JLabel("Available Seats");
        lTableDate.setVisible(false);
        lTableDate.setBounds(480,500, 200,30);
        lTableDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        //lTableSeats.setVisible(false);
        // places.setBackground(navy);
        frame.add(lTableDate);


        //TABLE
        JButton b3=new JButton("Give table");
        b3.setBounds(370,350,95,30);
        Reservation instance3=new Reservation();//pentru a apela functia la click
        b3.setActionCommand(Actions.TABLE.name());
        b3.addActionListener(instance3);
        frame.add(b3);

        ltable = new JLabel("Chose table");
        ltable.setBounds(500,350, 200,30);
        ltable.setFont(new Font("Tahoma", Font.PLAIN, 20));
        // places.setBackground(navy);
        frame.add(ltable);

        cart=new JButton("");
        cart.setBounds(1000,90,40,40);
        HomeMenue instancec=new HomeMenue();
        cart.setIcon(cartI);
        cart.addActionListener(instancec);
        frame.add(cart);
        cart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Cart c = new Cart();
                c.Cart();
            }
        });

        seat=new JTextField("");  //text box
        seat.setBounds(650,350, 200,30);
        frame.add(seat);
        //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        seat.setColumns(20);

        b4=new JButton("Make reservation");
        b4.setBounds(600,400,100,30);
        Reservation instance4=new Reservation();//pentru a apela functia la click
        b4.setActionCommand(Actions.RESERV.name());
        b4.addActionListener(instance4);
        b4.setVisible(false);
        frame.add(b4);

        frame.setSize(1600,850);                //frame
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



}
