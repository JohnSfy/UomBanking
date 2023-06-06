package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgressBar extends JDialog{

    private JButton cancelButton;
    private JProgressBar progressBar;
    private ProgressBarListener listener;
    public ProgressBar(Window parent, String title) {
        super(parent, title, Dialog.ModalityType.APPLICATION_MODAL);

        cancelButton = new JButton("Cancel");
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        progressBar.setString("Logging in...");

        setLayout(new FlowLayout());

        add(progressBar);
        add(cancelButton);

        Dimension size = cancelButton.getPreferredSize();
        size.width = 400;

        progressBar.setPreferredSize(size);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener != null) {
                    listener.progressBarCancelled();
                }
            }
        });

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (listener != null) {
                    listener.progressBarCancelled();
                }
            }
        });

        pack();

        setLocationRelativeTo(parent);
    }

    public void setListener(ProgressBarListener listener) {
        this.listener = listener;
    }

    @Override
    public void setVisible(boolean visible) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(!visible) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    progressBar.setValue(0);
                }

                if(visible) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }else{
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }

                ProgressBar.super.setVisible(visible);
            }
        });
    }
    public void setMaximum(int value) {
        progressBar.setMaximum(value);
    }

    public void setValue(int value){
        int progress = 100 * value/progressBar.getMaximum();

        progressBar.setString(String.format("%d%% complete",progress));
        progressBar.setValue(value);
    }
}
