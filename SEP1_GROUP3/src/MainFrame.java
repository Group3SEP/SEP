import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;


public class MainFrame extends JFrame
{

   private static final long serialVersionUID = 1L;

   JFrame frame;

   private ImageIcon bannerimg = new ImageIcon(getClass().getResource("banner.jpg"));
   private ImageIcon intropic = new ImageIcon(getClass().getResource("intropic.jpg"));
   private ImageIcon backimg = new ImageIcon(getClass().getResource("backbutton.jpg"));
   private JPanel window = new JPanel();
   private JPanel intro = new JPanel();
   private JPanel lend = new JPanel();
   private JPanel search = new JPanel();
   private JPanel add = new JPanel();
   private JPanel unregister = new JPanel();
   private JPanel returnPanel = new JPanel();
   private JPanel makeReservationPanel = new JPanel();
   private JPanel showReservationsPanel = new JPanel();
   private JPanel deleteReservationPanel = new JPanel();
   private JPanel addBookPanel = new JPanel();
   private JPanel addCdPanel = new JPanel();
   private JPanel addDvdPanel = new JPanel();
   private JPanel addArticlePanel = new JPanel();
   private JPanel searchItem = new JPanel(); 
   private JPanel searchCustomer = new JPanel(); 
   private JPanel itemFound = new JPanel();
   private JPanel customerFound = new JPanel();  
   private JPanel searchOptions = new JPanel(); 
   private JPanel register = new JPanel();
   private JPanel notificationsPanel = new JPanel();
   private JTextField bookTitleField;
   private JTextField bookAuthorField;
   private JTextField bookYearField;
   private JTextField bookIsbnField;
   private JTextField cdTitleField;
   private JTextField cdAuthorField;
   private JTextField cdYearField;
   private JTextField dvdTitleField;
   private JTextField dvdAuthorField;
   private JTextField dvdYearField;
   private JTextField articleTitleField;
   private JTextField articleAuthorField;
   private JTextField articleYearField;
   private JTextField articleMonthField;
   private JTextField textFieldCustomer;
   private JTextField textFieldItem;
   private JTextField nameTextField;
   private JTextField cprTextField;
   private JTextField lendCprTextField = new JTextField();
   private JTextField lendTitleTextField = new JTextField();
   private JTextField reservationCprTextField = new JTextField();
   private JTextField reservationTitleTextField = new JTextField(); 
   private JTextField reservationShowCprTextField = new JTextField();
   private JTextField reservationDeleteCprTextField = new JTextField();
   private JTextField reservationDeleteTitleTextField = new JTextField(); 
   private JTextField returnCprTextField = new JTextField();
   private JTextField returnTitleTextField = new JTextField();
   private JTextField unregisterCprTextField = new JTextField();
   private JTextArea textArea = new JTextArea();
   private JTextArea searchTextArea = new JTextArea();
   private JTextArea lendTextArea = new JTextArea();
   private JTextArea registerTextArea = new JTextArea();
   private JTextArea unregisterTextArea = new JTextArea();
   private JTextArea reservationTextArea = new JTextArea();
   private JTextArea reservationShowTextArea = new JTextArea();
   private JTextArea reservationDeleteTextArea = new JTextArea();
   private JTextArea returnTextArea = new JTextArea();
   private JTextArea notificationsTextArea = new JTextArea();
   private JLabel lblFoundCustomerName = new JLabel();
   private JLabel lblItemFound = new JLabel("Item Found");
   private JToggleButton tglItem = new JToggleButton();
   private JToggleButton tglCustomer = new JToggleButton();
   private String x = null;
   private String customerType;
   private JComboBox<String> addTypeComboBox;
   private JSpinner spinner = new JSpinner();
   private int copies = 1;
   private Customer temporaryCustomer = new Customer("","","");
   private Item temporaryItem = new Book("","",0,"");

   Items database = new Items();
   Customers customers = new Customers();
   List<Date> myList = new ArrayList<Date>();


   public MainFrame() throws ClassNotFoundException, IOException
   {
      loadAndStart();

      initialize();

      introPanel();
      lendPanel();
      addPanel();
      searchPanel();
      registerPanel();
      unregisterPanel();
      returnPanel();
      makeReservationPanel();
      showReservationsPanel();
      deleteReservationPanel();
      notificationsPanel();
   }


   private void saveAndClose() throws IOException
   {
      try
      {
         FileOutputStream fos = new FileOutputStream("customers database");
         ObjectOutputStream oos = new ObjectOutputStream(fos);

         oos.writeObject(customers);

         oos.close();
      }

      catch (FileNotFoundException e)
      {
         System.out.println("no file");
      }

      try
      {
         FileOutputStream fos1 = new FileOutputStream("items database");
         ObjectOutputStream oos1 = new ObjectOutputStream(fos1);

         oos1.writeObject(database);

         oos1.close();
      }

      catch (FileNotFoundException e)
      {
         System.out.println("no file");
      }

      System.out.println("SAVED");
   }

   private void loadAndStart() throws IOException, ClassNotFoundException
   {
      try {
         FileInputStream is = new FileInputStream("customers database");
         ObjectInputStream in = new ObjectInputStream(is);

         Object cus1 = in.readObject();
         customers = (Customers)cus1;

         in.close();
      } 

      catch (FileNotFoundException e) 
      {
         System.out.println("No file");
      }

      try 
      {
         FileInputStream is = new FileInputStream("items database");
         ObjectInputStream in = new ObjectInputStream(is);

         Object items1 = in.readObject();
         database = (Items)items1;

         in.close();

      } 

      catch (FileNotFoundException e) 
      {
         System.out.println("No file");
      }

      System.out.println("LOAD");
   }

   public void initialize()
   {

      Runtime.getRuntime().addShutdownHook(new Thread()	                                                    //Save on close
      {
         @Override
         public void run()
         {
            try
            {
               saveAndClose();
            }
            catch (IOException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      });


      frame = new JFrame();
      frame.setResizable(false);
      frame.setBounds(100, 100, 960, 540);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new BorderLayout(0, 0));
      frame.setIconImage(Toolkit.getDefaultToolkit().getImage("iconimg.png"));
      frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("iconimg.png")));

      JPanel banner = new JPanel();
      frame.getContentPane().add(banner, BorderLayout.NORTH);  
      JLabel bannertop = new JLabel("", bannerimg, JLabel.CENTER);
      banner.add(bannertop, BorderLayout.CENTER );

      JPanel content = new JPanel();
      frame.getContentPane().add(content, BorderLayout.CENTER);
      content.setLayout(null);

      JPanel footer = new JPanel();
      footer.setBounds(10, 410, 934, 30);
      content.add(footer);
      footer.setLayout(null);

      JLabel lblFooter = new JLabel("\u00A9S&S  ");
      lblFooter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
      lblFooter.setBounds(888, 11, 46, 14);
      footer.add(lblFooter);


      JPanel items = new JPanel();
      items.setBounds(10, 11, 190, 384);
      items.setBorder(new TitledBorder("ITEM"));
      content.add(items);
      items.setLayout(null);

      JButton btnAdd = new JButton("Add");
      btnAdd.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showAdd();
            textArea.setText("");
         }
      });
      btnAdd.setBounds(10, 245, 170, 50);
      items.add(btnAdd);

      JButton btnLend = new JButton("Lend");
      btnLend.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showLend();
         }
      });
      btnLend.setBounds(10, 26, 170, 50);
      items.add(btnLend);

      JButton btnReturn = new JButton("Return");
      btnReturn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showReturn();
         }
      });
      btnReturn.setBounds(10, 87, 170, 50);
      items.add(btnReturn);


      JButton btnNotifications = new JButton("NOTIFICATIONS");
      btnNotifications.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showNotifications();
         }
      });
      btnNotifications.setBounds(10, 169, 170, 50);
      btnNotifications.setBorder(new LineBorder(Color.BLUE, 3));
      items.add(btnNotifications);


      JButton btnSearch = new JButton("Search");
      btnSearch.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showSearch();
         }
      });
      btnSearch.setBounds(10, 306, 170, 50);
      items.add(btnSearch);




      JPanel customer = new JPanel();
      customer.setBorder(new TitledBorder("CUSTOMER"));
      customer.setBounds(227, 11, 190, 162);
      content.add(customer);
      customer.setLayout(null);

      JButton btnRegister = new JButton("Register");
      btnRegister.setBounds(10, 24, 170, 50);
      btnRegister.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showRegister();
         }
      });
      customer.add(btnRegister);



      JButton btnUnregister = new JButton("Unregister");
      btnUnregister.setBounds(10, 85, 170, 50);
      btnUnregister.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showUnregister();
         }
      });
      customer.add(btnUnregister);




      JPanel reservation = new JPanel();
      reservation.setBorder(new TitledBorder("RESERVATION"));
      reservation.setBounds(227, 184, 190, 209);
      content.add(reservation);
      reservation.setLayout(null);

      JButton btnMake = new JButton("Make");
      btnMake.setBounds(10, 29, 170, 50);
      btnMake.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showMakeReservation();
         }
      });
      reservation.add(btnMake);



      JButton btnShow = new JButton("Show");
      btnShow.setBounds(10, 90, 170, 50);
      btnShow.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showShowReservations();
         }
      });
      reservation.add(btnShow);


      JButton btnDelete = new JButton("Delete");
      btnDelete.setBounds(10, 151, 170, 50);
      btnDelete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showDeleteReservation();
         }
      });
      reservation.add(btnDelete);


      window.setBounds(449, 19, 485, 372);
      content.add(window);
      window.setLayout(null);
      search.setBorder(new LineBorder(Color.DARK_GRAY, 2));
   }


   private void searchPanel()
   {
      //SEARCH
      search.setBounds(0, 0, 485, 372);
      window.add(search);
      search.setVisible(false);
      search.setLayout(null);

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      search.add(backButton);



      customerFound.setBounds(10, 11, 465, 188);
      search.add(customerFound);
      customerFound.setVisible(false);
      customerFound.setLayout(null);

      JButton btnFoundShowReservations = new JButton("Show Reservations");
      btnFoundShowReservations.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (temporaryCustomer.getNumberOfReservations()==0)
               searchTextArea.setText("No reservations to show.");
            else{
               searchTextArea.setText(temporaryCustomer.reservationsToString());
               searchTextArea.setCaretPosition(0);
            }
         }
      });
      btnFoundShowReservations.setBounds(10, 73, 177, 51);
      customerFound.add(btnFoundShowReservations);

      JButton btnFoundUnregister = new JButton("Unregister");
      btnFoundUnregister.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            searchTextArea.setText("");
            searchTextArea.setText(temporaryCustomer.getName() + " has been deleted.");
            searchTextArea.setCaretPosition(0);
            customers.unregisterCustomer(temporaryCustomer);      

         }
      });
      btnFoundUnregister.setBounds(10, 137, 177, 51);
      customerFound.add(btnFoundUnregister);

      JButton btnFoundShowLentBooks = new JButton("Lent Items");
      btnFoundShowLentBooks.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            searchTextArea.setText("");
            if(temporaryCustomer.getNumberOfBorrowings()==0)
               searchTextArea.setText("No lent items to show.");
            else{   
               searchTextArea.setText(temporaryCustomer.borrowingsToString());
               searchTextArea.setCaretPosition(0);
            };
         }
      });
      btnFoundShowLentBooks.setBounds(278, 73, 177, 51);
      customerFound.add(btnFoundShowLentBooks);



      JButton btnFoundLend = new JButton("Lend an Item");
      btnFoundLend.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            searchTextArea.setText("");
            showLend();
            lendCprTextField.setText(temporaryCustomer.getCpr());

         }
      });
      btnFoundLend.setBounds(278, 137, 177, 51);
      customerFound.add(btnFoundLend);



      lblFoundCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
      lblFoundCustomerName.setFont(new Font("Tahoma", Font.BOLD, 13));
      lblFoundCustomerName.setBounds(10, 11, 445, 51);
      customerFound.add(lblFoundCustomerName);






      itemFound.setBounds(10, 11, 465, 188);
      search.add(itemFound);
      itemFound.setVisible(false);
      itemFound.setLayout(null);

      JButton btnSearchLend = new JButton("Lend\r\n");
      btnSearchLend.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnSearchLend.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            searchTextArea.setText("");
            showLend();
            lendTitleTextField.setText(temporaryItem.getTitle());
         }
      });
      btnSearchLend.setBounds(10, 73, 177, 51);
      itemFound.add(btnSearchLend);




      JButton btnSearchReserve = new JButton("Make Reservation");
      btnSearchReserve.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showMakeReservation();
            reservationTitleTextField.setText(temporaryItem.getTitle());
         }
      });
      btnSearchReserve.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnSearchReserve.setBounds(10, 137, 177, 51);
      itemFound.add(btnSearchReserve);



      JButton btnSearchDelete = new JButton("Delete");
      btnSearchDelete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            database.removeAnItem(database.searchAnItemAndGetIndex(temporaryItem.getTitle()));
            searchTextArea.setText(temporaryItem.getTitle() + " was deleted from your database.");
            searchTextArea.setCaretPosition(0);
         }
      });
      btnSearchDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnSearchDelete.setBounds(278, 137, 177, 51);
      itemFound.add(btnSearchDelete);

      JButton btnSearchAgain = new JButton("Search Again");
      btnSearchAgain.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showSearch();
         }
      });
      btnSearchAgain.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnSearchAgain.setBounds(278, 73, 177, 51);
      itemFound.add(btnSearchAgain);


      lblItemFound.setHorizontalAlignment(SwingConstants.CENTER);
      lblItemFound.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblItemFound.setBounds(10, 11, 445, 51);
      itemFound.add(lblItemFound);










      searchOptions.setBounds(10, 11, 465, 90);
      search.add(searchOptions);
      searchOptions.setLayout(null);
      searchOptions.setVisible(true);
      tglItem.setBounds(0, 47, 200, 30);
      searchOptions.add(tglItem);


      tglItem.setText("Item");
      tglItem.setSelected(true);
      tglCustomer.setBounds(265, 47, 200, 30);
      searchOptions.add(tglCustomer);


      tglCustomer.setText("Customer");

      JLabel lblSearch = new JLabel("SEARCH");
      lblSearch.setBounds(157, 0, 151, 45);
      searchOptions.add(lblSearch);
      lblSearch.setFont(new Font("Tahoma", Font.BOLD, 37));
      tglCustomer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            tglCustomer.setSelected(true);
            tglItem.setSelected(false);
            searchItem.setVisible(false);
            searchCustomer.setVisible(true);

         }
      });

      tglItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            tglItem.setSelected(true);
            tglCustomer.setSelected(false);
            searchItem.setVisible(true);
            searchCustomer.setVisible(false);
         }
      });


      searchCustomer.setBounds(10, 102, 465, 97);
      search.add(searchCustomer);
      searchCustomer.setLayout(null);
      searchCustomer.setVisible(false);



      JButton btnSearchCustomer = new JButton("Search");
      btnSearchCustomer.setBounds(190, 63, 89, 23);
      searchCustomer.add(btnSearchCustomer);
      btnSearchCustomer.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnSearchCustomer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (customers.searchByCPR(textFieldCustomer.getText()).getName().equals("cx451df5vc"))
            {
               searchTextArea.setText("Customer Not Found.");
               textFieldCustomer.setText("");
            }

            else
            {
               searchTextArea.setText(customers.searchByCPR(textFieldCustomer.getText()).toString());
               lblFoundCustomerName.setText(customers.searchByCPR(textFieldCustomer.getText()).getName());
               temporaryCustomer = customers.searchByCPR(textFieldCustomer.getText());
               customerFound();
               searchTextArea.setCaretPosition(0);
            }
         }
      });

      JLabel lblCpr = new JLabel("CPR Number");
      lblCpr.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblCpr.setBounds(41, 13, 90, 14);
      searchCustomer.add(lblCpr);


      textFieldCustomer = new JTextField();
      textFieldCustomer.setBounds(141, 11, 296, 20);
      searchCustomer.add(textFieldCustomer);
      textFieldCustomer.setColumns(10);





      searchItem.setBounds(10, 102, 465, 97);
      search.add(searchItem);
      searchItem.setLayout(null);
      searchItem.setVisible(true);





      JButton btnSearchItem = new JButton("Search");
      btnSearchItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (database.searchAnItem(textFieldItem.getText()).getTitle().equals("no match found"))
            {
               searchTextArea.setText("Title Not Found.");
            }
            else
            {
               searchTextArea.setText(database.searchAnItem(textFieldItem.getText()).toString());  
               lblItemFound.setText(database.searchAnItem(textFieldItem.getText()).getTitle());
               temporaryItem = database.searchAnItem(textFieldItem.getText());
               itemFound();
               searchTextArea.setCaretPosition(0);
            }
         }
      });

      btnSearchItem.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnSearchItem.setBounds(190, 63, 89, 23);
      searchItem.add(btnSearchItem);

      JLabel lblTitle = new JLabel("Title");
      lblTitle.setBounds(73, 13, 46, 14);
      searchItem.add(lblTitle);
      lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));

      textFieldItem = new JTextField();
      textFieldItem.setColumns(10);
      textFieldItem.setBounds(141, 11, 296, 20);
      searchItem.add(textFieldItem);


      JScrollPane scrollPane = new JScrollPane();
      searchTextArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(searchTextArea); 
      search.add(scrollPane);

   }

   private void introPanel()
   {
      //INTRO
      intro.setBounds(0, 0, 485, 372);
      window.add(intro);
      intro.setVisible(true);
      intro.setLayout(new BorderLayout(0, 0));
      JLabel intropicture = new JLabel("", intropic, JLabel.CENTER);
      intro.add(intropicture, BorderLayout.CENTER );
      lend.setBorder(new LineBorder(Color.DARK_GRAY, 2));
   }

   private void lendPanel()
   {
      //LEND
      lend.setBounds(0, 0, 485, 372);
      window.add(lend);
      lend.setVisible(false);
      lend.setLayout(null);

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      lend.add(backButton);


      JLabel lblLendAnItem = new JLabel("Lend an Item");
      lblLendAnItem.setHorizontalAlignment(SwingConstants.CENTER);
      lblLendAnItem.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblLendAnItem.setBounds(10, 11, 445, 31);
      lend.add(lblLendAnItem);

      JLabel lblLendCpr = new JLabel("CPR");
      lblLendCpr.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblLendCpr.setBounds(29, 72, 46, 14);
      lend.add(lblLendCpr);

      JLabel lblLendTitle = new JLabel("Title");
      lblLendTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblLendTitle.setBounds(29, 97, 46, 14);
      lend.add(lblLendTitle);


      lendCprTextField.setBounds(74, 70, 227, 20);
      lend.add(lendCprTextField);
      lendCprTextField.setColumns(10);


      lendTitleTextField.setColumns(10);
      lendTitleTextField.setBounds(74, 97, 227, 20);
      lend.add(lendTitleTextField);

      JButton btnLendAnItem = new JButton("Lend an Item");
      btnLendAnItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (customers.searchByCPR(lendCprTextField.getText()).getName().equals("cx451df5vc") || database.searchAnItem(lendTitleTextField.getText()).getTitle().equals("no match found"))
            {
               lendTextArea.setText("Enter correct CPR and a title");
            }
            else
            {

               customers.searchByCPR(lendCprTextField.getText()).newBorrowing("", database.searchAnItem(lendTitleTextField.getText()));

               if (customers.searchByCPR(lendCprTextField.getText()).borrowingsToString().contains(database.searchAnItem(lendTitleTextField.getText()).getTitle()))
               {
                  lendTextArea.setText("You've lent "+database.searchAnItem(lendTitleTextField.getText()).getTitle()
                        + " to "+ customers.searchByCPR(lendCprTextField.getText()).getName());
               }
               else
               {
                  lendTextArea.setText("No more copies available.");
               }

            }

            lendTitleTextField.setText("");
            lendCprTextField.setText("");
            lendTextArea.setCaretPosition(0);


         }
      });
      btnLendAnItem.setFont(new Font("Tahoma", Font.BOLD, 13));
      btnLendAnItem.setBounds(311, 70, 144, 48);
      lend.add(btnLendAnItem);



      JScrollPane scrollPane = new JScrollPane();
      lendTextArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(lendTextArea); 
      lend.add(scrollPane);
   }

   private void addPanel()
   {
      //ADD
      add.setBounds(0, 0, 485, 372);
      window.add(add);
      add.setVisible(false);
      add.setLayout(null);
      add.setBorder(new LineBorder(Color.DARK_GRAY, 2));


      addBookPanel.setBounds(20, 21, 206, 177);
      add.add(addBookPanel);
      addBookPanel.setLayout(null);

      JLabel bookTitle = new JLabel("Title");
      bookTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
      bookTitle.setBounds(21, 39, 46, 14);
      addBookPanel.add(bookTitle);

      JLabel bookAuthor = new JLabel("Author");
      bookAuthor.setFont(new Font("Tahoma", Font.BOLD, 12));
      bookAuthor.setBounds(10, 71, 46, 14);
      addBookPanel.add(bookAuthor);

      bookTitleField = new JTextField();
      bookTitleField.setBounds(81, 36, 86, 20);
      addBookPanel.add(bookTitleField);
      bookTitleField.setColumns(10);

      bookAuthorField = new JTextField();
      bookAuthorField.setColumns(10);
      bookAuthorField.setBounds(81, 70, 86, 20);
      addBookPanel.add(bookAuthorField);

      JLabel bookYear = new JLabel("Year");
      bookYear.setFont(new Font("Tahoma", Font.BOLD, 12));
      bookYear.setBounds(21, 104, 46, 14);
      addBookPanel.add(bookYear);

      bookYearField = new JTextField();
      bookYearField.setColumns(10);
      bookYearField.setBounds(81, 104, 86, 20);
      addBookPanel.add(bookYearField);

      JLabel bookIsbn = new JLabel("ISBN");
      bookIsbn.setFont(new Font("Tahoma", Font.BOLD, 12));
      bookIsbn.setBounds(21, 135, 46, 14);
      addBookPanel.add(bookIsbn);

      bookIsbnField = new JTextField();
      bookIsbnField.setColumns(10);
      bookIsbnField.setBounds(81, 138, 86, 20);
      addBookPanel.add(bookIsbnField);

      JLabel lblBook = new JLabel("BOOK");
      lblBook.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblBook.setBounds(96, 11, 46, 14);
      addBookPanel.add(lblBook);

      addBookPanel.setVisible(false);






      addCdPanel.setBounds(20, 21, 206, 165);
      add.add(addCdPanel);
      addCdPanel.setVisible(true);
      addCdPanel.setLayout(null);

      JLabel cdTitle = new JLabel("Title");
      cdTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
      cdTitle.setBounds(21, 39, 26, 15);
      addCdPanel.add(cdTitle);

      JLabel cdAuthor = new JLabel("Author");
      cdAuthor.setFont(new Font("Tahoma", Font.BOLD, 12));
      cdAuthor.setBounds(10, 71, 44, 15);
      addCdPanel.add(cdAuthor);

      cdTitleField = new JTextField();
      cdTitleField.setBounds(81, 36, 86, 20);
      addCdPanel.add(cdTitleField);
      cdTitleField.setColumns(10);

      cdAuthorField = new JTextField();
      cdAuthorField.setColumns(10);
      cdAuthorField.setBounds(81, 70, 86, 20);
      addCdPanel.add(cdAuthorField);

      JLabel cdYear = new JLabel("Year");
      cdYear.setFont(new Font("Tahoma", Font.BOLD, 12));
      cdYear.setBounds(21, 104, 44, 15);
      addCdPanel.add(cdYear);

      cdYearField = new JTextField();
      cdYearField.setColumns(10);
      cdYearField.setBounds(81, 104, 86, 20);
      addCdPanel.add(cdYearField);

      JLabel lblCd;
      lblCd = new JLabel("CD");
      lblCd.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblCd.setBounds(94, 0, 95, 33);
      addCdPanel.add(lblCd);







      addDvdPanel.setBounds(20, 21, 206, 165);
      add.add(addDvdPanel);
      addDvdPanel.setVisible(true);
      addDvdPanel.setLayout(null);

      JLabel dvdTitle = new JLabel("Title");
      dvdTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
      dvdTitle.setBounds(21, 39, 26, 15);
      addDvdPanel.add(dvdTitle);

      JLabel dvdAuthor = new JLabel("Author");
      dvdAuthor.setFont(new Font("Tahoma", Font.BOLD, 12));
      dvdAuthor.setBounds(10, 71, 44, 15);
      addDvdPanel.add(dvdAuthor);

      dvdTitleField = new JTextField();
      dvdTitleField.setBounds(81, 36, 86, 20);
      addDvdPanel.add(dvdTitleField);
      dvdTitleField.setColumns(10);

      dvdAuthorField = new JTextField();
      dvdAuthorField.setColumns(10);
      dvdAuthorField.setBounds(81, 70, 86, 20);
      addDvdPanel.add(dvdAuthorField);

      JLabel dvdYear = new JLabel("Year");
      dvdYear.setFont(new Font("Tahoma", Font.BOLD, 12));
      dvdYear.setBounds(21, 104, 44, 15);
      addDvdPanel.add(dvdYear);

      dvdYearField = new JTextField();
      dvdYearField.setColumns(10);
      dvdYearField.setBounds(81, 104, 86, 20);
      addDvdPanel.add(dvdYearField);

      JLabel lblDvd = new JLabel("DVD");
      lblDvd.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblDvd.setBounds(94, 0, 95, 33);
      addDvdPanel.add(lblDvd);







      addArticlePanel.setBounds(20, 21, 206, 165);
      add.add(addArticlePanel);
      addArticlePanel.setVisible(true);
      addArticlePanel.setLayout(null);

      JLabel articleTitle = new JLabel("Title");
      articleTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
      articleTitle.setBounds(21, 39, 26, 15);
      addArticlePanel.add(articleTitle);

      JLabel articleAuthor = new JLabel("Author");
      articleAuthor.setFont(new Font("Tahoma", Font.BOLD, 12));
      articleAuthor.setBounds(10, 71, 44, 15);
      addArticlePanel.add(articleAuthor);

      articleTitleField = new JTextField();
      articleTitleField.setBounds(81, 36, 86, 20);
      addArticlePanel.add(articleTitleField);
      articleTitleField.setColumns(10);

      articleAuthorField = new JTextField();
      articleAuthorField.setColumns(10);
      articleAuthorField.setBounds(81, 70, 86, 20);
      addArticlePanel.add(articleAuthorField);

      JLabel lblArticle = new JLabel("ARTICLE");
      lblArticle.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblArticle.setBounds(96, 11, 71, 14);
      addArticlePanel.add(lblArticle);

      JLabel articleMonth = new JLabel("Month");
      articleMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
      articleMonth.setBounds(14, 104, 44, 15);
      addArticlePanel.add(articleMonth);

      articleMonthField = new JTextField();
      articleMonthField.setColumns(10);
      articleMonthField.setBounds(81, 104, 86, 20);
      addArticlePanel.add(articleMonthField);

      articleYearField = new JTextField();
      articleYearField.setBounds(81, 138, 86, 20);
      addArticlePanel.add(articleYearField);
      articleYearField.setColumns(10);

      JLabel articleYear = new JLabel("Year");
      articleYear.setBounds(21, 138, 44, 15);
      addArticlePanel.add(articleYear);
      articleYear.setFont(new Font("Tahoma", Font.BOLD, 12));







      addTypeComboBox = new JComboBox<String>();            //Choose items
      addTypeComboBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            x = addTypeComboBox.getSelectedItem().toString();
            if (x.equals("Book"))
            {
               addBookPanel.setVisible(true);
               addCdPanel.setVisible(false);
               addDvdPanel.setVisible(false);
               addArticlePanel.setVisible(false);
               spinner.setValue(1);
            }

            else if (x.equals("CD"))
            {
               addBookPanel.setVisible(false);
               addCdPanel.setVisible(true);
               addDvdPanel.setVisible(false);
               addArticlePanel.setVisible(false);
               spinner.setValue(1);
            }

            else if (x.equals("DVD"))
            {
               addBookPanel.setVisible(false);
               addCdPanel.setVisible(false);
               addDvdPanel.setVisible(true);
               addArticlePanel.setVisible(false);
               spinner.setValue(1);
            }

            else
            {
               addBookPanel.setVisible(false);
               addCdPanel.setVisible(false);
               addDvdPanel.setVisible(false);
               addArticlePanel.setVisible(true);
               spinner.setValue(1);
            }
         }
      });
      addTypeComboBox.setBounds(333, 58, 86, 20);
      addTypeComboBox.addItem("Book");
      addTypeComboBox.addItem("CD");
      addTypeComboBox.addItem("DVD");
      addTypeComboBox.addItem("Article");
      add.add(addTypeComboBox);

      JLabel lblType = new JLabel("TYPE");
      lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblType.setBounds(277, 61, 46, 14);
      add.add(lblType);

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      add.add(backButton);




      JScrollPane scrollPane = new JScrollPane();
      textArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(textArea); 
      add.add(scrollPane);

      JButton btnAdd_1 = new JButton("ADD");                                  //Add item
      btnAdd_1.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnAdd_1.setBounds(330, 166, 89, 20);
      add.add(btnAdd_1);
      btnAdd_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {



            if (x.equals("Book"))                                                                           //Add BOOK
            {

               if (bookTitleField.getText().equals("") )
               { 
                  try { 
                     textArea.getDocument().insertString(0,"ENTER TITLE OF A BOOK" + "\n",null);
                  } 
                  catch (BadLocationException e1) 
                  {
                     e1.printStackTrace();
                  }

               }

               else {
                  if (bookYearField.getText().equals("") || bookYearField.getText().matches("[a-zA-Z]+")==true) 
                  {
                     bookYearField.setText("0");
                  }

                  database.addAnItem(new Book(bookTitleField.getText(), bookAuthorField.getText(), Integer.parseInt(bookYearField.getText()), bookIsbnField.getText()));

                  for (int i=0; i<copies; i++)
                     database.searchAnItem(bookTitleField.getText()).addTheItemOfTheSameType();

                  try { 

                     if (copies==1)
                     {
                        textArea.getDocument().insertString(0,"1 copy of a book " + bookTitleField.getText() + " written by " + bookAuthorField.getText() + " had been added to your database. " + "\n",null);
                     }

                     else
                     {
                        textArea.getDocument().insertString(0,copies + " copies of a book " + bookTitleField.getText() + " written by " + bookAuthorField.getText() + " had been added to your database. " + "\n",null);
                     }
                  } 
                  catch (BadLocationException e1) 
                  {
                     e1.printStackTrace();
                  }

                  bookTitleField.setText("");
                  bookAuthorField.setText("");
                  bookYearField.setText("");
                  bookIsbnField.setText("");
                  textArea.setCaretPosition(0);

               }
            }





            else if (x.equals("CD"))                                                                        //Add CD
            {
               if (cdTitleField.getText().equals(""))
               { 
                  try { 
                     textArea.getDocument().insertString(0,"ENTER TITLE OF A CD" + "\n",null);
                  } 
                  catch (BadLocationException e1) 
                  {
                     e1.printStackTrace();
                  }
               }

               else {
                  if (cdYearField.getText().equals("") || cdYearField.getText().matches("[a-zA-Z]+")==true) 
                  {
                     cdYearField.setText("0");
                  }
                  database.addAnItem(new CD(cdTitleField.getText(), cdAuthorField.getText(), Integer.parseInt(cdYearField.getText())));

                  for (int i=0; i<copies; i++)
                     database.searchAnItem(cdTitleField.getText()).addTheItemOfTheSameType();

                  try { 
                     if (copies==1)
                     {
                        textArea.getDocument().insertString(0,"1 copy of a CD " + cdTitleField.getText() + " by " + cdAuthorField.getText() + " " +cdYearField.getText()+" " + " had been added to your database." + "\n",null);
                     }

                     else
                     {
                        textArea.getDocument().insertString(0,copies + " copies of a CD " + cdTitleField.getText() + " by " + cdAuthorField.getText() + " " +cdYearField.getText()+" " + " had been added to your database." + "\n",null);
                     }
                  } 
                  catch (BadLocationException e1) 
                  {
                     e1.printStackTrace();
                  }

                  cdTitleField.setText("");
                  cdAuthorField.setText("");
                  cdYearField.setText("");
                  textArea.setCaretPosition(0);
               }
            }








            else if (x.equals("DVD"))                                                                             //Add DVD
            {
               if (dvdTitleField.getText().equals(""))
               { 
                  try { 
                     textArea.getDocument().insertString(0,"ENTER TITLE OF A DVD" + "\n",null);
                  } 
                  catch (BadLocationException e1) 
                  {
                     e1.printStackTrace();
                  }
               }

               else {
                  if (dvdYearField.getText().equals("") || dvdYearField.getText().matches("[a-zA-Z]+")==true) 
                  {
                     dvdYearField.setText("0");
                  }
                  database.addAnItem(new DVD(dvdTitleField.getText(), dvdAuthorField.getText(), Integer.parseInt(dvdYearField.getText())));
                  for (int i=0; i<copies; i++)
                     database.searchAnItem(dvdTitleField.getText()).addTheItemOfTheSameType();


                  try { 
                     if (copies==1)
                     {
                        textArea.getDocument().insertString(0,"1 copy of a DVD " + dvdTitleField.getText() + " by " + dvdAuthorField.getText() + " " +dvdYearField.getText()+" " + " had been added to your database." + "\n",null);
                     }

                     else
                     {
                        textArea.getDocument().insertString(0,copies + " copies of a DVD " + dvdTitleField.getText() + " by " + dvdAuthorField.getText() + " " +dvdYearField.getText()+" " + " had been added to your database." + "\n",null);
                     }
                  } 
                  catch (BadLocationException e1) 
                  {
                     e1.printStackTrace();
                  }


                  dvdTitleField.setText("");
                  dvdAuthorField.setText("");
                  dvdYearField.setText("");
                  textArea.setCaretPosition(0);
               }
            }








            else                                                                                //Add ARTICLE
            {
               if (articleTitleField.getText().equals(""))
               { 
                  try { 
                     textArea.getDocument().insertString(0,"ENTER TITLE OF AN ARTICLE" + "\n",null);
                  } 
                  catch (BadLocationException e1) 
                  {
                     e1.printStackTrace();
                  }
               }

               else {
                  if (articleYearField.getText().equals("") || articleYearField.getText().matches("[a-zA-Z]+")==true) 
                  {
                     articleYearField.setText("0");
                  }
                  database.addAnItem(new Article(articleTitleField.getText(), articleAuthorField.getText(), articleMonthField.getText(), Integer.parseInt(articleYearField.getText())));
                  for (int i=0; i<copies; i++)
                     database.searchAnItem(articleTitleField.getText()).addTheItemOfTheSameType();


                  try { 
                     if (copies==1)
                     {
                        textArea.getDocument().insertString(0,"1 copy of an article " + articleTitleField.getText() + " written by " + articleAuthorField.getText() + " had been added to your database." + "\n",null);
                     }

                     else
                     {
                        textArea.getDocument().insertString(0, copies +" copies of an article " + articleTitleField.getText() + " written by " + articleAuthorField.getText() + " had been added to your database." + "\n",null); 
                     }
                  } 
                  catch (BadLocationException e1) 
                  {
                     e1.printStackTrace();
                  }


                  articleTitleField.setText("");
                  articleAuthorField.setText("");
                  articleMonthField.setText("");
                  articleYearField.setText("");
                  textArea.setCaretPosition(0);
               }
            }

         }
      });

      //Number of copies
      spinner.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            copies = (Integer) spinner.getValue();      
         }
      });
      spinner.setBounds(333, 89, 86, 20);
      add.add(spinner);
      spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

      JLabel lblNumberOfCopies = new JLabel("Copies");
      lblNumberOfCopies.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblNumberOfCopies.setBounds(273, 86, 46, 23);
      add.add(lblNumberOfCopies);
   }

   private void returnPanel()
   {
      //RETURN
      returnPanel.setBounds(0, 0, 485, 372);
      window.add(returnPanel);
      returnPanel.setVisible(false);
      returnPanel.setLayout(null);
      returnPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2));

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      returnPanel.add(backButton);


      JLabel lblReturnAnItem = new JLabel("Return an Item");
      lblReturnAnItem.setHorizontalAlignment(SwingConstants.CENTER);
      lblReturnAnItem.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblReturnAnItem.setBounds(10, 11, 445, 31);
      returnPanel.add(lblReturnAnItem);

      JLabel lblReturnCpr = new JLabel("CPR");
      lblReturnCpr.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblReturnCpr.setBounds(29, 72, 46, 14);
      returnPanel.add(lblReturnCpr);

      JLabel lblReturnTitle = new JLabel("Title");
      lblReturnTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblReturnTitle.setBounds(29, 97, 46, 14);
      returnPanel.add(lblReturnTitle);


      returnCprTextField.setBounds(74, 70, 227, 20);
      returnPanel.add(returnCprTextField);
      returnCprTextField.setColumns(10);


      returnTitleTextField.setColumns(10);
      returnTitleTextField.setBounds(74, 97, 227, 20);
      returnPanel.add(returnTitleTextField);

      JButton btnReturnAnItem = new JButton("Return an Item");
      btnReturnAnItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {


            if (customers.searchByCPR(returnCprTextField.getText()).getName().equals("cx451df5vc"))
            {
               returnTextArea.setText("Customer Not Found.");
               returnTitleTextField.setText("");
               returnCprTextField.setText("");
            }

            else
            {
               customers.searchByCPR(returnCprTextField.getText()).deleteBorrowing(database.searchAnItem(returnTitleTextField.getText()));
               returnTextArea.setText("Customer " + customers.searchByCPR(returnCprTextField.getText()).getName() + " has given back the item " + database.searchAnItem(returnTitleTextField.getText()).getTitle());

               returnTitleTextField.setText("");
               returnCprTextField.setText("");
               returnTextArea.setCaretPosition(0);
            }  

         }
      });
      btnReturnAnItem.setFont(new Font("Tahoma", Font.BOLD, 13));
      btnReturnAnItem.setBounds(311, 70, 144, 48);
      returnPanel.add(btnReturnAnItem);



      JScrollPane scrollPane = new JScrollPane();
      returnTextArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(returnTextArea); 
      returnPanel.add(scrollPane);


   }

   private void registerPanel()
   {

      register.setBounds(0, 0, 485, 372);
      window.add(register);
      register.setLayout(null);
      register.setVisible(false);
      register.setBorder(new LineBorder(Color.DARK_GRAY, 2));

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      register.add(backButton);





      JScrollPane scrollPane = new JScrollPane();
      registerTextArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(registerTextArea); 
      register.add(scrollPane);

      JLabel lblRegistration = new JLabel("Registration");
      lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
      lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 18));
      lblRegistration.setBounds(111, 11, 261, 36);
      register.add(lblRegistration);

      JComboBox<String> customerComboBox = new JComboBox<String>();
      customerComboBox.setBounds(87, 70, 130, 20);
      register.add(customerComboBox);
      customerComboBox.addItem("Student");
      customerComboBox.addItem("Lecturer");
      customerComboBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            customerType = customerComboBox.getSelectedItem().toString();
         }
      });


      JLabel lblType = new JLabel("Type");
      lblType.setHorizontalAlignment(SwingConstants.CENTER);
      lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblType.setBounds(27, 70, 46, 14);
      register.add(lblType);

      JLabel lblRegisterName = new JLabel("Name");
      lblRegisterName.setHorizontalAlignment(SwingConstants.CENTER);
      lblRegisterName.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblRegisterName.setBounds(27, 103, 46, 14);
      register.add(lblRegisterName);

      JLabel lblRegisterCpr = new JLabel("CPR");
      lblRegisterCpr.setHorizontalAlignment(SwingConstants.CENTER);
      lblRegisterCpr.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblRegisterCpr.setBounds(27, 132, 46, 14);
      register.add(lblRegisterCpr);

      nameTextField = new JTextField();
      nameTextField.setBounds(87, 100, 130, 20);
      register.add(nameTextField);
      nameTextField.setColumns(10);

      cprTextField = new JTextField();
      cprTextField.setColumns(10);
      cprTextField.setBounds(87, 130, 130, 20);
      register.add(cprTextField);

      JButton btnRegisterCustomer = new JButton("Register Now");
      btnRegisterCustomer.setBounds(284, 86, 170, 50);
      btnRegisterCustomer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {



            try { 
               if (cprTextField.getText().equals("") && nameTextField.getText().equals(""))
               {
                  registerTextArea.getDocument().insertString(0,"Enter Name and CPR.\n",null);
               }
               else if (nameTextField.getText().equals(""))
               {
                  registerTextArea.getDocument().insertString(0,"Enter Name.\n",null);
               }
               else if (cprTextField.getText().equals(""))
               {
                  registerTextArea.getDocument().insertString(0,"Enter CPR.\n",null);
               }
               else if (cprTextField.getText().equals("") && nameTextField.getText().equals(""))
               {
                  registerTextArea.getDocument().insertString(0,"Enter Name and CPR.\n",null);
               }
               else if (customers.searchByCPR(cprTextField.getText()).getCpr().equals(cprTextField.getText()))
               {
                  registerTextArea.getDocument().insertString(0,"CPR In Use.\n",null);
               }
               else
               {
                  registerTextArea.getDocument().insertString(0, customerType + " " + nameTextField.getText() +  " had been registered" + "\n" + "CPR: " + cprTextField.getText() + " will be be used for all operations in this system." + "\n" + "\n",null);
                  customers.registerCustomer(new Customer(nameTextField.getText(), cprTextField.getText(), customerType));
               }

            } 
            catch (BadLocationException e1) 
            {
               e1.printStackTrace();
            }

            nameTextField.setText(""); 
            cprTextField.setText("");
            registerTextArea.setCaretPosition(0);

         }
      });
      register.add(btnRegisterCustomer);
   }

   private void unregisterPanel()

   {
      //UNREGISTER
      unregister.setBounds(0, 0, 485, 372);
      window.add(unregister);
      unregister.setVisible(false);
      unregister.setLayout(null);
      unregister.setBorder(new LineBorder(Color.DARK_GRAY, 2));



      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      unregister.add(backButton);


      JScrollPane scrollPane = new JScrollPane();
      unregisterTextArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(unregisterTextArea); 
      unregister.add(scrollPane);

      JLabel lblUnregister = new JLabel("Unregister a customer");
      lblUnregister.setHorizontalAlignment(SwingConstants.CENTER);
      lblUnregister.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblUnregister.setBounds(10, 11, 445, 31);
      unregister.add(lblUnregister);

      JLabel lblUnregisterCpr = new JLabel("CPR");
      lblUnregisterCpr.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblUnregisterCpr.setBounds(29, 85, 46, 14);
      unregister.add(lblUnregisterCpr);


      unregisterCprTextField.setBounds(74, 83, 227, 20);
      unregister.add(unregisterCprTextField);
      unregisterCprTextField.setColumns(10);



      JButton btnUnregisterLend = new JButton("Unregister");
      btnUnregisterLend.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (customers.searchByCPR(unregisterCprTextField.getText()).getName().equals("cx451df5vc"))
            {
               unregisterTextArea.setText("Customer Not Found.");
            }
            else
            {
               temporaryCustomer = customers.searchByCPR(unregisterCprTextField.getText());
               customers.unregisterCustomer(temporaryCustomer);
               unregisterTextArea.setText(temporaryCustomer.getName() + " was deleted.");
               unregisterTextArea.setCaretPosition(0);
            }
         }
      });
      btnUnregisterLend.setFont(new Font("Tahoma", Font.BOLD, 13));
      btnUnregisterLend.setBounds(311, 70, 144, 48);
      unregister.add(btnUnregisterLend);





   }

   private void makeReservationPanel()
   {

      makeReservationPanel.setBounds(0, 0, 485, 372);
      window.add(makeReservationPanel);
      makeReservationPanel.setVisible(false);
      makeReservationPanel.setLayout(null);
      makeReservationPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2));

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      makeReservationPanel.add(backButton);


      JLabel lblMakeAReservation = new JLabel("Make a Reservation");
      lblMakeAReservation.setHorizontalAlignment(SwingConstants.CENTER);
      lblMakeAReservation.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblMakeAReservation.setBounds(10, 11, 445, 31);
      makeReservationPanel.add(lblMakeAReservation);

      JLabel lblReservationCpr = new JLabel("CPR");
      lblReservationCpr.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblReservationCpr.setBounds(29, 72, 46, 14);
      makeReservationPanel.add(lblReservationCpr);

      JLabel lblReservationTitle = new JLabel("Title");
      lblReservationTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblReservationTitle.setBounds(29, 97, 46, 14);
      makeReservationPanel.add(lblReservationTitle);


      reservationCprTextField.setBounds(74, 70, 227, 20);
      makeReservationPanel.add(reservationCprTextField);
      reservationCprTextField.setColumns(10);


      reservationTitleTextField.setColumns(10);
      reservationTitleTextField.setBounds(74, 97, 227, 20);
      makeReservationPanel.add(reservationTitleTextField);

      JButton btnMakeReservation = new JButton("Reserve");
      btnMakeReservation.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (customers.searchByCPR(reservationCprTextField.getText()).getName().equals("cx451df5vc"))
            {
               reservationTextArea.setText("Customer Not Found."); 
               reservationCprTextField.setText("");

            }

            else if (database.searchAnItem(reservationTitleTextField.getText()).getTitle().equals("no match found"))
            {
               reservationTextArea.setText("Title Not Found."); 

               reservationTitleTextField.setText(""); 
            }

            else{
               customers.searchByCPR(reservationCprTextField.getText()).newReservation("", database.searchAnItem(reservationTitleTextField.getText()));
               reservationTextArea.setText(customers.searchByCPR(reservationCprTextField.getText()).getName() + " has reserved " + reservationTitleTextField.getText());
               reservationCprTextField.setText("");
               reservationTitleTextField.setText("");
               reservationTextArea.setCaretPosition(0);
            }


         }
      });
      btnMakeReservation.setFont(new Font("Tahoma", Font.BOLD, 13));
      btnMakeReservation.setBounds(311, 70, 144, 48);
      makeReservationPanel.add(btnMakeReservation);


      JScrollPane scrollPane = new JScrollPane();
      reservationTextArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(reservationTextArea); 
      makeReservationPanel.add(scrollPane);


   }

   private void showReservationsPanel()
   {
      showReservationsPanel.setBounds(0, 0, 485, 372);
      window.add(showReservationsPanel);
      showReservationsPanel.setVisible(false);
      showReservationsPanel.setLayout(null);
      showReservationsPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2));

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      showReservationsPanel.add(backButton);


      JLabel lblShowReservations = new JLabel("Show Reservations");
      lblShowReservations.setHorizontalAlignment(SwingConstants.CENTER);
      lblShowReservations.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblShowReservations.setBounds(10, 11, 445, 31);
      showReservationsPanel.add(lblShowReservations);

      JLabel lblShowReservationCpr = new JLabel("CPR");
      lblShowReservationCpr.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblShowReservationCpr.setBounds(29, 87, 46, 14);
      showReservationsPanel.add(lblShowReservationCpr);



      reservationShowCprTextField.setBounds(74, 85, 227, 20);
      showReservationsPanel.add(reservationShowCprTextField);
      reservationShowCprTextField.setColumns(10);




      JButton btnShowReservations = new JButton("Show");
      btnShowReservations.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (customers.searchByCPR(reservationShowCprTextField.getText()).getName().equals("cx451df5vc"))
            {
               reservationShowTextArea.setText("Customer Not Found."); 
               reservationShowCprTextField.setText("");
            }

            else{

               if (customers.searchByCPR(reservationShowCprTextField.getText()).getNumberOfReservations()==0)
               {

                  reservationShowTextArea.setText("No reservations.");
               }
               else
               {
                  reservationShowTextArea.setText("Reservations by " + customers.searchByCPR(reservationShowCprTextField.getText()).getName() + " : \n" + (customers.searchByCPR(reservationShowCprTextField.getText())).reservationsToString());
                  reservationShowCprTextField.setText("");
                  reservationShowTextArea.setCaretPosition(0);
               }


            }
         }
      });
      btnShowReservations.setFont(new Font("Tahoma", Font.BOLD, 13));
      btnShowReservations.setBounds(311, 70, 144, 48);
      showReservationsPanel.add(btnShowReservations);


      JScrollPane scrollPane = new JScrollPane();
      reservationShowTextArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(reservationShowTextArea); 
      showReservationsPanel.add(scrollPane);
   }

   private void deleteReservationPanel()
   {
      deleteReservationPanel.setBounds(0, 0, 485, 372);
      window.add(deleteReservationPanel);
      deleteReservationPanel.setVisible(false);
      deleteReservationPanel.setLayout(null);
      deleteReservationPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2));

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      deleteReservationPanel.add(backButton);


      JLabel lblDeleteReservations = new JLabel("Delete a Reservation");
      lblDeleteReservations.setHorizontalAlignment(SwingConstants.CENTER);
      lblDeleteReservations.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblDeleteReservations.setBounds(10, 11, 445, 31);
      deleteReservationPanel.add(lblDeleteReservations);

      JLabel lblDeleteReservationCpr = new JLabel("CPR");
      lblDeleteReservationCpr.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblDeleteReservationCpr.setBounds(29, 72, 46, 14);
      deleteReservationPanel.add(lblDeleteReservationCpr);

      JLabel lblReservationTitle = new JLabel("Title");
      lblReservationTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblReservationTitle.setBounds(29, 97, 46, 14);
      deleteReservationPanel.add(lblReservationTitle);


      reservationDeleteCprTextField.setBounds(74, 70, 227, 20);
      reservationDeleteCprTextField.setColumns(10);
      deleteReservationPanel.add(reservationDeleteCprTextField);


      reservationDeleteTitleTextField.setColumns(10);
      reservationDeleteTitleTextField.setBounds(74, 97, 227, 20);
      deleteReservationPanel.add(reservationDeleteTitleTextField);

      JButton btnDeleteReservation = new JButton("Delete");
      btnDeleteReservation.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (customers.searchByCPR(reservationDeleteCprTextField.getText()).getName().equals("cx451df5vc"))
            {
               reservationDeleteTextArea.setText("Customer Not Found.");
               returnTitleTextField.setText("");
               returnCprTextField.setText("");
            }

            else
            {
               customers.searchByCPR(reservationDeleteCprTextField.getText()).deleteReservation(database.searchAnItem(reservationDeleteTitleTextField.getText()));
               reservationDeleteTextArea.setText("Reservation by " + customers.searchByCPR(reservationDeleteCprTextField.getText()).getName()
                     + " of the item " + database.searchAnItem(reservationDeleteTitleTextField.getText()).getTitle() + " has been canceled.");

               returnTitleTextField.setText("");
               returnCprTextField.setText("");
               reservationDeleteTextArea.setCaretPosition(0);
            }     
         }
      });
      btnDeleteReservation.setFont(new Font("Tahoma", Font.BOLD, 13));
      btnDeleteReservation.setBounds(311, 70, 144, 48);
      deleteReservationPanel.add(btnDeleteReservation);


      JScrollPane scrollPane = new JScrollPane();
      reservationDeleteTextArea.setEditable(false);
      scrollPane.setBounds(10, 210, 465, 151);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(reservationDeleteTextArea); 
      deleteReservationPanel.add(scrollPane);
   }

   private void notificationsPanel()
   {
      notificationsPanel.setBounds(0, 0, 485, 372);
      notificationsPanel.setVisible(false);
      notificationsPanel.setLayout(null);
      notificationsPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2));
      window.add(notificationsPanel);

      JButton backButton = new JButton("");
      backButton.setIcon(backimg);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showIntro();
         }
      });
      backButton.setBounds(435, 11, 40, 40);
      notificationsPanel.add(backButton);


      notificationsTextArea.setLineWrap(true);
      notificationsTextArea.setEditable(false);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 68, 465, 293);
      scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      scrollPane.setViewportView(notificationsTextArea); 
      notificationsPanel.add(scrollPane);


      JLabel lblNotifications = new JLabel("NOTIFICATIONS");
      lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
      lblNotifications.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblNotifications.setBounds(10, 11, 465, 48);
      notificationsPanel.add(lblNotifications);   

   }


   private void showIntro()
   {
      intro.setVisible(true);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);
   }

   private void showLend()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(true);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);

      lendTextArea.setText("");
      lendCprTextField.setText("");
      lendTitleTextField.setText("");
   }

   private void showSearch()
   {
      intro.setVisible(false);
      search.setVisible(true); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);

      searchOptions.setVisible(true);
      itemFound.setVisible(false);
      customerFound.setVisible(false);
      searchItem.setVisible(true);
      searchCustomer.setVisible(false);

      tglItem.setSelected(true);
      tglCustomer.setSelected(false);

      searchTextArea.setText("");
      textFieldCustomer.setText("");
      textFieldItem.setText("");

   }

   private void showAdd()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(true); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);

      spinner.setValue(1);
      bookTitleField.setText("");
      bookIsbnField.setText("");
      bookAuthorField.setText("");
      bookYearField.setText("");

      cdAuthorField.setText("");
      cdTitleField.setText("");
      cdYearField.setText("");

      dvdAuthorField.setText("");
      dvdTitleField.setText("");
      dvdYearField.setText("");

      articleAuthorField.setText("");
      articleMonthField.setText("");
      articleTitleField.setText("");
      articleYearField.setText("");

      addTypeComboBox.setSelectedIndex(0);
   }

   private void showRegister()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(true);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);

      customerType = "Student";
      nameTextField.setText("");
      cprTextField.setText("");
      registerTextArea.setText("");
   }

   private void showUnregister()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(true);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);

      unregisterTextArea.setText("");
      unregisterCprTextField.setText("");
   }

   private void showReturn()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(true);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);

      returnTitleTextField.setText("");
      returnCprTextField.setText("");
      returnTextArea.setText("");
   }

   private void showShowReservations()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(true);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);

      reservationShowCprTextField.setText("");
      reservationShowTextArea.setText("");
   }

   private void showMakeReservation()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(true);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(false);

      reservationCprTextField.setText("");
      reservationTitleTextField.setText("");
      reservationTextArea.setText("");
   }

   private void showDeleteReservation()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(true);
      notificationsPanel.setVisible(false);

      reservationDeleteCprTextField.setText("");
      reservationDeleteTitleTextField.setText("");
      reservationDeleteTextArea.setText("");
   }

   private void showNotifications()
   {
      intro.setVisible(false);
      search.setVisible(false); 
      add.setVisible(false); 
      lend.setVisible(false);
      returnPanel.setVisible(false);
      register.setVisible(false);
      unregister.setVisible(false);
      makeReservationPanel.setVisible(false);
      showReservationsPanel.setVisible(false);
      deleteReservationPanel.setVisible(false);
      notificationsPanel.setVisible(true);



      notificationsTextArea.setText("");



      ArrayList<String> dates = new ArrayList<String>();

      for (int i = 0; i<customers.returnDate().size(); i++)
      {
         for (int a = 0; a<customers.returnDate().get(i).size(); a++)
         {
            dates.add(customers.returnDate().get(i).get(a).toString());
         }
      }


      Collections.sort(dates, new Comparator<String>() {
         DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
         @Override
         public int compare(String o1, String o2) {
            try {
               return f.parse(o1).compareTo(f.parse(o2));
            } catch (ParseException e) {
               throw new IllegalArgumentException(e);
            }
         }
      });


      for (int i = 0; i<dates.size(); i++)
      {
         notificationsTextArea.append(dates.get(i) + "\n");
      }

      notificationsTextArea.setCaretPosition(0);  

   }



   private void itemFound()
   {
      searchOptions.setVisible(false);
      itemFound.setVisible(true);
      customerFound.setVisible(false);
      searchItem.setVisible(false);
      searchCustomer.setVisible(false);

      textFieldItem.setText("");
   }

   private void customerFound()
   {
      searchOptions.setVisible(false);
      itemFound.setVisible(false);
      customerFound.setVisible(true);
      searchItem.setVisible(false);
      searchCustomer.setVisible(false);

      textFieldCustomer.setText("");
   }
}



