package stage;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StageScore extends JPanel {

    private int stage;
    private int playerLife;
    private int aliveEnemy;
    ImageIcon scoreImage = new ImageIcon("img//score.png");
    ImageIcon enemyImage = new ImageIcon("img//enemy.png");
    ImageIcon playerImage = new ImageIcon("img//player.png");
    ImageIcon singlePlayerImage = new ImageIcon("img//1P.png");
    ImageIcon flagImage = new ImageIcon("img//flag.png");
    // number
    ImageIcon oneImage = new ImageIcon("img//number//1.png");
    ImageIcon twoImage = new ImageIcon("img//number//2.png");
    ImageIcon threeImage = new ImageIcon("img//number//3.png");
    ImageIcon fourImage = new ImageIcon("img//number//4.png");
    ImageIcon fiveImage = new ImageIcon("img//number//5.png");
    ImageIcon sixImage = new ImageIcon("img//number//6.png");
    ImageIcon sevenImage = new ImageIcon("img//number//7.png");
    ImageIcon eightImage = new ImageIcon("img//number//");
    ImageIcon nineImage = new ImageIcon("img//number//9.png");
    ImageIcon zeroImage = new ImageIcon("img//number//0.png");

    public StageScore(int stage, int playerLife, int aliveEnemy) {
        this.stage = stage;
        this.playerLife = playerLife;
        this.aliveEnemy = aliveEnemy;
        this.setOpaque(true);
    }

    private int enemyX = 21;
    private int enemyY = 22;
    private int singleX = 24;
    private int singleY = 299;
    private int playerX = 21;
    private int playerY = 319;
    private int flagX = 19;
    private int flagY = 419;
    private int numberX = 41;
    private int numberY = 319;
    private int stageX = 44;
    private int stageY = 459;

    @Override
    public void paintComponent(Graphics g) {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enemyX = 21;
        enemyY = 22;
        super.paintComponent(g);
        g.drawImage(scoreImage.getImage(), 0, 0, this);
        for (int from = 0; from < this.getAliveEnemy(); from++) {
            g.drawImage(enemyImage.getImage(), enemyX, enemyY, this);
            if ((from + 1) % 2 == 0) {
                enemyX = 21;
                enemyY += 20;
            } else {
                enemyX += 20;
            }
        }
        g.drawImage(singlePlayerImage.getImage(), singleX, singleY, this);
        g.drawImage(playerImage.getImage(), playerX, playerY, this);
        g.drawImage(checkNumber(this.getPlayerLife()), numberX, numberY, this);
        g.drawImage(flagImage.getImage(), flagX, flagY, this);

        if (stage < 10) {
            g.drawImage(checkNumber(this.getStage()), stageX, stageY, this);
        } else {
            g.drawImage(checkNumber(this.getStage() / 10), stageX - 20, stageY, this);
            g.drawImage(checkNumber(this.getStage() % 10), stageX, stageY, this);
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

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getPlayerLife() {
        return playerLife;
    }

    public void setPlayerLife(int playerLife) {
        this.playerLife = playerLife;
    }

    public int getAliveEnemy() {
        return aliveEnemy;
    }

    public void setAliveEnemy(int aliveEnemy) {
        this.aliveEnemy = aliveEnemy;
    }

}
