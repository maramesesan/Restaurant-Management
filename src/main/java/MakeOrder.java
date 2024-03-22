import Tables.MenueItemOnOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeOrder implements ActionListener {
    public static JFrame frame = new JFrame("Order");
    public static JFrame fBreakfast = new JFrame("Breakfast");
    public static JFrame fStarter = new JFrame("Starter");
    public static JFrame fMainc = new JFrame("Main Course");
    public static JFrame fDessert = new JFrame("Dessert");
    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String password = "1234567890";

    public static JLabel lBreakfast, lEggs, lPanc, lSand, lSoup, lSalad, lBrusc, lPork, lPot, lPasta;
    public static JLabel lCarrot, lCheese;
    public static JTextField tBreakfast, tEggs, tPanc, tSand, tSoup, tSalad, tBrusc, tPork, tPot, tPasta;
    public static JTextField tCarrot, tCheese;
    public static JRadioButton brk, back, strt, mcour, dess;
    public static JButton bEggs, bPanc, bSand, bSoup, bSalad, bBrusc, bPork, bPot, bPasta;
    public static JButton bCarrot, bCheese;
    public JTable tableb, tablet, tablem, tabled;
    public static int quantity, idBook, idOrder;
    public static String cName;
    public static JButton cart;

    public static ImageIcon eggs = new ImageIcon("eggs.jpg");
    public static ImageIcon pancakes = new ImageIcon("pancakes.jpg");
    public static ImageIcon sandwich = new ImageIcon("sandwich.png");
    public static ImageIcon soup = new ImageIcon("soup.png");
    public static ImageIcon salad = new ImageIcon("salad.png");
    public static ImageIcon bruschette = new ImageIcon("bruschette.jpg");
    public static ImageIcon pork = new ImageIcon("pork.png");
    public static ImageIcon potatos = new ImageIcon("potatoes.png");
    public static ImageIcon pasta = new ImageIcon("pasta.jpg");
    public static ImageIcon carrot = new ImageIcon("carrot.jpg");
    public static ImageIcon cheese = new ImageIcon("cheese.png");
    public static ImageIcon cartI = new ImageIcon("Cart.png");
    public static ImageIcon breakfastI = new ImageIcon("breakfast.png");
    public static ImageIcon starterI = new ImageIcon("starter.png");
    public static ImageIcon maincI = new ImageIcon("mainc.png");
    public static ImageIcon dessertI = new ImageIcon("dessert.png");
    public  String[][] rec;

    DBConnection dbc = new DBConnection(url,user,password);
    public enum Actions{
        BREAKFAST,
        STARTER,
        MAINCOURSE,
        DESSERT,
        MAINPAGE,
        ADDE,
        ADDPN,
        ADDSA,
        ADDSO,
        ADDSD,
        ADDB,
        ADDPO,
        ADDPOT,
        ADDPAS,
        ADDCAR,
        ADDCHE,

        CART

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand() == Actions.MAINCOURSE.name()) {
            fMainc.setVisible(true);
            back.setVisible(true);

            dbc.choseFoodByMeal("main course");
            rec=new String[][]{
                    {dbc.rtm[7].getName(), String.valueOf(dbc.rtm[7].getPrice())},
                    {dbc.rtm[8].getName(), String.valueOf(dbc.rtm[8].getPrice())},
                    {dbc.rtm[6].getName(), String.valueOf(dbc.rtm[10].getPrice())},
                   /// {dbc.rtm[4].getName(), String.valueOf(dbc.rtm[4].getPrice())}
            };

            String []title={"Food", "Price"};

            tablem=new JTable(rec, title);
            tablem.setBounds(900,250,200,100);
           fMainc.add(tablem);

        }

        if(e.getActionCommand() == Actions.BREAKFAST.name()) {

            int i;
            back.setVisible(true);

            dbc.choseFoodByMeal("breakfast");

                rec=new String[][]{
                        {dbc.rtm[1].getName(), String.valueOf(dbc.rtm[1].getPrice())},
                        {dbc.rtm[2].getName(), String.valueOf(dbc.rtm[2].getPrice())},
                        {dbc.rtm[3].getName(), String.valueOf(dbc.rtm[3].getPrice())},
                        //{dbc.rtm[4].getName(), String.valueOf(dbc.rtm[4].getPrice())}
                };

            String []title={"Food", "Price"};

            tableb=new JTable(rec, title);
            tableb.setBounds(1000,250,200,300);
            fBreakfast.add(tableb);
        }

        if(e.getActionCommand() == Actions.STARTER.name()) {
            fStarter.setVisible(true);
            back.setVisible(true);

            dbc.choseFoodByMeal("starter");
            String[][] rec=new String[][]{
                    {dbc.rtm[9].getName(), String.valueOf(dbc.rtm[9].getPrice())},
                    {dbc.rtm[10].getName(), String.valueOf(dbc.rtm[10].getPrice())},
                    {dbc.rtm[11].getName(), String.valueOf(dbc.rtm[11].getPrice())},
                   // {dbc.rtm[4].getName(), String.valueOf(dbc.rtm[4].getPrice())}
            };

            String []title={"Food", "Price"};

            tablet=new JTable(rec, title);
            tablet.setBounds(900,250,200,100);
            fStarter.add(tablet);

        }

         if(e.getActionCommand() == Actions.DESSERT.name()) {
            fDessert.setVisible(true);
            back.setVisible(true);

            dbc.choseFoodByMeal("dessert");
            String[][] rec=new String[][]{
                    {dbc.rtm[4].getName(), String.valueOf(dbc.rtm[4].getPrice())},
                    {dbc.rtm[5].getName(), String.valueOf(dbc.rtm[5].getPrice())},
                    //{ dbc.rtm[6].getName(), String.valueOf(dbc.rtm[6].getPrice())},
                   // {dbc.rtm[4].getName(), String.valueOf(dbc.rtm[4].getPrice())}
            };

            String []title={"Food", "Price"};

            tabled=new JTable(rec, title);
            tabled.setBounds(900,250,200,100);
            fDessert.add(tabled);

        }

        if(e.getActionCommand() == Actions.MAINPAGE.name())
        {
            frame.setVisible(true);
        }

        if(e.getActionCommand() == Actions.ADDE.name())
        {
            String text = tEggs.getText();
            quantity = Integer.parseInt(text);
            System.out.println(quantity);
           String aux = lEggs.getText();
            System.out.println(aux);

            int id_itemorder = dbc.increseIdMIOO();
            int item;
            item = dbc.idFoodByName(aux);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder,hm.idOrder,item,quantity);
            dbc.insertInOrder(mo);
        }

        if(e.getActionCommand() == Actions.ADDPN.name())
        {
            String text2 = tPanc.getText();
            quantity = Integer.parseInt(text2);
            System.out.println(quantity);
            String aux2 = lPanc.getText();
            System.out.println(aux2);

            int id_itemorder2 = dbc.increseIdMIOO();
            int item2;
            item2 = dbc.idFoodByName(aux2);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder2,hm.idOrder,item2,quantity);
            dbc.insertInOrder(mo);
        }
        if(e.getActionCommand() == Actions.ADDSA.name())
        {
            String text3 = tSand.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lSand.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO();
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }
        if(e.getActionCommand() == Actions.ADDSO.name())
        {
            String text3 = tSoup.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lSoup.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO()+1;
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }

        if(e.getActionCommand() == Actions.ADDSD.name())
        {
            String text3 = tSalad.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lSalad.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO();
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }
        if(e.getActionCommand() == Actions.ADDB.name())
        {
            String text3 = tBrusc.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lBrusc.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO();
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }
        if(e.getActionCommand() == Actions.ADDPO.name())
        {
            String text3 = tPork.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lPork.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO();
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }
        if(e.getActionCommand() == Actions.ADDPOT.name())
        {
            String text3 = tPot.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lPot.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO();
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }
        if(e.getActionCommand() == Actions.ADDPAS.name())
        {
            String text3 = tPasta.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lPasta.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO();
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }
        if(e.getActionCommand() == Actions.ADDCAR.name())
        {
            String text3 = tCarrot.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lCarrot.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO();
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }
        if(e.getActionCommand() == Actions.ADDCHE.name())
        {
            String text3 = tCheese.getText();
            quantity = Integer.parseInt(text3);
            System.out.println(quantity);
            String aux3 = lCheese.getText();
            System.out.println(aux3);

            int id_itemorder3 = dbc.increseIdMIOO();
            int item3;
            item3 = dbc.idFoodByName(aux3);

            HomeMenue hm = new HomeMenue();
            MenueItemOnOrder mo = new MenueItemOnOrder(id_itemorder3,hm.idOrder,item3,quantity);
            dbc.insertInOrder(mo);
        }
    }


  //public static void main(String[] args) {

      public void Order(){

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

        brk=new JRadioButton("Breakfast");
        brk.setIcon(breakfastI);
        brk.setBounds(100,250,200,200);
        MakeOrder instance=new MakeOrder();//pentru a apela functia la click
        brk.setActionCommand(Actions.BREAKFAST.name());
        brk.addActionListener(instance);
        frame.add(brk);
        brk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                fBreakfast.setVisible(true);
            }
        });

        strt=new JRadioButton("Starter");
        strt.setIcon(starterI);
        strt.setBounds(400,250,200,200);
        MakeOrder instance3=new MakeOrder();//pentru a apela functia la click
        strt.setActionCommand(Actions.STARTER.name());
        strt.addActionListener(instance3);
        frame.add(strt);
        strt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                fStarter.setVisible(true);
            }
        });

        mcour=new JRadioButton("Main Course");
        mcour.setIcon(maincI);
        mcour.setBounds(700,250,200,200);
        MakeOrder instance5=new MakeOrder();//pentru a apela functia la click
        mcour.setActionCommand(Actions.MAINCOURSE.name());
        mcour.addActionListener(instance5);
        frame.add(mcour);
        mcour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                fMainc.setVisible(true);
            }
        });

        dess=new JRadioButton("Dessert");
        dess.setBounds(1000,250,200,200);
        dess.setIcon(dessertI);
        MakeOrder instance6=new MakeOrder();//pentru a apela functia la click
        dess.setActionCommand(Actions.DESSERT.name());
        dess.addActionListener(instance6);
        frame.add(dess);

        dess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                fDessert.setVisible(true);
            }
        });

        //breakfast
        back=new JRadioButton("BACK");
        back.setBounds(100,100,100,30);
        MakeOrder instance2=new MakeOrder();//pentru a apela functia la click
        back.setActionCommand(Actions.MAINPAGE.name());
        back.addActionListener(instance2);
        fBreakfast.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                fBreakfast.setVisible(false);
            }
        });
//EGGS
        lEggs = new JLabel("Eggs");
        lEggs.setBounds(100,180, 300,200);
        lEggs.setIcon(eggs);
        fBreakfast.add(lEggs);

        tEggs=new JTextField("");  //text box
        tEggs.setBounds(320,200, 50,30);
       fBreakfast.add(tEggs);
        //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tEggs.setColumns(20);

        bEggs=new JButton("ADD");
        bEggs.setBounds(390,200,80,30);
        MakeOrder instance4=new MakeOrder();//pentru a apela functia la click
        bEggs.setActionCommand(Actions.ADDE.name());
        bEggs.addActionListener(instance4);
       fBreakfast.add(bEggs);

       bEggs.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //clear
           }
       });

//pancakes

       lPanc = new JLabel("Pancakes");
       lPanc.setBounds(480,180, 300,200);
       lPanc.setIcon(pancakes);
       fBreakfast.add(lPanc);

       tPanc=new JTextField("");  //text box
       tPanc.setBounds(680,200, 50,30);
       fBreakfast.add(tPanc);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
       tPanc.setColumns(20);

       bPanc=new JButton("ADD");
       bPanc.setBounds(750,200,80,30);
       MakeOrder instancepanc=new MakeOrder();//pentru a apela functia la click
       bPanc.setActionCommand(Actions.ADDPN.name());
       bPanc.addActionListener(instancepanc);
       fBreakfast.add(bPanc);

//sandwich
       lSand = new JLabel("Sandwich");
       lSand.setBounds(100,450, 200,200);
      // lsand.setLocation(500,180);
       lSand.setIcon(sandwich);
       fBreakfast.add(lSand);

       tSand=new JTextField("");  //text box
       tSand.setBounds(320,480, 50,30);
       fBreakfast.add(tSand);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
      tSand.setColumns(20);

      bSand=new JButton("ADD");
      bSand.setBounds(390,480,80,30);
       MakeOrder instancesand=new MakeOrder();//pentru a apela functia la click
      bSand.setActionCommand(Actions.ADDSA.name());
      bSand.addActionListener(instancesand);
       fBreakfast.add(bSand);



        //starter
        back=new JRadioButton("BACK");
        back.setBounds(100,100,100,30);
        MakeOrder instanceb2=new MakeOrder();//pentru a apela functia la click
        back.setActionCommand(Actions.MAINPAGE.name());
        back.addActionListener(instanceb2);
        fStarter.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                fStarter.setVisible(false);
            }
        });


        //soup
       lSoup = new JLabel("Soup");
       lSoup.setBounds(100,180, 300,200);
       lSoup.setIcon(soup);
       fStarter.add(lSoup);

       tSoup=new JTextField("");  //text box
       tSoup.setBounds(320,200, 50,30);
       fStarter.add(tSoup);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
       tSoup.setColumns(20);

       bSoup=new JButton("ADD");
       bSoup.setBounds(390,200,80,30);
       MakeOrder instancesoup=new MakeOrder();//pentru a apela functia la click
       bSoup.setActionCommand(Actions.ADDSO.name());
       bSoup.addActionListener(instancesoup);
       fStarter.add(bSoup);

       bSoup.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //clear
           }
       });

//salad
       lSalad = new JLabel("Salad");
       lSalad.setBounds(600,180, 300,200);
       lSalad.setIcon(salad);
       fStarter.add(lSalad);

       tSalad=new JTextField("");  //text box
       tSalad.setBounds(910,200, 50,30);
       fStarter.add(tSalad);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
       tSoup.setColumns(20);

       bSalad=new JButton("ADD");
       bSalad.setBounds(980,200,80,30);
       MakeOrder instancesal=new MakeOrder();//pentru a apela functia la click
       bSalad.setActionCommand(Actions.ADDSD.name());
       bSalad.addActionListener(instancesal);
       fStarter.add(bSalad);


       //bruschette

        lBrusc= new JLabel("Bruschette");
       lBrusc.setBounds(100,450, 300,200);
       lBrusc.setIcon(bruschette);
       fStarter.add(lBrusc);

       tBrusc=new JTextField("");  //text box
      tBrusc.setBounds(300,450, 50,30);
      fStarter.add(tBrusc);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
      tBrusc.setColumns(20);

       bBrusc=new JButton("ADD");
      bBrusc.setBounds(370,450,80,30);
       MakeOrder instancebsc=new MakeOrder();//pentru a apela functia la click
      bBrusc.setActionCommand(Actions.ADDB.name());
      bBrusc.addActionListener(instancebsc);
      fStarter.add(bBrusc);


       //main course
        back=new JRadioButton("BACK");
        back.setBounds(100,100,100,30);
        MakeOrder instanceb3=new MakeOrder();//pentru a apela functia la click
        back.setActionCommand(Actions.MAINPAGE.name());
        back.addActionListener(instanceb3);
        fMainc.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                fMainc.setVisible(false);
            }
        });

        //pork meat
       lPork = new JLabel("Pork Meat");
      lPork.setBounds(100,180, 300,200);
      lPork.setIcon(pork);
      fMainc.add(lPork);

       tPork=new JTextField("");  //text box
      tPork.setBounds(410,200, 50,30);
      fMainc.add(tPork);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
      tPork.setColumns(20);

       bPork=new JButton("ADD");
      bPork.setBounds(480,200,80,30);
       MakeOrder instancepork=new MakeOrder();//pentru a apela functia la click
      bPork.setActionCommand(Actions.ADDPO.name());
      bPork.addActionListener(instancepork);
      fMainc.add(bPork);

       //potatos
      lPot = new JLabel("Sweet potatoes");
       lPot.setBounds(580,180, 300,200);
      lPot.setIcon(potatos);
      fMainc.add(lPot);

       tPot=new JTextField("");  //text box
      tPot.setBounds(890,200, 50,30);
      fMainc.add(tPot);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
      tPot.setColumns(20);

       bPot=new JButton("ADD");
      bPot.setBounds(960,200,80,30);
       MakeOrder instancepot=new MakeOrder();//pentru a apela functia la click
      bPot.setActionCommand(Actions.ADDPOT.name());
      bPot.addActionListener(instancepot);
      fMainc.add(bPot);

       //pasta
       lPasta = new JLabel("Pasta");
      lPasta.setBounds(100,450, 300,200);
      lPasta.setIcon(pasta);
       fMainc.add(lPasta);

       tPasta=new JTextField("");  //text box
      tPasta.setBounds(300,470, 50,30);
      fMainc.add(tPasta);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
      tPasta.setColumns(20);

       bPasta=new JButton("ADD");
      bPasta.setBounds(370,470,80,30);
       MakeOrder instancepas=new MakeOrder();//pentru a apela functia la click
      bPasta.setActionCommand(Actions.ADDPAS.name());
      bPasta.addActionListener(instancepas);
      fMainc.add(bPasta);


        //dessert
        back=new JRadioButton("BACK");
        back.setBounds(100,100,100,30);
        MakeOrder instanceb4=new MakeOrder();//pentru a apela functia la click
        back.setActionCommand(Actions.MAINPAGE.name());
        back.addActionListener(instanceb4);
        fDessert.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                fDessert.setVisible(false);
            }
        });

        //carrot cake
       lCarrot = new JLabel("Carrot Cake");
      lCarrot.setBounds(100,180, 300,200);
      lCarrot.setIcon(carrot);
      fDessert.add(lCarrot);

      tCarrot=new JTextField("");  //text box
      tCarrot.setBounds(350,200, 50,30);
      fDessert.add(tCarrot);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
      tCarrot.setColumns(20);

       bCarrot=new JButton("ADD");
      bCarrot.setBounds(420,200,80,30);
       MakeOrder instancecarr=new MakeOrder();//pentru a apela functia la click
      bCarrot.setActionCommand(Actions.ADDCAR.name());
      bCarrot.addActionListener(instancecarr);
      fDessert.add(bCarrot);

       //cheesecake
       lCheese = new JLabel("Cheesecake");
      lCheese.setBounds(580,180, 300,200);
      lCheese.setIcon(cheese);
       fDessert.add(lCheese);

       tCheese=new JTextField("");  //text box
      tCheese.setBounds(890,200, 50,30);
      fDessert.add(tCheese);
       //name.setFont(new Font("Tahoma", Font.PLAIN, 32));
      tCheese.setColumns(20);

       bCheese=new JButton("ADD");
      bCheese.setBounds(960,200,80,30);
       MakeOrder instancecheese=new MakeOrder();//pentru a apela functia la click
      bCheese.setActionCommand(Actions.ADDCHE.name());
      bCheese.addActionListener(instancecheese);
       fDessert.add(bCheese);




        //frames

        frame.setSize(1600,850);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fBreakfast.setSize(1600,850);
      fBreakfast.setLayout(null);
      fBreakfast.setVisible(false);
      fBreakfast.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fStarter.setSize(1600,850);
      fStarter.setLayout(null);
      fStarter.setVisible(false);
      fStarter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        fMainc.setSize(1600,850);                //frame
      fMainc.setLayout(null);
      fMainc.setVisible(false);
      fMainc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fDessert.setSize(1600,850);                //frame
      fDessert.setLayout(null);
      fDessert.setVisible(false);
      fDessert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
