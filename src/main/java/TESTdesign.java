import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TESTdesign implements ActionListener {

    public static JFrame frame = new JFrame("Login");
    public static  JTextField name,surname,id;
    public static JLabel lname,lsurname;


    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "1234567890";



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main (String args[]){
        Color navy = new Color(0,0,80);

        lname = new JLabel("Name");
        lname.setBounds(50,50, 100,30);
        /// lname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lname.setBackground(navy);
       // name=new JTextField("name");

        frame.add(lname);
        frame.setSize(1600,850);                //frame
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBackground(navy);

    }


}
