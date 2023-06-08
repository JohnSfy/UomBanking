package gui;

import model.Account;
import org.example.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TemplateMainFrame extends JFrame {
    //  Properties
    private JLabel logo;
    private JLabel name;
    private JLabel accountIcon;
    private JLabel clientName;
    private JComboBox<JButton> dropdown;
    private SettingsPanel settingsPanel;
    public TemplateMainFrame(Account account) {
//      Setting layout manager to null for absolute positioning
        setLayout(null);

//      Initializing components
        logo = new JLabel("Logo");
        name = new JLabel("UOMSystemX");
        accountIcon = new JLabel("Account");
        clientName = new JLabel();

        settingsPanel = new SettingsPanel(this, account);
        settingsPanel.setVisible(false);

//      Setting up JLabel logo
        logo.setBounds(0, 0, 150, 30);
        logo.setSize(100, 100);

        logo.setIcon(Utils.setLabelIcon("src/main/java/images/UOM_LOGO_3.png", logo));

//      Setting up JLabel accountIcon
        accountIcon.setBounds(1130, 10, 150, 30);
        accountIcon.setSize(50, 50);

        accountIcon.setIcon(Utils.setLabelIcon("src/main/java/images/UserIcon.png", accountIcon));

//      Setting up JLabel name
        name.setBounds(10, 730, 250, 30);
        name.setFont(new Font("Courier", Font.PLAIN, 25));

//      Setting up JLabel clientName
        clientName.setBounds(1115, 65, 150, 30);
        clientName.setFont(new Font("Courier", Font.PLAIN, 15));

//      Setting up Log out
        accountIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                System.out.println("Label is pressed");
                settingsPanel.setBounds(1000, 65, 170, 70);
                settingsPanel.setVisible(!settingsPanel.isVisible());
            }
        });

//      Adding components to the frame
        add(logo);
        add(name);
        add(accountIcon);
        add(clientName);
        add(settingsPanel);

//      Basic setup for the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setMaximumSize(new Dimension(1200, 800));
        setSize(1200, 800);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        validate(); //validates the images
    }
}

class SettingsPanel extends JPanel{

    private JButton logOut;
    private JButton deleteAccount;

    public SettingsPanel(JFrame parent, Account account) {

        logOut = new JButton("Log out");
        logOut.setPreferredSize(new Dimension(50, 30));
        deleteAccount = new JButton("Delete Account");
        deleteAccount.setPreferredSize(new Dimension(50, 30));

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.dispose();
                new WelcomePage();
            }
        });

        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CardDB.fetchCard(account.getID()) != null){
                    System.out.println("Card exists");
                    CardDB.deleteCard(account.getID());
                } else {
                    System.out.println("Card does not exist");
                }

                if (LoanDB.fetchAllLoans(account) != null){
                    LoanDB.deleteLoan(account);
                    System.out.println("Loan exists");
                } else {
                    System.out.println("Loan does not exist");
                }

                if (ExpensesDB.fetchAllTransactions(account) != null){
                    TransactionsDB.deleteTransactions(account);
                    System.out.println("Transaction exists");
                } else {
                    System.out.println("Transaction does not exist");
                }

                ClientDB.deleteClient(account.getClient());
                AccountDB.deleteAccount(account);
                parent.dispose();
                new WelcomePage();
            }
        });

        add(logOut);
        add(deleteAccount);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
    }
}
