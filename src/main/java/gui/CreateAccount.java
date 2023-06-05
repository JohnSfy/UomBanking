package gui;

import model.Account;
import model.Client;
import org.example.AccountDB;
import org.example.ClientDB;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CreateAccount extends JFrame {
    public JFrame createAccount;
    private JLabel header;
    private JLabel subHeader;
    private CreateAccountPanel infoPanel;
    private JButton createAccountButton;
    private JLabel loginLabel;
    private JButton loginButton;

    public Client client;

    private Account account;

    public CreateAccount() {
//      Setting up the template
        createAccount = new TemplateNotLoggedIn();

//      Initializing components
        header = Utils.setHeader("Create an UoMBanking account");
        subHeader = new JLabel("Start now with our free services");
        createAccountButton = new JButton("Create your account");
        loginLabel = new JLabel("Already have an account?");
        loginButton = new JButton("Log in");

//      Setting up the header
        header.setBounds(250, 50, 1000, 100);

//      Setting up the subHeader
        subHeader.setBounds(450, 100, 1000, 100);
        subHeader.setFont(new Font("Courier", Font.PLAIN, 20));

//      Setting up the panel
        infoPanel = new CreateAccountPanel();

//      Setting up the create your account button
        createAccountButton.setBounds(450, 700, 200, 50);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//              Checking data validity
                String phoneNumber = infoPanel.getPhoneNumber().getText();
                String email = infoPanel.getEmail().getText();
                String password = infoPanel.getPasswordField().getText();
                String confirmPassword = infoPanel.getPasswordField2().getText();
                JComboBox costPerTransaction = infoPanel.getCostPerTransaction();


                if (checkData(email, phoneNumber, password, confirmPassword)) {

//                  Creating the client and the client's account
                    client = new Client(infoPanel.getFirstName().getText(), infoPanel.getLastName().getText(), infoPanel.getPhoneNumber().getText(),
                            infoPanel.getEmail().getText(), infoPanel.getUsername().getText(), infoPanel.getPasswordField().getText());

//                  Checking if client already exists
                    if (!ClientDB.findClient(client)) {
                        ClientDB.saveClient(client);
                        account = new Account(0, "", "", infoPanel.getUsername().getText(), (String) infoPanel.getCostPerTransaction().getSelectedItem());
                        AccountDB.saveAccount(account);
                        createAccount.dispose();
                        new MainFrame(account);
                    } else {
                        JOptionPane.showMessageDialog(null, "There is already an account with this email or username.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

//      Setting up the login label
        loginLabel.setBounds(920, 700, 200, 50);
        loginLabel.setFont(new Font("Courier", Font.PLAIN, 12));

//      Setting up login button
        loginButton.setBounds(1090, 700, 100, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//              Checking data
                createAccount.dispose();
                new WelcomePage();
            }
        });

//      Adding components to the frame
        createAccount.add(header);
        createAccount.add(subHeader);
        createAccount.add(createAccountButton);
        createAccount.add(loginLabel);
        createAccount.add(loginButton);
        createAccount.add(infoPanel);
        createAccount.add(loginLabel);
        createAccount.add(loginButton);

//      Basic settings
        createAccount.setLayout(null);
        createAccount.setVisible(true);
        createAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean checkPhoneNumber(String number) {
        char[] array = number.toCharArray();
        boolean flag = true;
        if (array.length == 10) {
            for (char c : array) {
                if (!Character.isDigit(c)) {
                    System.out.println(!Character.isDigit(c));
                    flag = false;
                }
                break;
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public boolean checkEmail(String email) {
        char[] array = email.toCharArray();
        boolean flag = false;
        int i = 0;
        while (!flag && i < array.length) {
            if (array[i] == '@') {
                flag = true;
            }
            i++;
        }
        return flag;
    }

    public boolean checkPasswordConfirmation(String text1, String text2) {
        char[] array = text1.toCharArray();
        char[] array2 = text2.toCharArray();
        boolean flag = true;
        if (array != null && array2 != null) {
            if (!Arrays.equals(array, array2)) {
                flag = false;
            }
        } else if (array == null && array2 == null) {
            flag = false;
        }
        return flag;
    }

    public boolean checkData(String email, String phoneNumber, String pass1, String pass2) {
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(null, "Please insert a valid email",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!checkPhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(null, "Please insert a valid phone number",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!checkPasswordConfirmation(pass1, pass2)) {
            JOptionPane.showMessageDialog(null, "Confirm password failed",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}



