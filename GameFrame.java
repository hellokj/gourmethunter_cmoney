import panels.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame {
    public static void main(String[] args) {
        final JFrame mainFrame = new JFrame();
        MainPanel mp = new MainPanel();
        mainFrame.setTitle("Gourmet Hunter");
        mainFrame.setSize(500, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(mp);

        mainFrame.setVisible(true);

        Timer t1 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.repaint();
            }
        });
        t1.start();
    }
}