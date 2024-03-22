import Tables.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddCustomer implements ActionListener {

    public static JFrame frame = new JFrame("Login");
    public static  JTextField name,surname,id;
    public static JLabel lname,lsurname,lid,error,error2;


    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "1234567890";

    public static int customer_id;
    public static String customer_name;
    public static String customer_surname;
    public static JButton b,b2;

    private enum Actions{
        REGISTER,
        LOGIN
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        customer_name = name.getText();
        customer_surname = surname.getText();

        DBConnection dbc = new DBConnection(url,user,password);

        if(e.getActionCommand() == Actions.REGISTER.name())
        {
            if(dbc.aleadyRegistered(customer_name,customer_surname)){
                error.setVisible(true);
            } else{
                customer_id=dbc.increseId(0);
                Customer c = new Customer(customer_id,customer_name,customer_surname);
                dbc.addCustomer(c);
                HomeMenue hm = new HomeMenue();
              // hm.HomeM();
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        HomeMenue hm = new HomeMenue();
                        hm.HomeM();
                    }
                });

            }

        }
        else if(e.getActionCommand() == Actions.LOGIN.name())
        {
            if(!dbc.aleadyRegistered(customer_name,customer_surname)){
                error2.setVisible(true);
            } else{
                customer_id=dbc.increseId(0);
                Customer c = new Customer(customer_id,customer_name,customer_surname);
                dbc.addCustomer(c);
                HomeMenue hm = new HomeMenue();
               // hm.HomeM();
                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        HomeMenue hm = new HomeMenue();
                        hm.HomeM();
                    }
                });

            }
        }
    }

    public static void main (String args[]){

        Color navy = new Color(0,0,80);
        Color red = new Color(255, 0, 0);


       b=new JButton("Sign In");
        b.setBounds(700,400,95,30);
        AddCustomer instance=new AddCustomer();//pentru a apela functia la click
        b.setActionCommand(AddCustomer.Actions.REGISTER.name());
        b.addActionListener(instance);
        frame.add(b);


        JButton b2=new JButton("Login");
        b2.setBounds(600,400,95,30);
        AddCustomer instance2=new AddCustomer();//pentru a apela functia la click
        b2.setActionCommand(Actions.LOGIN.name());
        b2.addActionListener(instance2);
        frame.add(b2);



        //already registered customer
        error = new JLabel("Customer already registered");
        error.setVisible(false);
        error.setBounds(570,450, 200,30);
        error.setFont(new Font("Tahoma", Font.PLAIN, 12));
        error.setForeground(red);
        frame.add(error);

        //account not made
        error2 = new JLabel("Make an account");
        error2.setVisible(false);
        error2.setBounds(570,450, 200,30);
        error2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        error2.setForeground(red);
        frame.add(error2);

        //label name
        lname = new JLabel("Name");
        lname.setBounds(500,250, 100,30);
        lname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lname.setBackground(navy);
        frame.add(lname);

        //text field name
        name=new JTextField("");  //text box
        name.setBounds(650,255, 200,30);
        frame.add(name);
        //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        name.setColumns(20);

        //label surname
        lsurname = new JLabel("Surname");
        lsurname.setBounds(500,300, 150,30);
        lsurname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lsurname.setBackground(navy);
        frame.add(lsurname);

        //text field surname
        surname=new JTextField("");  //text box
        surname.setBounds(650,300, 200,30);
        frame.add(surname);
        //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        surname.setColumns(20);

        frame.setSize(1600,850);                //frame
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBackground(navy);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
