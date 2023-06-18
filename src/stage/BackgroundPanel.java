package stage;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    private ImageIcon backgroundImage;

    public BackgroundPanel(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.setOpaque(true);
        this.setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0
                , backgroundImage.getIconWidth(), backgroundImage.getIconHeight(), this);
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
    }


}
