package start;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartBackground extends JPanel {

    private ImageIcon backgroundImage = new ImageIcon("img//start.png");
    private ImageIcon smallTankImage = new ImageIcon("img//startButton.gif");
    private ImageIcon zeroImage = new ImageIcon("img//0.png");
    private ImageIcon oneImage = new ImageIcon("img//1.png");
    private ImageIcon twoImage = new ImageIcon("img//2.png");
    private ImageIcon threeImage = new ImageIcon("img//3.png");
    private ImageIcon fourImage = new ImageIcon("img//4.png");
    private ImageIcon fiveImage = new ImageIcon("img//5.png");
    private ImageIcon sixImage = new ImageIcon("img//6.png");
    private ImageIcon sevenImage = new ImageIcon("img//7.png");
    private ImageIcon eightImage = new ImageIcon("img//8.png");
    private ImageIcon nineImage = new ImageIcon("img//9.png");
    private int pts;
    private int focusButton;

    public StartBackground(int pts, int focusButton) {
        if (pts != -1) {
            this.pts = pts % 100;
        } else {
            this.pts = -1;
        }
        this.focusButton = focusButton;
        this.setOpaque(true);
        this.setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0
                , backgroundImage.getIconWidth(), backgroundImage.getIconHeight(), this);
        if (this.focusButton == 1) {
            g.drawImage(smallTankImage.getImage(), 152, 280, this);
        } else if (this.focusButton == 2) {
            g.drawImage(smallTankImage.getImage(), 152, 321, this);
        }
        int n = this.pts;
        int cnt = 0;
        if (n != -1) {
            while (n != 0) {
                g.drawImage(checkNumber(n % 10), 134 - 17 * cnt, 52, this);
                n /= 10;
                cnt++;
            }
        }
    }

    private Image checkNumber(int n) {
        switch (n) {
            case 0:
                return zeroImage.getImage();
            case 1:
                return oneImage.getImage();
            case 2:
                return twoImage.getImage();
            case 3:
                return threeImage.getImage();
            case 4:
                return fourImage.getImage();
            case 5:
                return fiveImage.getImage();
            case 6:
                return sixImage.getImage();
            case 7:
                return sevenImage.getImage();
            case 8:
                return eightImage.getImage();
            case 9:
                return nineImage.getImage();
            default:
                return null;
        }
    }

    public int getPts() {
        if (this.pts == -1) {
            return -1;
        } else {
            return pts * 100;
        }
    }

    public void setPts(int pts) {
        if (pts == -1) {
            this.pts = -1;
        } else {
            this.pts = pts / 100;
        }
    }

    public int getFocusButton() {
        return focusButton;
    }

    public void setFocusButton(int focusButton) {
        this.focusButton = focusButton;
    }


}

