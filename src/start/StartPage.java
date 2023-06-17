package start;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import stage.Stage;
import stage.body.StageBody;

public class StartPage extends JFrame implements Runnable {

    StartBackground backgroundPanel;
    int focusButton = 1;

    public StartPage() {

        ImageIcon backgroundImage = new ImageIcon("img//start.png");
        ImageIcon singleButtonImage = new ImageIcon("img//selectedButton.png");
        ImageIcon doubleButtonImage = new ImageIcon("img//doublePlayersButton.png");

        backgroundPanel = new StartBackground(-1, this.focusButton);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        JButton singlePlayer = new JButton(singleButtonImage);
        singlePlayer.setContentAreaFilled(false);
        singlePlayer.setFocusPainted(false);
        singlePlayer.setBorderPainted(false);
        singlePlayer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        singlePlayer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StartPage.this.focusButton = 1;
            }
        });
        singlePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartPage.this.setVisible(false);
                JFrame stage = new Stage(StartPage.this);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ((Stage) stage).init();
                        stage.revalidate();
                        (((Stage) stage).getBackgroundPanel()).repaint();
                        revalidate();
                    }
                }, 3500);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        StageBody.bcgbgm.loop();
                    }
                }, 4500);
            }
        });

        JButton doublePlayers = new JButton(doubleButtonImage);
        doublePlayers.setContentAreaFilled(false);
        doublePlayers.setFocusPainted(false);
        doublePlayers.setBorderPainted(false);
        doublePlayers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        doublePlayers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StartPage.this.focusButton = 2;
            }
        });

        backgroundPanel.setLayout(null);
        backgroundPanel.add(singlePlayer);
        backgroundPanel.add(doublePlayers);
        singlePlayer.setBounds(211, 287, singleButtonImage.getIconWidth(), singleButtonImage.getIconHeight());
        doublePlayers.setBounds(211, 328, doubleButtonImage.getIconWidth(), doubleButtonImage.getIconHeight());

        this.add(backgroundPanel);
        this.setTitle("BattleCity");
        this.setIconImage(new ImageIcon("img//symbol.png").getImage());
        this.setResizable(false);
        this.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight() + 25);
        this.setLocation(screenSize.width / 2 - this.getWidth() / 2
                , screenSize.height / 2 - this.getWidth() / 2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new Thread(this).start();
    }

    public JPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.backgroundPanel.setFocusButton(this.focusButton);
            repaint();
        }
    }

}
