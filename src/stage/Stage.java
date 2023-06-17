package stage;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import stage.body.StageBody;
import stage.body.map.Stage1;
import stage.body.map.Stage2;
import stage.body.map.Stage3;
import start.StartPage;
import start.StartBackground;

/**
 * 切换游戏关卡和显示记分榜
 */
public class Stage extends JFrame implements Runnable {

    private JPanel backgroundPanel;
    private StageBody stagePanel;
    private StageScore score;
    private StartPage startPage;
    private int stage;
    private int totalStage;
    private int totalPts;
    public static ImageIcon stage1Image = new ImageIcon("img//stage1.png");
    public static ImageIcon stage2Image = new ImageIcon("img//stage2.png");
    public static ImageIcon stage3Image = new ImageIcon("img//stage3.png");
    public static Boolean gameOver = false;

    public Stage(StartPage start) {
        this.stage = 1;
        this.totalStage = 3;
        this.totalPts = 0;
        gameOver = false;
        this.startPage = start;
        backgroundPanel = new BackgroundPanel(stage1Image);
        StageBody.map = new Stage1();
        stagePanel = new StageBody();
        this.stagePanel.setEnemyTanksAward(1);
        this.stagePanel.setEnemyTanksNumberLimit(7);
        this.stagePanel.setEnemyTanksFatty(3);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        try {
            AudioClip bgm = Applet.newAudioClip(new File("bgm//gameStart.wav").toURL());
            //bgm.play();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
        this.add(backgroundPanel);
        this.setResizable(false);
        this.setTitle("Stage 1");
        this.setIconImage(new ImageIcon("img//symbol.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(520 + 79, 520 + 28);
        this.setLocation(screenSize.width / 2 - this.getWidth() / 2
                , screenSize.height / 2 - this.getWidth() / 2);
        this.setVisible(true);
    }

    public JPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    public void init() {
        this.remove(backgroundPanel);
        this.setLayout(null);
        stagePanel.setBounds(0, 0, 520, 520);
        this.add(stagePanel);
        score = new stage.StageScore(this.stage, 2, this.stagePanel.getEnemyTanksNumberLimit());
        score.setBounds(520, 0, 79, 520);
        this.add(score);
        this.setSize(520 + 79, 520 + 28);
        this.addKeyListener(stagePanel);
        new Thread(stagePanel).start();
        if (stage == 1) {
            new Thread(this).start();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.score.setStage(this.stage);
            this.score.setAliveEnemy(this.stagePanel.getEnemyTanksNumberLimit());
            if (this.stagePanel.getPlayerTank().getLife() <= 0) {
                this.score.setPlayerLife(0);
            } else {
                this.score.setPlayerLife(this.stagePanel.getPlayerTank().getLife());
            }
            this.score.repaint();

            if (this.stagePanel.getGameOver() != null || this.stagePanel.getEnemyTanksNumberLimit() == -1) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.stagePanel.getPlayerTank().setMoving(false);
                this.remove(this.score);
                this.remove(this.stagePanel);
                GameScores g = new GameScores(this.stagePanel.getScore().getNormalTank(), this.stagePanel.getScore().getFastTank()
                        , this.stagePanel.getScore().getFattyTank(), this.stagePanel.getScore().getTool(), stage, 0);
                g.setBounds(0, 0, 599, 520);
                this.add(g);
                this.repaint();
                this.totalPts += g.getTotalPts();
                if (this.stagePanel.getGameOver() != null) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.remove(g);
                    this.add(new GameOver());
                    gameOver = true;
                    this.repaint();
                    try {
                        AudioClip bgm = Applet.newAudioClip(new File("bgm//gameOver.wav").toURL());
                        bgm.play();
                    } catch (MalformedURLException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.setVisible(false);
                    this.startPage.setVisible(true);
                    ((StartBackground) this.startPage.getBackgroundPanel()).setPts(this.totalPts);
                    this.startPage.repaint();
                    StageBody.homeAlive = true;
                    this.stagePanel.power = null;
                    this.stagePanel.setGameOver(null);
                } else if (this.stagePanel.getEnemyTanksNumberLimit() == -1) {
                    // 赢了就跳下一关
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.stage++;
                    if (stage > totalStage) {
                        break;
                    }
                    this.setTitle("Stage " + stage);
                    try {
                        AudioClip bgm = Applet.newAudioClip(new File("bgm//gameStart.wav").toURL());
                        bgm.play();

                    } catch (MalformedURLException e2) {
                        e2.printStackTrace();
                    }
                    this.remove(g);
                    switch (this.stage) {
                        case 1:
                            this.backgroundPanel = new BackgroundPanel(stage1Image);
                            break;
                        case 2:
                            this.backgroundPanel = new BackgroundPanel(stage2Image);
                            break;
                        case 3:
                            this.backgroundPanel = new BackgroundPanel(stage3Image);
                            break;
                        default:
                            break;
                    }
                    this.backgroundPanel.setBounds(0, 0, 599, 520);
                    this.add(backgroundPanel);
                    this.repaint();
                    try {
                        Thread.sleep(3500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    switch (this.stage) {
                        case 1:
                            StageBody.map = new Stage1();
                            this.stagePanel = new StageBody();
                            this.stagePanel.setEnemyTanksAward(1);
                            this.stagePanel.setEnemyTanksFatty(3);
                            this.stagePanel.setEnemyTanksNumberLimit(9);
                            break;
                        case 2:
                            StageBody.map = new Stage2();
                            this.stagePanel = new StageBody();
                            this.stagePanel.setEnemyTanksAward(2);
                            this.stagePanel.setEnemyTanksFatty(5);
                            this.stagePanel.setEnemyTanksNumberLimit(11);
                            break;
                        case 3:
                            StageBody.map = new Stage3();
                            this.stagePanel = new StageBody();
                            this.stagePanel.setEnemyTanksAward(3);
                            this.stagePanel.setEnemyTanksFatty(7);
                            this.stagePanel.setEnemyTanksNumberLimit(15);
                            break;
                        default:
                            break;
                    }
                    StageBody.power = null;
                    init();
                    try {
                        Thread.sleep(1000);
                        StageBody.bcgbgm.loop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
