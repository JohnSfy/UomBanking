package gui;

import model.Account;
import model.Card;
import org.example.CardDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static java.awt.Color.BLACK;
import static java.awt.Color.getColor;
import static java.lang.Long.toHexString;

public class CreateNewCardFrame extends JFrame {

    private JFrame fr;
    private JLabel header;
    private JLabel nameLabel; //θα φιλοξενήσει το όνομα
    private JTextField nameText;
    private JLabel typeLabel;
    private JComboBox type; //για την επιλογη του τυπυ της καρτας
    private JLabel colorLabel; //θα φιλοξενήσει το χρώμα
    private JButton colorButton; //κουμπί για αλλαγή χρώματος
    private JLabel pinLabel; //θα φιλοξενήσει το πιν
    private JPasswordField pinField;
    private JLabel conPinLabel;
    private JPasswordField conPinField;
    private JButton createCardButton;//κουμπί για δημιουργία κάρτας
    private JButton  returnToMainPageButton;//κουμπί για επιστροφή στο αρχικό μενού
    private JLabel message; //υπενθύμιση για πιν
    private JPanel chipPanel; //υποθετικό τσιπ κάρτας
    public JColorChooser coChooser; //για επιλογή χρώματος
    private JLabel line;
    String pin1;
    String pin2;
    Color color;

    public CreateNewCardFrame(Account account) {
        this.setLayout(null);

//      Initializing
        fr = new Template();
        nameText = new JTextField();
        type = new JComboBox();
        pinField = new JPasswordField(4);
        conPinField = new JPasswordField(4);
        createCardButton = new JButton("Create card");
        chipPanel = new JPanel();
        line = new JLabel("__________________________________________________________________________________________________________________________");
        coChooser = new JColorChooser();

        String[] choices = {"VISA", "MasterCard"}; //δυο τυποί κάρτας
        message = new JLabel("Enter 4 digits");
        message.setFont(new Font("Tahoma", Font.PLAIN, 10));

        header = Utils.setHeader("Enter card details");
        header.setBounds(400,100,1000,100);

        line.setBounds(180,140,900,100);

        nameLabel = new JLabel("Name in the card");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        typeLabel = new JLabel("Type");
        typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        final JComboBox<String> cb = new JComboBox<String>(choices); //για επιλογή τύπου κάρτας

        colorLabel = new JLabel("Color");
        colorLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        colorButton = new JButton("Choose Color");
        colorButton.setFont(new Font("Tahoma", Font.PLAIN, 10));

        pinLabel = new JLabel("Pin");
        pinLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        conPinLabel = new JLabel("Confirm Pin");
        conPinLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        colorButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        createCardButton.setFont(new Font("Tahoma", Font.PLAIN, 13));

        //Με το πάτημα του κουμπιού θα εμφανίζονται τα διαθέσιμα χρώματα για επιλογή
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == colorButton)
                {
                    color = coChooser.showDialog(null, "", BLACK);
                }
            }
        });

        //Τοποθέτηση στοιχείων στις κατάλληλες διαστάσεις
        nameLabel.setBounds(360, 200, 1000, 100);
        nameText.setBounds(480, 240, 150, 20);
        typeLabel.setBounds(445, 250, 1000, 100);
        cb.setBounds(480, 290, 150, 20);
        cb.setFont(new Font("Tahoma", Font.PLAIN, 10));
        colorLabel.setBounds(445, 310, 1000, 100);
        colorButton.setBounds(480, 350, 100, 20);
        pinLabel.setBounds(460, 350, 1000, 100);
        pinField.setBounds(480, 390, 150, 20);
        message.setBounds(480, 405, 1000, 20);
        conPinLabel.setBounds(400, 400, 1000, 100);
        conPinField.setBounds(480, 440, 150, 20);
        createCardButton.setBounds(500, 480, 100, 25);

        createCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Παίρνω το String Value των pin για να τα ελέγξω στη μέθοδο check pin
                pin1 = String.valueOf(pinField.getPassword());
                pin2 = String.valueOf(conPinField.getPassword());

                if (!checkPin(pin1, pin2)) {
                    JOptionPane.showMessageDialog(fr, "Please insert an acceptable pin!",
                            "Pin Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    //Δημιουργείται η κάρτα
                    Card card = new Card(account.getID(),0,"",0,"MasterCard", color.toString(),"");
                    long cardNum = card.getCardNumber();
                    int cardCVV = card.getCvv();
                    String cardExp = card.getExpirationDate();
                    String cardName  = nameText.getText();
                    String typedText = ((JTextField)cb.getEditor().getEditorComponent()).getText(); //παίρνω επιλογή απο τύπο κάρτας

                    if(cardName.equals("")){
                        JOptionPane.showMessageDialog(fr, "Please insert a Name!",
                                "Name Error", JOptionPane.ERROR_MESSAGE);
                    }

                    else {
//
                        fr.dispose();
                        Card acard = new Card(account.getID(),cardNum,cardExp,cardCVV,typedText,color.toString(),cardName);
                        new PreviewCardFrame(account,typedText, cardNum, cardExp, cardName, cardCVV, color);
                        CardDB.saveCard(acard);
                    }
                }
            }
        });

        returnToMainPageButton = Utils.returnToMainPageButton(fr, account);
        returnToMainPageButton.setBounds(450, 550, 200, 25);


        //Τοποθέτηση στοιχείων στο Frame

        fr.add(header);
        fr.add(line);
        fr.add(nameLabel);
        fr.add(nameText);
        fr.add(typeLabel);
        fr.add(cb);
        fr.add(pinLabel);
        fr.add(pinField);
        fr.add(message);
        fr.add(colorLabel);
        fr.add(colorButton);
        fr.add(conPinLabel);
        fr.add(conPinField);
        fr.add(createCardButton);
        fr.add(returnToMainPageButton);

        fr.setVisible(true);
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fr.setBackground(Color.LIGHT_GRAY);
    }

//  Μέθοδος για τον έλεγχο του pin
    public boolean checkPin(String pin1, String pin2) {

        int le1, le2;
        boolean flag1 = true;
        boolean flag2 = true;

        le1 = pin1.length();
        le2 = pin2.length();

        for (int i = 0; i < le1; i++) {
            if (!Character.isDigit(pin1.charAt(i))) {
                flag1 = false;
                break;
            }

        }

        for (int i = 0; i < le2; i++) {
            if (!Character.isDigit(pin2.charAt(i))) {
                flag2 = false;
                break;
            }

        }

        if ((le1 == 4 && le2 == 4) && (flag1 && flag2))
        {
            if (pin1.equals(pin2))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}


