package stage.body;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import stage.body.buildings.Grass;
import stage.body.buildings.Ice;
import stage.body.buildings.Sea;
import stage.body.buildings.Steel;
import stage.body.buildings.Wall;
import stage.body.map.Map;
import stage.body.power.Clock;
import stage.body.power.Life;
import stage.body.power.Power;
import stage.body.power.Shield;
import stage.body.power.Star;
import stage.body.power.SteelHome;
import stage.body.scores.Score;
import stage.body.tank.Enemy;
import stage.body.tank.Player;
import stage.body.tank.Tank;

// w: UP a: LEFT s: DOWN d: RIGHT space: SHOOT
// type 0: stand for player's tank
// type 1: stand for enemy's tank
// using Thread.sleep to slow repainting down
/* in tanks Array
   0: stand for nothing
   1:stand for tank..
   2:stand for steel
   3:stand for home
   4:stand for wall
   5:stand for sea.
   6:stand for timer
   7:stand for addLife
   8:stand for boom
   9:stand for shield
   10:stand for stars
*/
public class StageBody extends JPanel implements KeyListener, Runnable {

    ImageIcon bulletUImage = new ImageIcon("img//bulletU.png");
    ImageIcon bulletLImage = new ImageIcon("img//bulletL.png");
    ImageIcon bulletDImage = new ImageIcon("img//bulletD.png");
    ImageIcon bulletRImage = new ImageIcon("img//bulletR.png");

    ImageIcon backgroundImage = new ImageIcon("img//body.png");
    ImageIcon wallImage = new ImageIcon("img//wall.png");
    ImageIcon seaImage = new ImageIcon("img//sea.gif");
    ImageIcon homeImage = new ImageIcon("img//home.png");
    ImageIcon iceImage = new ImageIcon("img//ice.png");
    ImageIcon destoryImage = new ImageIcon("img//destory.png");
    ImageIcon grassImage = new ImageIcon("img//grass.png");
    ImageIcon gameOverImage = new ImageIcon("img//over.png");
    ImageIcon steelImage = new ImageIcon("img//steel2.png");

    ImageIcon upImage = new ImageIcon("img//p1tankU.png");
    ImageIcon leftImage = new ImageIcon("img//p1tankL.png");
    ImageIcon downImage = new ImageIcon("img//p1tankD.png");
    ImageIcon rightImage = new ImageIcon("img//p1tankR.png");
    ImageIcon up2Image = new ImageIcon("img//p1tankU2.png");
    ImageIcon left2Image = new ImageIcon("img//p1tankL2.png");
    ImageIcon down2Image = new ImageIcon("img//p1tankD2.png");
    ImageIcon right2Image = new ImageIcon("img//p1tankR2.png");
    ImageIcon upMoveImage = new ImageIcon("img//p1Umove.gif");
    ImageIcon downMoveImage = new ImageIcon("img//p1Dmove.gif");
    ImageIcon leftMoveImage = new ImageIcon("img//p1Lmove.gif");
    ImageIcon rightMoveImage = new ImageIcon("img//p1Rmove.gif");

    ImageIcon enemyDImage = new ImageIcon("img//enemyD.png");
    ImageIcon enemyLImage = new ImageIcon("img//enemyL.png");
    ImageIcon enemyUImage = new ImageIcon("img//enemyU.png");
    ImageIcon enemyRImage = new ImageIcon("img//enemyR.png");
    ImageIcon enemy2UImage = new ImageIcon("img//enemy2U.gif");
    ImageIcon enemy2DImage = new ImageIcon("img//enemy2D.gif");
    ImageIcon enemy2RImage = new ImageIcon("img//enemy2R.gif");
    ImageIcon enemy2LImage = new ImageIcon("img//enemy2L.gif");
    ImageIcon enemy3UImage = new ImageIcon("img//enemy3U.png");
    ImageIcon enemy3DImage = new ImageIcon("img//enemy3D.png");
    ImageIcon enemy3RImage = new ImageIcon("img//enemy3R.png");
    ImageIcon enemy3LImage = new ImageIcon("img//enemy3L.png");
    ImageIcon enemy3UMoveImage = new ImageIcon("img//enemy3Umove.gif");
    ImageIcon enemy3DMoveImage = new ImageIcon("img//enemy3Dmove.gif");
    ImageIcon enemy3RMoveImage = new ImageIcon("img//enemy3Rmove.gif");
    ImageIcon enemy3LMoveImage = new ImageIcon("img//enemy3Lmove.gif");
    ImageIcon enemyLmoveImage = new ImageIcon("img//enemyLmove.gif");
    ImageIcon enemyRmoveImage = new ImageIcon("img//enemyRmove.gif");
    ImageIcon enemyDmoveImage = new ImageIcon("img//enemyDmove.gif");
    ImageIcon enemyUmoveImage = new ImageIcon("img//enemyUmove.gif");

    ImageIcon blast1Image = new ImageIcon("img//blast1.png");
    ImageIcon blast2Image = new ImageIcon("img//blast2.png");
    ImageIcon blast3Image = new ImageIcon("img//blast3.png");
    ImageIcon blast4Image = new ImageIcon("img//blast4.png");
    ImageIcon blast5Image = new ImageIcon("img//blast5.png");
    ImageIcon blast6Image = new ImageIcon("img//blast6.png");
    ImageIcon blast7Image = new ImageIcon("img//blast7.png");

    // tools: 加盾、加生命条、时间暂停、自爆
    ImageIcon shieldImage = new ImageIcon("img//shield.gif");
    ImageIcon boomImage = new ImageIcon("img//boom.gif");
    ImageIcon addLifeImage = new ImageIcon("img//addLife.gif");
    ImageIcon timeImage = new ImageIcon("img//timer.gif");
    ImageIcon addShieldImage = new ImageIcon("img//addShield.gif");
    ImageIcon addScoreImage = new ImageIcon("img//addScore.gif");
    ImageIcon steelHomeImage = new ImageIcon("img//steelHome.gif");
    ImageIcon steelToWall = new ImageIcon("img//steelWall.gif");

    ImageIcon born1Image = new ImageIcon("img//born1.png");
    ImageIcon born2Image = new ImageIcon("img//born2.png");
    ImageIcon born3Image = new ImageIcon("img//born3.png");
    ImageIcon born4Image = new ImageIcon("img//born4.png");

    ImageIcon fastUImage = new ImageIcon("img//enemyFU.gif");
    ImageIcon fastLImage = new ImageIcon("img//enemyFL.gif");
    ImageIcon fastDImage = new ImageIcon("img//enemyFD.gif");
    ImageIcon fastRImage = new ImageIcon("img//enemyFR.gif");

    Player playerTank;
    int enemyTanksAward = 0;
    int enemyTanksNumber = 4;
    int enemyTanksNumberLimit = 1;
    int enemyTanksFatty = 0;
    List bulletBoom = new ArrayList<Boom>();
    CopyOnWriteArrayList<Enemy> enemyTanks;
    List seas;
    List grasses;
    public static List steels;
    List ices;
    public static List walls;
    public static Map map;
    Score score;
    public static AudioClip bcgbgm;
    public static AudioClip blastbgm;

    @SuppressWarnings("unchecked")
    public StageBody() {

        this.seas = map.getSeas();
        this.grasses = map.getGrasses();
        this.steels = map.getSteels();
        this.walls = map.getWalls();
        this.ices = map.getIces();

        //积分系统
        score = new Score();
        // init player tank
        this.playerTank = new Player(260 - 32 - 32 - 16, 520 - 32);
        this.enemyTanks = new CopyOnWriteArrayList<Enemy>();
        // init enemy tanks
        for (int numbers = 0; numbers < enemyTanksNumber; numbers++) {
            Enemy enemy = new Enemy((numbers + 1) * 110, 0);
            enemy.setType(1);
            enemy.setMoving(false);
            enemy.setDirection(2);
            this.enemyTanks.add(enemy);
            new Thread(enemy).start();
        }
        // 加载音乐文件, 防止反复的加载而卡顿
        try {
            bcgbgm = Applet.newAudioClip(new File("bgm//move.wav").toURL());
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
        try {
            blastbgm = Applet.newAudioClip(new File("bgm//blast.wav").toURL());
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
    }

    GameOver gameOver = null;
    Boolean win = false;
    public static Boolean homeAlive = true;
    public static Power power = null;
    Boolean once = true;
    Boolean once1 = true;
    Boolean delay = false;
    Boolean enemyMove = true;
    long shieldTime = 5000;
    // 做无敌时间计时
    long clock = 0;
    // 做停止移动的计时
    long clock2 = 0;
    // 做铁制房子的倒计时
    long clock3 = 0;
    // 做道具的倒计时
    long clock4 = 0;

    @Override
    public void paintComponent(Graphics g) {

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.drawImage(backgroundImage.getImage(), 0, 0, this);

        // paint power
        if (power != null) {
            if (power.getAlive()) {
                if (clock4 == 0) {
                    clock4 = System.currentTimeMillis();
                } else {
                    if (System.currentTimeMillis() - clock4 >= 12000) {
                        this.power.setAlive(false);
                        this.power.setType(-1);
                        clock4 = 0;
                    }
                }
                switch (power.getType()) {
                    case 1:
                        // 停止移动
                        g.drawImage(timeImage.getImage(), power.getX(), power.getY(), this);
                        break;
                    case 2:
                        // 加命
                        g.drawImage(addLifeImage.getImage(), power.getX(), power.getY(), this);
                        break;
                    case 3:
                        // 坦克爆炸
                        g.drawImage(boomImage.getImage(), power.getX(), power.getY(), this);
                        break;
                    case 4:
                        // 获得保护盾
                        g.drawImage(addShieldImage.getImage(), power.getX(), power.getY(), this);
                        break;
                    case 5:
                        // 吃星星
                        g.drawImage(addScoreImage.getImage(), power.getX(), power.getY(), this);
                        break;
                    case 6:
                        // 铁房子
                        g.drawImage(steelHomeImage.getImage(), power.getX(), power.getY(), this);
                        break;
                    default:
                        this.power = null;
                        break;
                }
                // then enemys stop moving
            } else {
                switch (power.getType()) {
                    case 1:
                        enemyTanksStopMove();
                        if (clock2 == 0) {
                            this.enemyMove = false;
                            clock2 = System.currentTimeMillis();
                            bcgbgm.stop();
                        } else {
                            if (System.currentTimeMillis() - clock2 >= 12000) {
                                bcgbgm.loop();
                                enemyTanksMove();
                                this.enemyMove = true;
                                power = null;
                                clock2 = 0;
                                // 计算吃到道具的个数
                                score.setTool(score.getTool() + 1);
                            }
                        }
                        break;
                    case 2:
                        this.playerTank.setLife(this.playerTank.getLife() + 1);
                        // 计算吃到道具的个数
                        score.setTool(score.getTool() + 1);
                        power = null;
                        break;
                    case 3:
                        enemyTanksAllDie();
                        // 计算吃到道具的个数
                        score.setTool(score.getTool() + 1);
                        power = null;
                        break;
                    case 4:
                        this.playerTank.setBeatable(false);
                        // 计算吃到道具的个数
                        score.setTool(score.getTool() + 1);
                        power = null;
                        break;
                    case 5:
                        // 双倍得分
                        // 计算吃到道具的个数
                        score.setTool(score.getTool() + 1);
                        this.playerTank.setFastBullet(true);
                        power = null;
                        break;
                    case 6:
                        // 铁房子
                        if (once1) {
                            steelHome();
                            once1 = false;
                        }
                        if (clock3 == 0) {
                            clock3 = System.currentTimeMillis();
                        } else {
                            if (System.currentTimeMillis() - clock3 >= 20000) {
                                // 重变回砖头
                                steelToWallHome();
                                power = null;
                                score.setTool(score.getTool() + 1);
                                clock3 = 0;
                            }
                        }
                        break;
                    default:
                        break;
                }

            }
        }

        // paint ices
        for (int from = 0; from < this.ices.size(); from++) {
            Ice t = (Ice) this.ices.get(from);
            g.drawImage(iceImage.getImage(), t.getX(), t.getY(), this);
        }

        // draw player's tank 5.0
        if (this.playerTank.isVisible() == false) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.playerTank.setMoving(false);
            this.stop = true;
            if (this.playerTank.getWhenBornPaintTimes() > 3) {
                g.drawImage(born1Image.getImage(), this.playerTank.getX(), this.playerTank.getY(), this);
            } else if (this.playerTank.getWhenBornPaintTimes() > 2) {
                g.drawImage(born2Image.getImage(), this.playerTank.getX(), this.playerTank.getY(), this);
            } else if (this.playerTank.getWhenBornPaintTimes() > 1) {
                g.drawImage(born3Image.getImage(), this.playerTank.getX(), this.playerTank.getY(), this);
            } else if (this.playerTank.getWhenBornPaintTimes() > 0) {
                g.drawImage(born4Image.getImage(), this.playerTank.getX(), this.playerTank.getY(), this);
                this.playerTank.setMoving(true);
                this.playerTank.setVisible(true);
            } else {
                this.playerTank.setVisible(true);
            }
            this.playerTank.cut();
        } else if (playerTank.isAlive() == true) {

            paintTank(g, 0, this.playerTank.getX(), this.playerTank.getY(), this.playerTank.getDirection());

            if (this.playerTank.getBeatable() == false) {
                if (clock == 0) {
                    clock = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - clock >= shieldTime) {
                    this.playerTank.setBeatable(true);
                    clock = 0;
                }
            }
            if (this.playerTank.getBeatable() == false) {
                // new born
                g.drawImage(shieldImage.getImage(), this.playerTank.getX(), this.playerTank.getY(), this);
            }
            this.playerTank.move(this.stop);

        } else {
            // if life > 0 then resurgence place 这里需要延迟一下,防止刚复活奇怪的事情发生
            if (playerTank.getLife() == 0) {
                // game is over the gif is like a bullet
                if (this.gameOver == null) {
                    this.gameOver = new GameOver(260 - 40, 520);
                    new Thread(this.gameOver).start();
                    playerTank.setLife(-1);
                }

            } else {
                this.playerTank.setX(260 - 32 - 32 - 16);
                this.playerTank.setY(520 - 32);
            }
        }

        // paint walls
        for (int from = 0; from < this.walls.size(); from++) {
            Wall t = (Wall) this.walls.get(from);
            if (t.getAlive() == true) {
                g.drawImage(wallImage.getImage(), t.getX(), t.getY(), this);
            }
        }

        // paint sea
        for (int from = 0; from < this.seas.size(); from++) {
            Sea t = (Sea) this.seas.get(from);
            g.drawImage(seaImage.getImage(), t.getX(), t.getY(), this);
        }

        // draw player's bullets
        CopyOnWriteArrayList<Bullet> bullets = this.playerTank.getBulletPool();
        for (Bullet bullet : bullets) {
            if (bullet != null) {
                if (bullet.isAlive()) {
                    switch (bullet.getDirection()) {
                        case 0:
                            g.drawImage(bulletUImage.getImage(), bullet.getX(), bullet.getY(), this);
                            break;
                        case 1:
                            g.drawImage(bulletLImage.getImage(), bullet.getX(), bullet.getY(), this);
                            break;
                        case 2:
                            g.drawImage(bulletDImage.getImage(), bullet.getX(), bullet.getY(), this);
                            break;
                        case 3:
                            g.drawImage(bulletRImage.getImage(), bullet.getX(), bullet.getY(), this);
                            break;
                        default:
                            break;
                    }
                } else if (bullet.isHitBuilding()) {
                    if (bullet.getBoomPlace() != null) {
                        this.bulletBoom.add(new Boom(bullet.getBoomPlace().getX(), bullet.getBoomPlace().getY()));
                    } else {
                        this.bulletBoom.add(new Boom(bullet.getX(), bullet.getY()));
                    }
                    bullet.setHitBuilding(false);
                    if (bullet.getHitSteel() == true) {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//hitLimit.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }

        // draw enemy tanks and born.gif
        int aliveTanks = 0;
        Iterator<Enemy> enemys = this.enemyTanks.iterator();
        while (enemys.hasNext()) {
            Enemy temp = enemys.next();
            if (temp.isVisible() == false) {
                try {
                    Thread.sleep(35);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (temp.getWhenBornPaintTimes() > 3) {
                    g.drawImage(born1Image.getImage(), temp.getX(), temp.getY(), this);
                } else if (temp.getWhenBornPaintTimes() > 2) {
                    g.drawImage(born2Image.getImage(), temp.getX(), temp.getY(), this);
                } else if (temp.getWhenBornPaintTimes() > 1) {
                    g.drawImage(born3Image.getImage(), temp.getX(), temp.getY(), this);
                } else if (temp.getWhenBornPaintTimes() > 0) {
                    g.drawImage(born4Image.getImage(), temp.getX(), temp.getY(), this);
                    temp.setMoving(true);
                    temp.setVisible(true);
                } else {

                }
                temp.cut();
                aliveTanks += 1;
                continue;
            }
            if (temp.isAlive()) {
                aliveTanks += 1;
                if (temp.getType() == 2) {
                    if (temp.getLife() == 2) {
                        paintTank(g, temp.getType(), temp.getX(), temp.getY(), temp.getDirection());
                    } else if (temp.getLife() == 1) {
                        temp.setType(3);
                        paintTank(g, temp.getType(), temp.getX(), temp.getY(), temp.getDirection());
                    }
                } else {
                    paintTank(g, temp.getType(), temp.getX(), temp.getY(), temp.getDirection());
                }
            }

            // paint enemy bullet
            Iterator<Bullet> t = temp.getBulletPool().iterator();
            while (t.hasNext()) {
                try {
                    Bullet bullet = t.next();
                    if (bullet.isHitBuilding() == true) {
                        if (bullet.getBoomPlace() != null) {
                            this.bulletBoom.add(new Boom(bullet.getBoomPlace().getX(), bullet.getBoomPlace().getY()));
                        } else {
                            this.bulletBoom.add(new Boom(bullet.getX(), bullet.getY()));
                        }
                        bullet.setHitBuilding(false);
                    } else if (bullet.isAlive() == true) {
                        switch (bullet.getDirection()) {
                            case 0:
                                g.drawImage(bulletUImage.getImage(), bullet.getX(), bullet.getY(), this);
                                break;
                            case 1:
                                g.drawImage(bulletLImage.getImage(), bullet.getX(), bullet.getY(), this);
                                break;
                            case 2:
                                g.drawImage(bulletDImage.getImage(), bullet.getX(), bullet.getY(), this);
                                break;
                            case 3:
                                g.drawImage(bulletRImage.getImage(), bullet.getX(), bullet.getY(), this);
                                break;
                            default:
                                break;
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        // 补充死亡的坦克
        if ((aliveTanks < enemyTanksNumber) && (enemyTanksNumberLimit > 0)) {
            for (int from = 0; from < enemyTanksNumber - aliveTanks; from++) {
                if (enemyTanksNumberLimit <= 0) {
                    break;
                } else {
                    Enemy enemy = null;
                    switch ((int) (Math.random() * 3 + 1)) {
                        case 1:
                            enemy = new Enemy(10, 0);
                            break;
                        case 2:
                            enemy = new Enemy(270, 0);
                            break;
                        case 3:
                            enemy = new Enemy(480, 0);
                            break;
                    }
                    enemy.setType((int) (Math.random() * 3 + 1));
                    if (enemy.getType() == 3) {
                        if (enemyTanksAward <= 0) {
                            enemy.setType(1);
                        } else {
                            enemy.setType(4);
                            enemy.setAward((int) (Math.random() * 5 + 1));
                            enemy.setSpeed(16);
                            enemyTanksAward--;
                        }
                    }
                    if (enemy.getType() == 2) {
                        if (this.enemyTanksFatty <= 0) {
                            enemy.setType(1);
                        } else {
                            enemy.setLife(2);
                            this.enemyTanksFatty--;
                        }
                    }
                    enemy.setMoving(false);
                    enemy.setDirection(2);
                    this.enemyTanks.add(enemy);
                    new Thread(enemy).start();
                    this.enemyTanksNumberLimit -= 1;
                    aliveTanks += 1;
                }
            }
        }
        if (aliveTanks == 0) {
            enemyTanksNumberLimit = -1;
            bcgbgm.stop();
        }

        // draw buldings such as grass, heal
        // paint steel
        for (int from = 0; from < this.steels.size(); from++) {
            Steel t = (Steel) this.steels.get(from);
            g.drawImage(steelImage.getImage(), t.getX(), t.getY(), this);
        }

        // draw boom
        for (int from = 0; from < this.bulletBoom.size(); from++) {
            Boom boom = (Boom) (this.bulletBoom.get(from));
            if (boom.isAlive() == true) {
                if (boom.drawTimes > 6) {
                    g.drawImage(blast1Image.getImage(), boom.getLx(), boom.getLy(), this);
                    if (boom.isPlay() == true) {
                        blastbgm.play();
                    }
                } else if (boom.drawTimes > 5) {
                    g.drawImage(blast2Image.getImage(), boom.getLx(), boom.getLy(), this);
                } else if (boom.drawTimes > 4) {
                    g.drawImage(blast3Image.getImage(), boom.getLx(), boom.getLy(), this);
                } else if (boom.drawTimes > 3) {
                    g.drawImage(blast4Image.getImage(), boom.getLx(), boom.getLy(), this);
                } else if (boom.drawTimes > 2) {
                    g.drawImage(blast5Image.getImage(), boom.getLx(), boom.getLy(), this);
                } else if (boom.drawTimes > 1) {
                    g.drawImage(blast6Image.getImage(), boom.getLx(), boom.getLy(), this);
                } else if (boom.drawTimes > 0) {
                    g.drawImage(blast7Image.getImage(), boom.getLx(), boom.getLy(), this);
                }
                boom.cut();
            } else {
                this.bulletBoom.remove(boom);
            }
        }

        // grass
        for (int from = 0; from < this.grasses.size(); from++) {
            Grass t = (Grass) this.grasses.get(from);
            g.drawImage(grassImage.getImage(), t.getX(), t.getY(), this);
        }

        // draw home.gif
        if (homeAlive == true) {
            g.drawImage(homeImage.getImage(), 244, 488, this);
        } else {
            g.drawImage(destoryImage.getImage(), 244, 488, this);
            playerTank.setMoving(false);
            stop = true;
            if (once == true) {
                if (this.gameOver == null) {
                    this.gameOver = new GameOver(220, 520);
                    new Thread(this.gameOver).start();
                    once = false;
                }
            }
        }

        if (this.gameOver != null) {
            g.drawImage(gameOverImage.getImage(), this.gameOver.getX(), this.gameOver.getY(), 80, 45, this);
            bcgbgm.stop();
        }

    }

    // in tanks array initialize walls location
    public void initWallLocation() {
        for (int from = 0; from < this.walls.size(); from++) {
            Wall t = (Wall) this.walls.get(from);
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    Tank.tanks[t.getX() + i][t.getY() + j] = 4;
                }
            }
        }
    }

    // in tanks array initialize steel location
    public void initSteelLocation() {
        for (int from = 0; from < this.steels.size(); from++) {
            Steel t = (Steel) this.steels.get(from);
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    Tank.tanks[t.getX() + i][t.getY() + j] = 2;
                }
            }
        }
    }

    // whether enemy's tank hit the player's tank
    @SuppressWarnings("unchecked")
    public void ohMyGod() {
        for (int from = 0; from < enemyTanks.size(); from++) {
            Enemy temp = this.enemyTanks.get(from);
            CopyOnWriteArrayList<Bullet> bulletPool = temp.getBulletPool();
            for (int to = 0; to < bulletPool.size(); to++) {
                Bullet bullet = bulletPool.get(to);
                if (bullet.isAlive()) {
                    int before = this.playerTank.getLife();
                    scoreAHit(bullet, this.playerTank);
                    if (this.playerTank.getLife() > 0 && this.playerTank.getLife() < before) {
                        this.playerTank.setFastBullet(false);
                        this.playerTank.setDirection(0);
                        this.playerTank.setAlive(true);
                        this.playerTank.setMoving(false);
                        this.playerTank.setVisible(false);
                        this.playerTank.setWhenBornPaintTimes(4);
                        for (int i = 0; i < 32; i++) {
                            for (int j = 0; j < 32; j++) {
                                if (Tank.tanks[this.playerTank.getX() + i][this.playerTank.getY() + j] == 1)
                                    Tank.tanks[this.playerTank.getX() + i][this.playerTank.getY() + j] = 0;
                            }
                        }
                        Boom tankBoom = new Boom(this.playerTank.getX(), this.playerTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                        this.delay = true;
                        this.playerTank.setX(260 - 32 - 32 - 16, 1);
                        this.playerTank.setY(520 - 32, 1);
                        this.stop = true;
                        this.playerTank.setBeatable(false);
                        this.left = false;
                        this.right = false;
                        this.up = false;
                        this.down = false;
                        clock = System.currentTimeMillis();
                    }
                }
            }
        }
    }

    // become steel home
    @SuppressWarnings({"static-access", "unchecked"})
    public void steelHome() {
        ((Wall) this.walls.get(0)).setAlive(false);
        ((Wall) this.walls.get(1)).setAlive(false);
        ((Wall) this.walls.get(2)).setAlive(false);
        ((Wall) this.walls.get(3)).setAlive(false);
        ((Wall) this.walls.get(4)).setAlive(false);
        this.steels.add(new Steel(260 - 16 - 32, 520 - 32));
        this.steels.add(new Steel(260 - 16 - 32, 520 - 32 * 2));
        this.steels.add(new Steel(260 - 16, 520 - 32 * 2));
        this.steels.add(new Steel(260 + 16, 520 - 32));
        this.steels.add(new Steel(260 + 16, 520 - 32 * 2));
        initSteelLocation();
    }

    // steel home to wall home
    @SuppressWarnings({"static-access"})
    public void steelToWallHome() {
        ((Wall) this.walls.get(0)).setAlive(true);
        ((Wall) this.walls.get(1)).setAlive(true);
        ((Wall) this.walls.get(2)).setAlive(true);
        ((Wall) this.walls.get(3)).setAlive(true);
        ((Wall) this.walls.get(4)).setAlive(true);

        this.steels.remove(new Steel(260 - 16 - 32, 520 - 32));
        this.steels.remove(new Steel(260 - 16 - 32, 520 - 32 * 2));
        this.steels.remove(new Steel(260 - 16, 520 - 32 * 2));
        this.steels.remove(new Steel(260 + 16, 520 - 32));
        this.steels.remove(new Steel(260 + 16, 520 - 32 * 2));

    }

    //
    // enemy tanks can't move
    public void enemyTanksStopMove() {
        for (int from = 0; from < this.enemyTanks.size(); from++) {
            Enemy enemy = this.enemyTanks.get(from);
            if (enemy.isAlive()) {
                enemy.setMoving(false);
            }
        }
    }

    // enemy tanks in the screen all go die
    public void enemyTanksAllDie() {
        for (int from = 0; from < this.enemyTanks.size(); from++) {
            Enemy enemy = this.enemyTanks.get(from);
            if (enemy.isAlive()) {
                enemy.setAlive(false);
                Boom boom = new Boom(enemy.getX(), enemy.getY());
                this.bulletBoom.add(boom);
                switch (enemy.getType()) {
                    case 1:
                        score.setNormalTank(score.getNormalTank() + 1);
                        break;
                    case 2:
                        score.setFattyTank(score.getFattyTank() + 1);
                        break;
                    case 3:
                        score.setFattyTank(score.getFattyTank() + 1);
                        break;
                    case 4:
                        score.setFastTank(score.getFastTank() + 1);
                        break;
                    default:
                        break;
                }
            }
        }
        try {
            AudioClip bgm = Applet.newAudioClip(new File("bgm//boom.wav").toURL());
            bgm.play();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
    }

    // enemy tanks can't move
    public void enemyTanksMove() {
        for (int from = 0; from < this.enemyTanks.size(); from++) {
            Enemy enemy = this.enemyTanks.get(from);
            if (enemy.isAlive()) {
                enemy.setMoving(true);
            }
        }
    }

    // whether the bullet hit badTank 5.0
    @SuppressWarnings("unchecked")
    public void scoreAHit(Bullet bullet, Tank badTank) {
        if (badTank.getLife() <= 0) {
            // if tank is not survival then return
            return;
        }

        Boom tankBoom = null;
        switch (badTank.getDirection()) {
            case 0:
                if (bullet.getX() >= badTank.getX() && bullet.getX() <= badTank.getX() + 31
                        && bullet.getY() >= badTank.getY() && bullet.getY() <= badTank.getY() + 31) {
                    bullet.setAlive(false);
                    if (badTank.getFlag() == 0) {
                        if (this.playerTank.getBeatable() == false) {
                            return;
                        }
                    }
                    badTank.setLife(badTank.getLife() - 1);
                    if (badTank.getLife() <= 0) {
                        badTank.setAlive(false);
                        tankBoom = new Boom(badTank.getX(), badTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                    }
                } else if (bullet.getX() + 7 >= badTank.getX() && bullet.getX() + 7 <= badTank.getX() + 31
                        && bullet.getY() >= badTank.getY() && bullet.getY() <= badTank.getY() + 31) {
                    bullet.setAlive(false);
                    if (badTank.getFlag() == 0) {
                        if (this.playerTank.getBeatable() == false) {
                            return;
                        }
                    }
                    badTank.setLife(badTank.getLife() - 1);
                    if (badTank.getLife() <= 0) {
                        badTank.setAlive(false);
                        tankBoom = new Boom(badTank.getX(), badTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                    }
                }
                break;
            case 2:
                // Tank go die
                if (bullet.getX() >= badTank.getX() && bullet.getX() <= badTank.getX() + 31
                        && bullet.getY() + 7 >= badTank.getY() && bullet.getY() + 7 <= badTank.getY() + 31) {
                    bullet.setAlive(false);
                    if (badTank.getFlag() == 0) {
                        if (this.playerTank.getBeatable() == false) {
                            return;
                        }
                    }
                    badTank.setLife(badTank.getLife() - 1);
                    if (badTank.getLife() <= 0) {
                        // then play boom gif.
                        badTank.setAlive(false);
                        tankBoom = new Boom(badTank.getX(), badTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                    }
                } else if (bullet.getX() + 7 >= badTank.getX() && bullet.getX() + 7 <= badTank.getX() + 31
                        && bullet.getY() + 7 >= badTank.getY() && bullet.getY() + 7 <= badTank.getY() + 31) {
                    bullet.setAlive(false);
                    if (badTank.getFlag() == 0) {
                        if (this.playerTank.getBeatable() == false) {
                            return;
                        }
                    }
                    badTank.setLife(badTank.getLife() - 1);
                    if (badTank.getLife() <= 0) {
                        // then play boom gif.
                        badTank.setAlive(false);
                        tankBoom = new Boom(badTank.getX(), badTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                    }
                }
                break;
            case 1:
                if (bullet.getX() >= badTank.getX() && bullet.getX() <= badTank.getX() + 31
                        && bullet.getY() >= badTank.getY() && bullet.getY() <= badTank.getY() + 31) {
                    bullet.setAlive(false);
                    if (badTank.getFlag() == 0) {
                        if (this.playerTank.getBeatable() == false) {
                            return;
                        }
                    }
                    badTank.setLife(badTank.getLife() - 1);
                    if (badTank.getLife() <= 0) {
                        badTank.setAlive(false);
                        tankBoom = new Boom(badTank.getX(), badTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                    }
                } else if (bullet.getX() >= badTank.getX() && bullet.getX() <= badTank.getX() + 31
                        && bullet.getY() + 7 >= badTank.getY() && bullet.getY() + 7 <= badTank.getY() + 31) {
                    bullet.setAlive(false);
                    if (badTank.getFlag() == 0) {
                        if (this.playerTank.getBeatable() == false) {
                            return;
                        }
                    }
                    badTank.setLife(badTank.getLife() - 1);
                    if (badTank.getLife() <= 0) {
                        badTank.setAlive(false);
                        tankBoom = new Boom(badTank.getX(), badTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                    }
                }
                break;
            case 3:
                if (bullet.getX() + 7 >= badTank.getX() && bullet.getX() + 7 <= badTank.getX() + 31
                        && bullet.getY() >= badTank.getY() && bullet.getY() <= badTank.getY() + 31) {
                    bullet.setAlive(false);
                    if (badTank.getFlag() == 0) {
                        if (this.playerTank.getBeatable() == false) {
                            return;
                        }
                    }
                    badTank.setLife(badTank.getLife() - 1);
                    if (badTank.getLife() <= 0) {
                        badTank.setAlive(false);
                        tankBoom = new Boom(badTank.getX(), badTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                    }
                } else if (bullet.getX() + 7 >= badTank.getX() && bullet.getX() + 7 <= badTank.getX() + 31
                        && bullet.getY() + 7 >= badTank.getY() && bullet.getY() + 7 <= badTank.getY() + 31) {
                    bullet.setAlive(false);
                    if (badTank.getFlag() == 0) {
                        if (this.playerTank.getBeatable() == false) {
                            return;
                        }
                    }
                    badTank.setLife(badTank.getLife() - 1);
                    if (badTank.getLife() <= 0) {
                        badTank.setAlive(false);
                        tankBoom = new Boom(badTank.getX(), badTank.getY());
                        tankBoom.setPlay(true);
                        this.bulletBoom.add(tankBoom);
                    }
                }
                break;
            default:
                break;
        }
    }

    // paint the enemy tanks
    private void paintTank(Graphics g, int type, int x, int y, int direction) {

        switch (direction) {
            case 0:
                if (type == 0) {
                    if (this.playerTank.getFastBullet()) {
                        g.drawImage(up2Image.getImage(), x, y, this);
                    } else {
                        if (this.stop == false) {
                            g.drawImage(upImage.getImage(), x, y, this);
                            g.drawImage(upMoveImage.getImage(), x, y, this);
                        } else {
                            g.drawImage(upImage.getImage(), x, y, this);
                        }
                    }
                } else if (type == 1) {
                    if (this.enemyMove) {
                        g.drawImage(enemyUmoveImage.getImage(), x, y, this);
                    } else {
                        g.drawImage(enemyUImage.getImage(), x, y, this);
                    }
                } else if (type == 2) {
                    g.drawImage(enemy2UImage.getImage(), x, y, this);
                } else if (type == 3) {
                    if (this.enemyMove) {
                        g.drawImage(enemy3UMoveImage.getImage(), x, y, this);
                    } else {
                        g.drawImage(enemy3UImage.getImage(), x, y, this);
                    }
                } else {
                    g.drawImage(fastUImage.getImage(), x, y, this);
                }
                break;
            case 1:
                if (type == 0) {
                    if (this.playerTank.getFastBullet()) {
                        g.drawImage(left2Image.getImage(), x, y, this);
                    } else {
                        if (this.stop == false) {
                            g.drawImage(leftImage.getImage(), x, y, this);
                            g.drawImage(leftMoveImage.getImage(), x, y, this);
                        } else {
                            g.drawImage(leftImage.getImage(), x, y, this);
                        }
                    }
                } else if (type == 1) {
                    if (this.enemyMove) {
                        g.drawImage(enemyLmoveImage.getImage(), x, y, this);
                    } else {
                        g.drawImage(enemyLImage.getImage(), x, y, this);
                    }
                } else if (type == 2) {
                    g.drawImage(enemy2LImage.getImage(), x, y, this);
                } else if (type == 3) {
                    if (this.enemyMove) {
                        g.drawImage(enemy3LMoveImage.getImage(), x, y, this);
                    } else {
                        g.drawImage(enemy3LImage.getImage(), x, y, this);
                    }
                } else {
                    g.drawImage(fastLImage.getImage(), x, y, this);
                }
                break;

            case 2:
                if (type == 0) {
                    if (this.playerTank.getFastBullet()) {
                        g.drawImage(down2Image.getImage(), x, y, this);
                    } else {
                        if (this.stop == false) {
                            g.drawImage(downImage.getImage(), x, y, this);
                            g.drawImage(downMoveImage.getImage(), x, y, this);
                        } else {
                            g.drawImage(downImage.getImage(), x, y, this);
                        }
                    }
                } else if (type == 1) {
                    if (this.enemyMove) {
                        g.drawImage(enemyDmoveImage.getImage(), x, y, this);
                    } else {
                        g.drawImage(enemyDImage.getImage(), x, y, this);
                    }
                } else if (type == 2) {
                    g.drawImage(enemy2DImage.getImage(), x, y, this);
                } else if (type == 3) {
                    if (this.enemyMove) {
                        g.drawImage(enemy3DMoveImage.getImage(), x, y, this);
                    } else {
                        g.drawImage(enemy3DImage.getImage(), x, y, this);
                    }
                } else {
                    g.drawImage(fastDImage.getImage(), x, y, this);
                }
                break;
            case 3:
                if (type == 0) {
                    if (this.playerTank.getFastBullet()) {
                        g.drawImage(right2Image.getImage(), x, y, this);
                    } else {
                        if (this.stop == false) {
                            g.drawImage(rightImage.getImage(), x, y, this);
                            g.drawImage(rightMoveImage.getImage(), x, y, this);
                        } else {
                            g.drawImage(rightImage.getImage(), x, y, this);
                        }
                    }
                } else if (type == 1) {
                    if (this.enemyMove) {
                        g.drawImage(enemyRmoveImage.getImage(), x, y, this);
                    } else {
                        g.drawImage(enemyRImage.getImage(), x, y, this);
                    }
                } else if (type == 2) {
                    g.drawImage(enemy2RImage.getImage(), x, y, this);
                } else if (type == 3) {
                    if (this.enemyMove) {
                        g.drawImage(enemy3RMoveImage.getImage(), x, y, this);
                    } else {
                        g.drawImage(enemy3RImage.getImage(), x, y, this);
                    }
                } else {
                    g.drawImage(fastRImage.getImage(), x, y, this);
                }
                break;

            default:
                break;
        }
        repaint();
    }

    // check bullet meet
    public void bulletMeet() {

        Iterator<Bullet> bullets = playerTank.getBulletPool().iterator();
        while (bullets.hasNext()) {
            Bullet bullet = bullets.next();
            if (bullet.isAlive()) {
                for (int from = 0; from < enemyTanks.size(); from++) {
                    CopyOnWriteArrayList<Bullet> bullets2 = enemyTanks.get(from).getBulletPool();
                    for (int to = 0; to < bullets2.size(); to++) {
                        Bullet b = bullets2.get(to);
                        if (b.isAlive()) {
                            if (Math.abs(b.getX() - bullet.getX()) <= 6 && Math.abs(b.getY() - bullet.getY()) <= 6) {
                                bullet.setAlive(false);
                                b.setAlive(false);
                            }
                        }
                    }

                }
            }

        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // whether hit something
            Iterator<Bullet> bullets = playerTank.getBulletPool().iterator();
            while (bullets.hasNext()) {
                Bullet bullet = bullets.next();
                if (bullet.isAlive()) {
                    for (int from = 0; from < enemyTanks.size(); from++) {
                        Enemy badTank = enemyTanks.get(from);
                        if (badTank.isAlive()) {
                            scoreAHit(bullet, badTank);
                            if (badTank.isAlive() == false) {
                                if (badTank.getAward() > 0) {
                                    if (this.power == null) {
                                        switch (badTank.getAward()) {
                                            case 1:
                                                this.power = new Clock(252 + 32, 10);
                                                break;
                                            case 2:
                                                this.power = new Life(252 + 32, 50);
                                                break;
                                            case 3:
                                                this.power = new stage.body.power.Boom(252 + 32, 30);
                                                break;
                                            case 4:
                                                this.power = new Shield(252 + 32, 20);
                                                break;
                                            case 5:
                                                this.power = new Star(252 + 32, 10);
                                                break;
                                            case 6:
                                                this.power = new SteelHome(232 + 32, 10);
                                                break;
                                            default:
                                                break;
                                        }
                                        badTank.setAward(0);
                                    }

                                }

                                // 计分榜
                                switch (badTank.getType()) {
                                    case 1:
                                        score.setNormalTank(score.getNormalTank() + 1);
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        score.setFattyTank(score.getFattyTank() + 1);
                                        break;
                                    case 4:
                                        score.setFastTank(score.getFastTank() + 1);
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }

                    }
                }
            }
            bulletMeet();
            ohMyGod();
            repaint();
            if (this.gameOver != null || this.enemyTanksNumberLimit == -1) {
                break;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void getDirection() {

        if (this.playerTank.isMoving() == false) {
            return;
        }
        if (left && !right && !up && !down)
            this.playerTank.setDirection(1);
        else if (!left && right && !up && !down)
            this.playerTank.setDirection(3);
        else if (!left && !right && up && !down)
            this.playerTank.setDirection(0);
        else if (!left && !right && !up && down)
            this.playerTank.setDirection(2);
        else if (!left && !right && !up && !down)
            stop = true;
    }

    Boolean up = false;
    Boolean left = false;
    Boolean down = false;
    Boolean right = false;
    Boolean stop = true;

    @Override
    public void keyPressed(KeyEvent e) {
        // 对于其他的按键不处理

        if (this.playerTank.isMoving() == false) {
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            stop = false;
            this.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            stop = false;
            this.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            stop = false;
            this.down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            stop = false;
            this.right = true;
        }

        getDirection();

    }


    @Override
    public void keyReleased(KeyEvent e) {

        if (this.playerTank.isMoving() == false) {
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.down = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.playerTank.fire();
        }

        getDirection();
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public Player getPlayerTank() {
        return playerTank;
    }


    public void setEnemyTanksNumberLimit(int enemyTanksNumberLimit) {
        this.enemyTanksNumberLimit = enemyTanksNumberLimit;
    }

    public int getEnemyTanksNumberLimit() {
        return enemyTanksNumberLimit;
    }

    public Score getScore() {
        return score;
    }

    public void setGameOver(GameOver gameOver) {
        this.gameOver = gameOver;
    }

    public void setEnemyTanksAward(int enemyTanksAward) {
        this.enemyTanksAward = enemyTanksAward;
    }

    public void setEnemyTanksNumber(int enemyTanksNumber) {
        this.enemyTanksNumber = enemyTanksNumber;
    }

    public void setEnemyTanksFatty(int enemyTanksFatty) {
        this.enemyTanksFatty = enemyTanksFatty;
    }


}
