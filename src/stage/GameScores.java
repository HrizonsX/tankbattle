package stage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class GameScores extends JPanel {
    private Image gradeImg = new ImageIcon("img//scores.png").getImage();
    private static Image[] digit = new Image[10];
    private static Image[] odigit = new Image[10];
    private int stage;
    private int tank1;
    private int tank2;
    private int tank3;
    private int total;
    private int totalPts;

    public static void initialize() {
        for (int i = 0; i < 10; i++) {
            String photo = "img//" + i + ".png";
            digit[i] = new ImageIcon(photo).getImage();
            photo = "img//number//0" + i + ".png";
            odigit[i] = new ImageIcon(photo).getImage();
        }
    }

    public GameScores(int tank1, int tank2, int tank3, int tool, int stage, int totalPts) {
        this.tank1 = tank1;
        this.tank2 = tank2;
        this.tank3 = tank3;
        this.total = tank1 + tank2 + tank3;
        this.totalPts = totalPts + tank1 * 100 + tank2 * 200 + tank3 * 300 + tool * 200;
        this.stage = stage;
        initialize();
        this.repaint();
    }

    public int getTotalPts() {
        return totalPts;
    }

    public void setTotalPts(int totalPts) {
        this.totalPts = totalPts;
    }

    public void drawGrade(Graphics g, int tank, int flag) {
        int pts = tank * (flag + 1), a = 0, b = 0, c = 0, d = 0, e = 0;
        if (tank == 0) {
            g.drawImage(digit[0], 119, 208 + flag * 52, this);
            g.drawImage(digit[0], 233, 208 + flag * 52, this);
        } else {
            a = (tank / 10) % 10;
            b = tank % 10;
            c = (pts / 100) % 10;
            d = (pts / 10) % 10;
            e = pts % 10;
            g.drawImage(digit[b], 233, 208 + flag * 52, this);
            g.drawImage(digit[0], 103, 208 + flag * 52, this);
            g.drawImage(digit[0], 119, 208 + flag * 52, this);
            g.drawImage(digit[e], 87, 208 + flag * 52, this);
            if (a != 0) {
                g.drawImage(digit[a], 217, 208 + flag * 52, this);
            }
            if (c != 0) {
                g.drawImage(digit[c], 55, 208 + flag * 52, this);
                g.drawImage(digit[d], 71, 208 + flag * 52, this);
            }
            if (c == 0 && d != 0)
                g.drawImage(digit[d], 71, 208 + flag * 52, this);
        }
    }

    public void drawTotal(Graphics g, int total) {
        int a = 0, b = 0, c = 0;
        a = (total / 100) % 10;
        b = (total / 10) % 10;
        c = total % 10;
        g.drawImage(digit[c], 265, 400, this);
        if (a != 0) {
            g.drawImage(digit[a], 233, 400, this);
            g.drawImage(digit[b], 249, 400, this);
        }
        if (b != 0)
            g.drawImage(digit[b], 249, 400, this);

    }

    public void drawStage(Graphics g, int stage) {
        int a = 0, b = 0;
        a = (stage / 10) % 10;
        b = stage % 10;
        g.drawImage(digit[b], 374, 86, this);
        if (a != 0)
            g.drawImage(digit[a], 358, 86, this);

    }

    public void drawTotalPts(Graphics g, int totalPts) {
        int a = 0, b = 0, c = 0;
        g.drawImage(odigit[0], 189, 155, this);
        if (totalPts != 0) {
            a = (totalPts / 10000) % 10;
            b = (totalPts / 1000) % 10;
            c = (totalPts / 100) % 10;
            g.drawImage(odigit[0], 173, 155, this);
            g.drawImage(odigit[c], 157, 155, this);
            if (a != 0) {
                g.drawImage(odigit[a], 125, 155, this);
                g.drawImage(odigit[b], 141, 155, this);
            }
            if (b != 0)
                g.drawImage(odigit[b], 141, 155, this);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);
        g.drawImage(gradeImg, 0, 0, 599, 520, this);
        drawGrade(g, tank1, 0);
        drawGrade(g, tank2, 1);
        drawGrade(g, tank3, 2);
        drawTotal(g, total);
        drawStage(g, stage);
        drawTotalPts(g, totalPts);
    }
}