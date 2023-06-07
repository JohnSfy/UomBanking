package gui;

import model.Client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class CreateAccountPanel extends JPanel {
    private JCheckBox showPassword;
    private JPasswordField passwordField;
    private JPasswordField passwordField2;
    private JLabel message;
    private JLabel checkLabel;
    private JComboBox costPerTransaction;
    private JTextField phoneNumber;
    private JTextField email;

    private JTextField username;

    public JTextField getUsername() {
        return username;
    }

    public JTextField getPhoneNumber() {
        return this.phoneNumber;
    }

    public JTextField getEmail() {
        return this.email;
    }

    public JPasswordField getPasswordField() {
        return this.passwordField;
    }

    public JPasswordField getPasswordField2() {
        return this.passwordField2;
    }

    public JComboBox getCostPerTransaction() {
        return costPerTransaction;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    private JTextField firstName;
    private JTextField lastName;

    private Client client;


    public CreateAccountPanel() {

        showPassword = new JCheckBox("Show Password");
        passwordField = new JPasswordField(10);
        passwordField2 = new JPasswordField(10);
        message = new JLabel("Make sure to use a strong password");
        checkLabel = new JLabel();
        username = new JTextField(10);

        costPerTransaction = new JComboBox();

//      Setting up the message label
        message.setFont(new Font("Tahoma", Font.PLAIN, 10));

        showPassword.setFont(new Font("Tahoma",Font.PLAIN,15));

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

//      Setting up the costPerTransaction combo box
        DefaultComboBoxModel costPerTransactionModel = new DefaultComboBoxModel();
        costPerTransactionModel.addElement("100");
        costPerTransactionModel.addElement("500");
        costPerTransactionModel.addElement("Other");
        costPerTransaction.setModel(costPerTransactionModel);
        costPerTransaction.setSelectedIndex(0);
        costPerTransaction.setEditable(true);


//      Setting up the confirm password field
        passwordField2.setEchoChar('•');
        passwordField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (passwordField2.getText().length() != 0) {
                    if (passwordField2.getText().equals(passwordField.getText())) {
                        checkLabel.setVisible(true);
                        checkLabel.setSize(18, 20);
                        checkLabel.setIcon(Utils.setLabelIcon("src/main/java/images/Valid.png", checkLabel));
                        checkLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));

                    } else {
                        checkLabel.setSize(18, 20);
                        checkLabel.setVisible(true);
                        checkLabel.setIcon(Utils.setLabelIcon("src/main/java/images/invalid.png", checkLabel));
                        checkLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
                    }
                }
            }
        });

//      Basic setup for the panel
        setBounds(350, 200, 500, 500);
        setBackground(Color.LIGHT_GRAY);
        Border border = BorderFactory.createTitledBorder("Enter your information");
        setBorder(border);
        setSize(new Dimension(500, 450));
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
        JLabel fName = new JLabel("First Name");
        fName.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(fName, gc);

//      First row-Second column
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(firstName = new JTextField(10), gc);

//      Second row-First column
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        JLabel lName = new JLabel("Last Name");
        lName.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(lName, gc);

//      Second row-Second column
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(lastName = new JTextField(10), gc);

//      Third row-First column
        gc.gridy++;
        gc.gridx = 0;
        gc.weighty = 0.1;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        JLabel phoneNum = new JLabel("Phone Number");
        phoneNum.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(phoneNum, gc);

//      Third row-Second column
        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(phoneNumber = new JTextField(10), gc);

//      Fourth row-First column
        gc.gridy++;
        gc.gridx = 0;
        gc.weighty = 0.1;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        JLabel emailL = new JLabel("Email");
        emailL.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(emailL, gc);

//      Fourth row-Second column
        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(email = new JTextField(10), gc);

        gc.gridy++;
        gc.gridx = 0;
        gc.weighty = 0.1;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        JLabel userName = new JLabel("Username");
        userName.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(userName, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(username, gc);

//      Fifth row-First column
        gc.gridy++;
        gc.gridx = 0;
        gc.weighty = 0.1;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        JLabel password = new JLabel("Password");
        password.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(password, gc);

//      Fifth row-Second column
        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(passwordField, gc);

//      Fifth row-Third column
        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(showPassword, gc);

//      Sixth row-First column
//        gc.gridy++;
//        gc.gridx++;
//        gc.weighty = 0.1;
//        gc.weightx = 1;
//        gc.anchor = GridBagConstraints.LAST_LINE_END;
//        gc.insets = new Insets(0, 0, 0, 0);
//        add(message, gc);

//      Seventh row-First column
        gc.gridy++;
        gc.gridx = 0;
        gc.weighty = 0.1;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        JLabel conPassword = new JLabel("Confirm Password");
        conPassword.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(conPassword, gc);

//      Seventh row-Second column
        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(passwordField2, gc);

//      Seventh row-Third column
        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        checkLabel.setVisible(false);
        add(checkLabel, gc);

//      Eighth row-First column
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        JLabel costPerTrans = new JLabel("Cost Per transaction");
        costPerTrans.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(costPerTrans, gc);

//      Eighth row-Second column
        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(costPerTransaction, gc);

        return null;
    }

    public void checkPhoneNumber(String number) {
        char[] array = number.toCharArray();
        for (char c: array) {
            if (!Character.isDigit(c)) {
                JOptionPane.showMessageDialog(this, "Please insert a valid phone number",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}