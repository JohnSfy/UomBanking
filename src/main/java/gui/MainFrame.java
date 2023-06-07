package gui;

import model.*;
import org.example.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.management.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.stream.Collectors;



public class MainFrame extends JFrame {
    private JLabel header;
    private JPanel expensesPanel;
    private JPanel balancePanel;
    private JPanel spendCategoriesPanel;
    static JPanel cardPanel;
    private JButton transactionHistoryButton;
    private JButton loanButton;
    private JButton createCardButton;

    protected static Account account;
    private JPanel loanPanel;

    public static JFrame mainFrame;

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public MainFrame(Account account){
        mainFrame = new Template();

//      Initializing components
        header = Utils.setHeader("Welcome back " + ClientDB.fetchClient(account.getClient()).getFirstName());
        expensesPanel = new ExpensesPanel(account);
        balancePanel = new BalancePanel(account);
        spendCategoriesPanel = new SpendCategoriesPanel(account);
        cardPanel = new CardPanel(account);
        transactionHistoryButton = new JButton("Transaction History");
        loanButton = new JButton("Apply for a loan");
        loanButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createCardButton = new JButton("Create your new card today!");
        createCardButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        loanPanel = new LoanPanel(account);

        cardPanel.setBackground(Color.WHITE);
//      Setting up header
        header.setBounds(380, 50, 1000, 100);

//      Setting up loanButton
        loanButton.setBounds(850, 670, 200, 50);
        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LoanFrame(account);
            }
        });

//      Setting up createCard button
        createCardButton.setBounds(370, 710, 250, 30);



<<<<<<< HEAD


      //  if(CardDB.fetchCard(account.getID()) == null){
              createCardButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                      new CreateNewCardFrame(account);

                  }
              });
     //   }



=======
//      Creating account
       // account = new Account(0, "", "", client.getUsername(),"");
>>>>>>> b79d258fd1c8584e0314a70c07decbf6e687fb17

//      Setting up newTransactionButton

//      Adding components to the frame
        mainFrame.add(header);
        mainFrame.add(expensesPanel);
        mainFrame.add(balancePanel);
        mainFrame.add(spendCategoriesPanel);
        mainFrame.add(loanButton);
        mainFrame.add(cardPanel);
<<<<<<< HEAD
        mainFrame.add(createCardButton);
=======
        mainFrame.add(loanPanel);
>>>>>>> b79d258fd1c8584e0314a70c07decbf6e687fb17



     //Basic settings
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public MainFrame() {

    }
}

class CardPanel extends JPanel {
    private JLabel cardLabel;
    private JLabel cardL,cardL1;
    private JButton cardButton;


    public CardPanel(Account account) {
        cardLabel = new JLabel("Card  details");
        cardL = new JLabel("______________________________________________");
        cardL1 = new JLabel("_____________________________________________");
        cardButton = new JButton("See your card");
        cardButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cardButton.setBounds(500, 500, 150, 20);

//      Setting up cardLabel
        cardLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));

//      Setting up cardIcon
        cardL.setFont(new Font("Tahoma", Font.BOLD, 20));
        cardL1.setForeground(Color.white);
        cardL1.setFont(new Font("Tahoma", Font.PLAIN, 20));

//      Basic Settings
        setBounds(320, 480, 350, 200);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


<<<<<<< HEAD
        if (CardDB.fetchCard(account.getID()) != null) {
=======
        add(cardIcon);

//        JLabel carNumber = new JLabel(String.valueOf(CardDB.fetchCard(account.getID()).getCardNumber()));

//        String color = CardDB.fetchCard(account.getID()).getColor();
//        Color c = Color.decode(color);
//        this.setBackground(c);
>>>>>>> b79d258fd1c8584e0314a70c07decbf6e687fb17


            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {


                    String type = CardDB.fetchCard(account.getID()).getType();
                    long cardNum = CardDB.fetchCard(account.getID()).getCardNumber();
                    String cardExp = CardDB.fetchCard(account.getID()).getExpirationDate();
                    String cardName = CardDB.fetchCard(account.getID()).getCardName();
                    int cardCvv = CardDB.fetchCard(account.getID()).getCvv();

                    //Initialize color
                    String color = CardDB.fetchCard(account.getID()).getColor();
                    color = color.replace("java.awt.color[","").replace("]","");
                    String[] rgbValues = color.split(",");

                    int red = Integer.parseInt(rgbValues[0].split("=")[1]);
                    int green = Integer.parseInt(rgbValues[1].split("=")[1]);
                    int blue = Integer.parseInt(rgbValues[2].split("=")[1]);

                    Color finalColor = new Color(red,green,blue);



                    new PreviewCardFrame(account, type, cardNum, cardExp, cardName, cardCvv, finalColor);
                    MainFrame.mainFrame.dispose();


                }
            });
        }
        else {
            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    JOptionPane.showMessageDialog(cardButton, "You don't have a card!",
                            "Card Warning", JOptionPane.WARNING_MESSAGE);
                }
            });
       }

        add(cardLabel);
        add(cardL);
        add(cardL1);
        add(cardButton);
    }

        private Color decodeColor(String colorString) {
            // Use the Color class to decode the named color string
            try {
                // Use reflection to get the named color field from the Color class
                java.lang.reflect.Field field = Color.class.getField(colorString);
                return (Color) field.get(null);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the case where the named color is not found
                return Color.BLACK;
            }
        }
//        layoutComponents();
    }

//    public LayoutManager layoutComponents() {
//        setLayout(new GridBagLayout());
//
//        GridBagConstraints gc = new GridBagConstraints();
//
//        gc.gridy = 0;
//        gc.gridx = 0;
//        add(cardIcon, gc);
//
//        return null;
//    }


class SpendCategoriesPanel extends JPanel {
    private JButton spendCategoriesButton;
    private ChartPanel chartPanel;


    public SpendCategoriesPanel(Account account) {
        spendCategoriesButton = new JButton("Spend Categories");
        chartPanel = new ChartPanel(null);

//      Basic settings
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBounds(700, 200, 470, 400);

//      Fetching payments
        ArrayList<Transactions> payments = SpendCategoriesDB.fetchAllPayments(account);

//      Setting up the Chart
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Transactions payment: payments) {
            dataset.setValue(payment.getDescription(), payment.getAmount());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Spend Categories", // chart title
                dataset, // data
                true, // include legend
                true, // tooltips
                false // urls
        );
//      Setting the categories
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Category 1", Color.BLUE);
        plot.setSectionPaint("Category 2", Color.GREEN);
        plot.setSectionPaint("Category 3", Color.ORANGE);

//      Adding the chartPanel to the panel
        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        chartPanel.setPreferredSize(new Dimension(400, 300));

//      Setting up spendCategoriesButton
        spendCategoriesButton = new JButton("Spend categories");
        spendCategoriesButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        spendCategoriesButton.setPreferredSize(new Dimension(180, 50));

        spendCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getMainFrame().dispose();
                new SpendCategoriesFrame(account, payments);
            }
        });

        layoutComponents();

    }

    public LayoutManager layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        gc.gridx = 0;
        add(chartPanel, gc);

        gc.gridy++;
        gc.insets = new Insets(20, 0, 0, 0);
        add(spendCategoriesButton, gc);


        return null;
    }
}

class BalancePanel extends JPanel{
    private JLabel header;
    private JLabel balance;
    public static JButton newTransactionButton;

    public static JButton getNewTransactionButton() {
        return newTransactionButton;
    }

    public BalancePanel(Account account) {
//      Initializing components
        header = Utils.setHeader("Your account balance");

        balance = new JLabel(String.valueOf(account.getBalance())+"€");
        newTransactionButton = new JButton("New Transaction");
        newTransactionButton.setFont(new Font("Tahoma", Font.PLAIN, 13));

//      Setting up newTransactionButton
        newTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getMainFrame().dispose();
                new TransactionTypeFrame(account);
            }
        });

//      Setting up header
        header.setFont(new Font("Tahoma", Font.PLAIN, 23));

//      Setting up balance
        balance.setFont(new Font("Tahoma", Font.PLAIN, 55));

//      Basic settings
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBounds(350, 200, 300, 200);

        layoutComponents();
    }

    public LayoutManager layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(0, 0, 30, 0);
        add(header, gc);

        gc.gridy++;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0, 0, 0, 0);
        add(balance, gc);

        gc.gridy++;
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.insets = new Insets(30, 0, 0, 0);
        add(newTransactionButton, gc);

        return null;
    }
}

class ExpensesPanel extends JPanel {
    private JLabel header;
    private JScrollPane scrollPane;
    private JList expenseList;
    private DefaultListModel listModel;
    private JButton transactionHistoryButton;
    public ExpensesPanel(Account account) {

//      Initializing components
        header = Utils.setHeader("Your expenses");
        transactionHistoryButton = new JButton();

//      Setting up header
        header.setFont(new Font("Tahoma", Font.PLAIN, 18));

//      Setting up scrollPane
        expenseList = new JList();
        listModel = new DefaultListModel();
        expenseList.setModel(listModel);
        expenseList.setFont(new Font("Tahoma", Font.PLAIN, 10));
        scrollPane = new JScrollPane(expenseList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setMaximumSize(new Dimension(150, 200));

//      Retrieving all transactions from the database
        ArrayList<Transactions> transactions = new ArrayList<>();
        transactions = ExpensesDB.fetchAllTransactions(account);

        if (transactions != null) {
            for (Transactions transaction : transactions) {
                if(transaction.getClass().getName().equals("model.Deposit")){
                    listModel.addElement("• Deposit, "+transaction.getAmount() +"€");
                }
                else if(transaction.getClass().getName().equals("model.Withdraw")){
                    listModel.addElement("• Withdraw, " + transaction.getAmount() +"€");
                }
                else if(transaction.getClass().getName().equals("model.Transfer")){
                    listModel.addElement("• " + transaction.getDescription() + transaction.getAmount() +"€");
                }
                else if(transaction.getClass().getName().equals("model.Payment")){
                    listModel.addElement("• " + transaction.getDescription() + ", " + transaction.getAmount() +"€");
                }
            }
        }

//      Setting up transaction history button
        transactionHistoryButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        transactionHistoryButton.setPreferredSize(new Dimension(180, 50));
        transactionHistoryButton.setText("<html><center>"+"Check your transaction"+"<br>"+"history"+"</center></html>");
        ArrayList<Transactions> finalTransactions = transactions;
        transactionHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getMainFrame().dispose();
                new TransactionHistoryFrame(account, finalTransactions);
            }
        });

//      Basic settings
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBounds(20, 160, 280, 300);

        layoutComponents();
    }

    public LayoutManager layoutComponents() {
        setLayout(new FlowLayout(1, 10, 28));

        add(header);
        add(scrollPane);
        add(transactionHistoryButton);

        return null;
    }
}

<<<<<<< HEAD














=======
class LoanPanel extends JPanel {

    private JLabel header;
    private JScrollPane scrollPane;
    private JList loanList;
    private DefaultListModel listModel;
    private JButton payLoanButton;
    public LoanPanel(Account account) {

//      Initializing components
        header = Utils.setHeader("Your Loans");
        payLoanButton = new JButton();

        ArrayList<Loan> loans = new ArrayList<>();
        loans = LoanDB.fetchAllLoans(account);

//      Setting up header
        header.setFont(new Font("Tahoma", Font.PLAIN, 18));

//      Setting up scrollPane
        loanList = new JList();
        listModel = new DefaultListModel();
        loanList.setModel(listModel);
        loanList.setFont(new Font("Tahoma", Font.PLAIN, 10));
        scrollPane = new JScrollPane(loanList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setPreferredSize(new Dimension(150, 100));

        if(loans != null){
            for(Loan loan : loans){
                listModel.addElement("• " + loan.getDescription() + ", " + loan.getLoanAmount() + "€");
            }
        }

//      Setting up transaction history button
        payLoanButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        payLoanButton.setPreferredSize(new Dimension(180, 50));
        payLoanButton.setText("<html><center>"+"Select your loan"+"<br>"+"and pay it"+"</center></html>");

        ArrayList<Loan> finalLoans = loans;
        payLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = loanList.getSelectedIndex();
                if (index >=  0) {
                    Loan loanSelected = finalLoans.get(index);
                    MainFrame.getMainFrame().dispose();
                    new PayLoanFrame(account, loanSelected);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select a loan to pay");
                }

            }
        });

//      Basic settings
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBounds(40, 470, 200, 250);

        layoutComponents();
    }

    public LayoutManager layoutComponents() {
        setLayout(new FlowLayout(1, 8, 25));

        add(header);
        add(scrollPane);
        add(payLoanButton);

        return null;
    }
}
>>>>>>> b79d258fd1c8584e0314a70c07decbf6e687fb17
