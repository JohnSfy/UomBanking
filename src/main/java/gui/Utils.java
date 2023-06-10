package gui;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Utils {

    //  Used for setting a Jlabel with an icon
    public static ImageIcon setLabelIcon(String path, JLabel logo) {

        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH); //resizing the image to fit the label
        return new ImageIcon(newImg);
    }

    //  Used for setting a header
    public static JLabel setHeader(String text) {
        JLabel header = new JLabel(text);
        header.setBounds(350, 100, 1000, 100);
        header.setFont(new Font("Tahoma", Font.PLAIN, 45));
        return header;
    }

    public static JButton returnToMainPageButton(JFrame frame, Account account) {
        JButton returnToMainPageButton = new JButton("Return to main page");
        returnToMainPageButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        returnToMainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Do you want to return to the main page?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new MainFrame(account);
                }
            }
        });

        return returnToMainPageButton;
    }
}
