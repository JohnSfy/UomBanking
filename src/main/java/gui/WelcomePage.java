package gui;

import model.Account;
import model.Client;
import model.Main;
import org.example.AccountDB;
import org.example.ClientDB;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    private JLabel header;
    private JLabel signIn;
    private JPanel infoPanel;
    private JButton createAccountButton;
    private JLabel message;
    public JFrame welcomePage;

    private Client client;
    private Account account;

    private JTextField username;
    public JTextField getUsername() {
        return username;
    }





     public WelcomePage(){
         welcomePage = new TemplateNotLoggedIn();

//       Setting layout manager to null for absolute positioning
         welcomePage.setLayout(null);

//       Initializing components
         header = Utils.setHeader("Welcome to UoMBanking");
         signIn = new JLabel("Sign In");
         infoPanel = new JPanel();
         createAccountButton = new JButton("Create an account");
         message = new JLabel("New Here?");

//       Setting up JLabel signIn
         signIn.setBounds(550, 300, 1000, 100);
         signIn.setFont(new Font("Courier", Font.PLAIN, 25));

//       Setting up the information panel
         infoPanel = new InfoPanel();

//       Setting up createAccountButton
         createAccountButton.setBounds(590, 675, 200, 50);
         createAccountButton.setFont(new Font("Courier", Font.PLAIN, 15));
         createAccountButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 welcomePage.dispose();
                 new CreateAccount();
             }
         });

//       Setting up message
         message.setBounds(500, 650, 300, 100);
         message.setFont(new Font("Courier", Font.PLAIN, 15));

//       Adding components to the frame
         welcomePage.add(header);
         welcomePage.add(signIn);
         welcomePage.add(infoPanel);
         welcomePage.add(createAccountButton);
         welcomePage.add(message);

         client = new Client();

//       Basic setup for the frame
         welcomePage.setVisible(true);
         welcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         welcomePage.getContentPane().setBackground(Color.LIGHT_GRAY);
         welcomePage.validate(); //validates the images
     }

    class InfoPanel extends JPanel{
        private JCheckBox showPassword;
        private JPasswordField passwordField;
        public JButton signInButton;

        public InfoPanel() {
//      Initializing components
            showPassword = new JCheckBox("Show Password");
            passwordField = new JPasswordField(10);
            signInButton = new JButton("Sign In");

//      Setting up showPassword button
            passwordField.setEchoChar('•');
            showPassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (showPassword.isSelected()) {
                        passwordField.setEchoChar((char) 0);
                    } else {
                        passwordField.setEchoChar('•');
                    }
                }
            });

//      Setting up signInButton
            signInButton.addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {

                    JTextField usernameField = (JTextField) infoPanel.getComponent(1);
                    String username = usernameField.getText();
                    String password = passwordField.getText();
//                    //check database for user verify

                    if (ClientDB.fetchClient(username) != null) {
                        if (ClientDB.fetchClient(username).getPassword().equals(password)) {
                            account = AccountDB.fetchAccount(username);
                            welcomePage.dispose();
                            new MainFrame(account);
                        } else {
                            JOptionPane.showMessageDialog(welcomePage, "Incorrect password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            // Clear the password field
                            passwordField.setText("");
                            // Request focus on the password field for user convenience
                            passwordField.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(welcomePage, "Invalid username. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        // Clear both the username and password fields
                        usernameField.setText("");
                        passwordField.setText("");
                        // Request focus on the username field for user convenience
                        usernameField.requestFocus();
                    }

                }
            });

//      Basic setup for the panel
            setBounds(350, 400, 500, 300);
            setBackground(Color.LIGHT_GRAY);
            Border border = BorderFactory.createTitledBorder("Information");
            setBorder(border);
            setSize(new Dimension(500, 250));
            layoutComponents();
        }

        public LayoutManager layoutComponents() {
            setLayout(new GridBagLayout());

            GridBagConstraints gc = new GridBagConstraints();

//      First row-First column
            gc.gridy = 0;

            gc.weightx = 1;
            gc.weighty = 0.1;
            gc.gridx = 0;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(0, 0, 0, 5);
            add(new JLabel("Username"), gc);

//      First row-Second column
            gc.gridx = 1;
            gc.anchor = GridBagConstraints.LINE_START;
            gc.insets = new Insets(0, 0, 0, 0);
            add(new JTextField(10), gc);

//      Second row-First column
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 0.1;
            gc.gridx = 0;
            gc.anchor = GridBagConstraints.FIRST_LINE_END;
            gc.insets = new Insets(0, 0, 0, 5);
            add(new JLabel("Password"), gc);

//      Second row-Second column
            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0, 0, 0, 0);
            add(passwordField, gc);

//      Second row-Third column
            gc.gridx = 2;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0, 0, 0, 0);
            add(showPassword, gc);

//      Third row-First column
            gc.gridy++;
            gc.gridx = 1;
            gc.weighty = 0.1;
            gc.weightx = 1;
            gc.insets = new Insets(0, 0, 0, 0);
            add(signInButton, gc);

            return null;
        }

    }
}



