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
        createCardButton = new JButton("Create your new card today!");

//      Setting up header
        header.setBounds(250, 50, 1000, 100);

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






        createCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateNewCardFrame(account);
                mainFrame.dispose();
            }
        });



//      Creating account
       // account = new Account(0, "", "", client.getUsername(),"");

//      Setting up newTransactionButton

//      Adding components to the frame
        mainFrame.add(header);
        mainFrame.add(expensesPanel);
        mainFrame.add(balancePanel);
        mainFrame.add(spendCategoriesPanel);
        mainFrame.add(loanButton);
        mainFrame.add(cardPanel);


        // if user has already create the card
//        if(CardDB.fetchCard(account.getID()) == null){
            mainFrame.add(createCardButton);
//
//        }
      //  else {
            Card card = CardDB.fetchCard(account.getID());

         //   }

//      Basic settings
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public MainFrame() {

    }
}

class CardPanel extends JPanel {
    private JLabel cardLabel;
    private JLabel cardIcon;
    private StringBuilder cardNumberBuilder;
    private Long carNumber;
    private JLabel cardNum;
    private JLabel nameInTheCard;
    public CardPanel(Account account) {
        cardLabel = new JLabel("Cards");
        cardIcon = new JLabel();

//      Setting up cardLabel
        cardLabel.setFont(new Font("Courier", Font.BOLD, 20));

//      Setting up cardIcon
        cardIcon.setSize(50, 50);
        cardIcon.setIcon(Utils.setLabelIcon("src/main/resources/images/card.png", cardIcon));

//      Basic Settings
        setBounds(320, 480, 350, 200);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        add(cardIcon);

//        JLabel carNumber = new JLabel(String.valueOf(CardDB.fetchCard(account.getID()).getCardNumber()));




//        carNumber = CardDB.fetchCard(account.getID()).getCardNumber();
//
//        cardNumberBuilder = new StringBuilder(String.valueOf(carNumber));
//        for (int i = 4; i < cardNumberBuilder.length(); i += 6) {
//            cardNumberBuilder.insert(i, "  ");
//        }
//
//        cardNum = new JLabel(String.valueOf(carNumber));
//        cardNum.setBounds(350,600,200,100);
//        add(cardNum);
//
//        nameInTheCard = new JLabel(String.valueOf(CardDB.fetchCard(account.getID()).getCardName()));
//        nameInTheCard.setBounds(350,500,200,100);
//        add(nameInTheCard);


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
}

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
        spendCategoriesButton.setFont(new Font("Courier", Font.PLAIN, 12));
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

//      Setting up newTransactionButton
        newTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getMainFrame().dispose();
                new TransactionTypeFrame(account);
            }
        });

//      Setting up header
        header.setFont(new Font("Courier", Font.PLAIN, 23));

//      Setting up balance
        balance.setFont(new Font("Courier", Font.PLAIN, 55));

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
        header.setFont(new Font("Courier", Font.PLAIN, 18));

//      Setting up scrollPane
        expenseList = new JList();
        listModel = new DefaultListModel();
        expenseList.setModel(listModel);
        scrollPane = new JScrollPane(expenseList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
        transactionHistoryButton.setFont(new Font("Courier", Font.PLAIN, 12));
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
        setBounds(50, 200, 190, 300);

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
