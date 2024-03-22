
import Tables.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class DBConnection {
    String url;
    String user;
    String password;
   public RowTableSeats[] rts = new RowTableSeats[20];
    public RowTableSeats[] rts2 = new RowTableSeats[20];

    public RowTableMenue[] rtm = new RowTableMenue[200];
    public RowTableOrder[] rto = new RowTableOrder[20];

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public DBConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void addCustomer(Customer c) {
        String sql = "INSERT INTO customer (id_customer,name,surname) VALUES (?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, c.getId_customer());
            ps.setString(2, c.getName());
            ps.setString(3, c.getSurname());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomer(int id) {
        String query = "DELETE FROM customer WHERE id_customer = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectcustomer() {
        String query = "SELECT id_customer, name, surname FROM customer";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ) {
            while (rs.next()) {
                System.out.print(rs.getInt("id_customer"));
                System.out.print(" " + rs.getString("name"));
                System.out.print(" " + rs.getString("surname"));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCustomerByName(String name) {
        String query = "SELECT * FROM customer WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();
        ) {
            //
            ps.setString(1, name);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getInt("id_customer"));
                System.out.print(" " + rs.getString("name"));
                System.out.print(" " + rs.getString("surname"));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCustomerById(int id) {
        String query = "SELECT * FROM customer WHERE id_customer = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();
        ) {
            //
            ps.setInt(1, id);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getInt("id_customer"));
                System.out.print(" " + rs.getString("name"));
                System.out.print(" " + rs.getString("surname"));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void makeReservation(Booking b) {
        String query = "INSERT INTO booking (id_booking, id_customer, table_nr, nr_persons, date) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, b.getId_booking());
            ps.setInt(2, b.getId_customer());
            ps.setInt(3, b.getTable_nr());
            ps.setInt(4, b.getNr_persons());
            ps.setString(5, b.getDate());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void showReservation(String name){
        String query = "SELECT b.table_nr, b.date, c.name, c.surname FROM booking b INNER JOIN customer c ON b.id_customer = c.id_customer WHERE c.name = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();

        ) {
            ps.setString(1, name);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getInt("table_nr"));
                System.out.print(" " +rs.getDate("date"));
                System.out.print(" " + rs.getString("name"));
                System.out.print(" " + rs.getString("surname"));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    boolean customerExists(int id){
        String query = "SELECT 1 FROM customer WHERE id_customer = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();

        ) {
            ps.setInt(1, id);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            return rs.next();
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    boolean aleadyRegistered(String c_name, String c_surname){
        String query = "SELECT 1 FROM customer WHERE name = ? AND surname = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();

        ) {
            ps.setString(1,c_name);
            ps.setString(2,c_surname);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void selectBookingBySeats(int nr_seats) {
        int i=0;

    String query = "SELECT * FROM seats";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement ps = connection.createStatement();
             // Statement statement = connection.createStatement();
        ) {
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery(query);
            //Vector v = new Vector();
            while (rs.next()) {
                    i++;
                    if(rs.getInt("table_nr")!=0 && rs.getString("details")!= null)
                    {
                        if(rs.getInt("nr_seats") == nr_seats)
                        {
                        rts[i]=new RowTableSeats(rs.getInt("table_nr"), rs.getString("details"));
                        // System.out.print(rs.getInt("table_nr"));
                        //System.out.print(" " + rs.getString("details"));
                        // System.out.println();
                    }
                        else{
                            rts[i]=new RowTableSeats(rs.getInt("table_nr"), "Not enough nr of seats");
                        }
                    }
                    else{
                        rts[i]=new RowTableSeats(rs.getInt("table_nr"), "Not enough nr of seats");
                    }

                }
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
    }


    void selectBookingByDate(String date) {
        int i=0;
        String query = "SELECT DISTINCT s.table_nr, s.details from seats s, booking b WHERE NOT EXISTS(SELECT b.table_nr FROM booking b WHERE b.table_nr = s.table_nr)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
            // PreparedStatement ps = connection.prepareStatement(query);
             Statement ps = connection.createStatement();
            // PreparedStatement ps = connection.prepareStatement(query);
        ) {

            //ResultSet rs = ps.executeQuery();
            ResultSet rs = ps.executeQuery(query);

            //ps.setString(1, date);
            while (rs.next()) {
               i++;

                if(rs.getInt("table_nr")!=0 && rs.getString("details")!= null )
                {

                    rts[i]=new RowTableSeats(rs.getInt("table_nr"), rs.getString("details"));

                }
                else{
                    rts[i]=new RowTableSeats(0, "Not enough nr of seats");
                }
                rts[i]=new RowTableSeats(0, "Not enough nr of seats");

                /* //if(rs.getString("date") != date){
                System.out.println("Tables available for date: ");
                System.out.print(rs.getInt("table_nr"));
                // System.out.print(" " + rs.getString("details"));
                System.out.println();*/
            }
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }

    }

    void selectBookingByDate2(String date)
    {
        int i=0;
        String query = "SELECT DISTINCT s.table_nr, s.details, b.date from seats s, booking b WHERE b.table_nr = s.table_nr";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             // PreparedStatement ps = connection.prepareStatement(query);
             Statement ps = connection.createStatement();
             // PreparedStatement ps = connection.prepareStatement(query);
        ) {

            //ResultSet rs = ps.executeQuery();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                i++;
                if(rs.getInt("table_nr")!=0 && rs.getString("details")!= null )
                {
                    if(rs.getString("date")!=date)
                    {
                        rts2[i]=new RowTableSeats(rs.getInt("table_nr"), rs.getString("details"));
                    }
                    else
                    {
                        rts2[i]=new RowTableSeats(rs.getInt("table_nr"), "Not free");
                    }
                }
                else {
                    rts2[i]=new RowTableSeats(rs.getInt("table_nr"), "Not free");

                }

                /* //if(rs.getString("date") != date){
                System.out.println("Tables available for date: ");
                System.out.print(rs.getInt("table_nr"));
                // System.out.print(" " + rs.getString("details"));
                System.out.println();*/
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    int selectBookingByName(String name, String date, String surname)
    {
        String query = "SELECT b.id_booking FROM booking b INNER JOIN customer c ON b.id_customer=c.id_customer WHERE c.name=? AND b.date = ? AND c.surname = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();

        ) {
            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3,surname);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                return rs.getInt("id_booking");
                //System.out.println("Tables available for "+ nr_seats);
               // System.out.print(rs.getInt("table_nr"));
                //System.out.print(" " + rs.getString("details"));
                // System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

   void choseFoodByMeal(String meal)
   {
       int i =0;
    String query = "SELECT * FROM menue_item ORDER BY meal_type";
       try(Connection connection = DriverManager.getConnection(url, user, password);
       Statement ps = connection.createStatement();

        ) {
       ResultSet rs = ps.executeQuery(query);
       while (rs.next()){

           i++;
           if(rs.getString("name")!=null && rs.getFloat("price")!=0 )
           {
               if(rs.getString("meal_type").equals(meal))
               {
                   rtm[i]=new RowTableMenue(rs.getString("name"),rs.getFloat("price"),rs.getInt("id_item"));
                  /* System.out.print(" " + rs.getString("name"));
                   System.out.print("Price: "+ rs.getInt("price"));
                   System.out.println();*/
               }
               else{
                   rtm[i]=new RowTableMenue(" ", 0,0);
               }
           }
           else{
               rtm[i]=new RowTableMenue(" ", 0,0);
           }

           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


   }

   int increseId(int max_id){
        String query = "SELECT MAX(id_customer) AS max FROM customer";

       try (Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(query);
            // Statement statement = connection.createStatement();

       ) {
           //ps.setString(1, meal);
           //ps.executeUpdate();
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {

               max_id = rs.getInt("max");
               return max_id+1;
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return 0;
   }

    int increseIdBooking(int max_id){
        String query = "SELECT MAX(id_booking) AS max FROM booking";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();

        ) {
            //ps.setString(1, meal);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                max_id = rs.getInt("max");
                return max_id+1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

      int id_order_by_booking(int booking)
      {
          String query = "SELECT o.id_order as orderi FROM orders o, booking b WHERE b.id_booking=o.booking_id AND booking_id = ?";
          try (Connection connection = DriverManager.getConnection(url, user, password);
               PreparedStatement ps = connection.prepareStatement(query);
               // Statement statement = connection.createStatement();

          ) {
              ps.setInt(1, booking);
              //ps.executeUpdate();
              ResultSet rs = ps.executeQuery();
              while (rs.next()) {
                  return rs.getInt("orderi");

              }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }

         return 0;
      }

      int idFoodByName(String name){
          String query = "SELECT id_item as item FROM menue_item WHERE name = ?";
          try (Connection connection = DriverManager.getConnection(url, user, password);
               PreparedStatement ps = connection.prepareStatement(query);
               // Statement statement = connection.createStatement();

          ) {
              ps.setString(1, name);
              //ps.executeUpdate();
              ResultSet rs = ps.executeQuery();
              while (rs.next()) {

                 return rs.getInt("item");
              }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
          return 0;
      }

      void insertInOrder(MenueItemOnOrder o)
      {
          String query = "INSERT INTO menue_item_on_order (menue_item_on_order_id, id_order, menue_item, quantity) VALUES (?,?,?,?)";
          try (Connection connection = DriverManager.getConnection(url, user, password);
               PreparedStatement ps = connection.prepareStatement(query);
          ) {
              ps.setInt(1, o.getMenue_item_on_order_id());
              ps.setInt(2,o.getId_ored());
              ps.setInt(3,o.getManue_item());
              ps.setInt(4,o.getQuantity());
              ps.executeUpdate();
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }

      }

      int calculateBill(int id_order)
      {
        String query = "SELECT SUM(m.price*mo.quantity) as bill from menue_item m, menue_item_on_order mo, orders o WHERE m.id_item=mo.menue_item AND mo.id_order=o.id_order AND O.id_order=?";

          try (Connection connection = DriverManager.getConnection(url, user, password);
               PreparedStatement ps = connection.prepareStatement(query);
               // Statement statement = connection.createStatement();

          ) {
              ps.setInt(1, id_order);
              //ps.executeUpdate();
              ResultSet rs = ps.executeQuery();
              while (rs.next()) {

                  return rs.getInt("bill");
              }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
          return 0;
      }

      void insertBill(Bill b) {

        String query = "INSERT INTO bills (id_bill, id_order, price) VALUES (?,?,?)";
          try (Connection connection = DriverManager.getConnection(url, user, password);
               PreparedStatement ps = connection.prepareStatement(query);
          ) {
              ps.setInt(1, b.getId_bill());
              ps.setInt(2,b.getId_order());
              ps.setFloat(3,b.getPrice());
              ps.executeUpdate();
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }

      }


    int increseIdMIOO(){
        String query = "SELECT MAX(menue_item_on_order_id) AS max FROM menue_item_on_order";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();

        ) {
            //ps.setString(1, meal);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return  rs.getInt("max")+1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    int increseIdOrder(){
        String query = "SELECT MAX(id_order) AS max FROM orders";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();

        ) {
            //ps.setString(1, meal);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return  rs.getInt("max")+1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    int increseIdBill(){
        String query = "SELECT MAX(id_bill) AS max FROM bills";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             // Statement statement = connection.createStatement();

        ) {
            //ps.setString(1, meal);
            //ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return  rs.getInt("max")+1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    void createOrder(int order_id, int booking_id)
    {
        String query = "INSERT INTO orders (id_order, booking_id) VALUES (?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, order_id);
            ps.setInt(2,booking_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void selectFoodByOrder(int id_order)
    {
        int i=0;
        String query ="SELECT io.*, m.* FROM menue_item_on_order io, menue_item m WHERE io.menue_item = m.id_item;";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             // PreparedStatement ps = connection.prepareStatement(query);
             Statement ps = connection.createStatement();
             // PreparedStatement ps = connection.prepareStatement(query);
        ) {

            //ResultSet rs = ps.executeQuery();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                i++;
                if(rs.getString("name")!=null && rs.getFloat("price")!=0 && rs.getInt("quantity")!=0){
                    if(rs.getInt("id_order")==id_order)
                    {
                        rto[i]=new RowTableOrder(rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
                    }
                    else
                    {
                        rto[i]=new RowTableOrder(" ", 0, 0);
                    }
                }
                else {
                    rto[i]=new RowTableOrder(" ", 0, 0);
                }

                /* //if(rs.getString("date") != date){
                System.out.println("Tables available for date: ");
                System.out.print(rs.getInt("table_nr"));
                // System.out.print(" " + rs.getString("details"));
                System.out.println();*/
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
