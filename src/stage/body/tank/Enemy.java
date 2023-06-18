package stage.body.tank;

import java.util.concurrent.CopyOnWriteArrayList;

import stage.Stage;
import stage.body.Bullet;

// 3.0
public class Enemy extends Tank implements Runnable {

    int type;
    // type 1: 普通的坦克
    // 2: 2和3是同一种类型的坦克（肉盾坦克）
    // 4: 飞速坦克
    CopyOnWriteArrayList<Bullet> bulletPool;
    // 一些坦克杀死后有奖励
    // 0: 没奖励
    // 1： 获得时间暂停
    // 2: 加一条命
    // 3: 所有坦克上西天
    // 4: 获得保护盾
    // 5: 吃三次星星（每颗有额外加分）子弹加速、子弹穿钢铁
    // 6: 基地获得建筑变成
    // TODO: 实现道具
    int award;


    public Enemy(int x, int y) {
        super(x, y);
        this.life = 1;
        this.award = 0;
        this.bulletPool = new CopyOnWriteArrayList<>();
        this.alive = true;
        this.setDirection(2);
        this.setSpeed(8);
        this.moving = true;
        this.type = 1;
        this.setFlag(1);
        // to show born gif. new a enemy needs unvisual
        this.visible = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // AI tank 5.0..
    @Override
    public void run() {

        int fire = 0;
        while (true) {
            Bullet bullet = null;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //touch the wall stop
            switch (this.getDirection()) {
                // UP
                case 0:
                    for (int from = 0; from < 4; from++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.isMoving() == false) {
                            break;
                        }
                        if (this.getY() > 0) {
                            this.setY(this.getY() - this.getSpeed());
                        } else {
                            break;
                        }
                    }
                    break;
                // LEFT
                case 1:
                    for (int from = 0; from < 4; from++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.isMoving() == false) {
                            break;
                        }
                        if (this.getX() > 0) {
                            this.setX(this.getX() - this.getSpeed());
                        } else {
                            break;
                        }
                    }
                    break;
                // DOWN
                case 2:
                    for (int from = 0; from < 4; from++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.isMoving() == false) {
                            break;
                        }
                        if (this.getY() < 520 - 32) {
                            this.setY(this.getY() + this.getSpeed());
                        } else {
                            break;
                        }
                    }
                    break;
                // RIGHT
                case 3:
                    for (int from = 0; from < 4; from++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.isMoving() == false) {
                            break;
                        }
                        if (this.getX() < 520 - 32) {
                            this.setX(this.getX() + this.getSpeed());
                        } else {
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
            fire = (int) (Math.random() * 2);
            if (this.isAlive() && this.isMoving() && (fire == 1)) {
                switch (this.getDirection()) {
                    case 0:
                        bullet = new Bullet(getX() + 12, getY() - 8, 0);
                        break;
                    case 1:
                        bullet = new Bullet(getX() - 8, getY() + 12, 1);
                        break;
                    case 2:
                        bullet = new Bullet(getX() + 12, getY() + 32, 2);
                        break;
                    case 3:
                        bullet = new Bullet(getX() + 32, getY() + 12, 3);
                        break;
                    default:
                        break;
                }
                bullet.setType(1);
                this.bulletPool.add(bullet);
                Thread thread = new Thread(bullet);
                thread.start();

            }

            if (this.isMoving()) {
                int dir = (int) (Math.random() * 5);
                if (dir > 3) {
                    this.setDirection(2);
                } else {
                    this.setDirection(dir);
                }
            }

            if (this.isAlive() == false) {
                // kill the thread directly and then release
                for (int i = 0; i < 32; i++) {
                    for (int j = 0; j < 32; j++) {
                        if (tanks[this.getX() + i][this.getY() + j] == 1) {
                            tanks[this.getX() + i][this.getY() + j] = 0;
                        }
                    }
                }
                break;
            }
            if (Stage.gameOver == true) {
                break;
            }
        }

    }


    public int getType() {
        return type;
    }

    public CopyOnWriteArrayList<Bullet> getBulletPool() {
        return bulletPool;
    }

    public void setBulletPool(CopyOnWriteArrayList<Bullet> bulletPool) {
        this.bulletPool = bulletPool;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getWhenBornPaintTimes() {
        return whenBornPaintTimes;
    }

    public void setWhenBornPaintTimes(int whenBornPaintTimes) {
        this.whenBornPaintTimes = whenBornPaintTimes;
    }


}
