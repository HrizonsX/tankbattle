package stage.body.tank;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import stage.body.Bullet;

public class Player extends Tank {

    CopyOnWriteArrayList<Bullet> bulletPool;
    // limit time lag to fire
    int timeLag = 500;

    long extTime;

    public Player(int x, int y) {
        super(x, y);
        this.setSpeed(2);
        this.bulletPool = new CopyOnWriteArrayList<>();
        this.alive = true;
        this.moving = false;
        this.visible = false;
        this.life = 4;
        this.setFlag(0);
        try {
            bgm = Applet.newAudioClip(new File("bgm//fire.wav").toURL());
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
        this.extTime = System.currentTimeMillis();
    }

    long lastFire = 0;
    long lastestFire = 0;
    AudioClip bgm;

    public void fire() {

        if (this.moving == false) {
            return;
        }
        lastestFire = System.currentTimeMillis();
        if (lastestFire - lastFire < timeLag) {
            return;
        } else {
            lastFire = lastestFire;
        }

        Bullet bullet = null;

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
        bullet.setType(0);
        if (this.fastBullet) {
            bullet.setSpeed(22);
        }
        Thread fire = new Thread(bullet);
        fire.start();
        // play music
        bgm.play();
        this.bulletPool.add(bullet);

        Iterator<Bullet> iter = bulletPool.iterator();
        while (iter.hasNext()) {
            Bullet t = iter.next();
            if (t.isAlive() == false) {
                this.bulletPool.remove(t);
            }
        }
    }

    public void move(Boolean stop) {
        if (stop == true || this.isMoving() == false) {
            return;
        }
        switch (this.getDirection()) {
            case 0:
                this.directionUp();
                break;
            case 1:
                this.directionLeft();
                break;
            case 2:
                this.directionDown();
                break;
            case 3:
                this.directionRight();
                break;
            default:
                break;
        }
    }

    public void directionUp() {
        setY(getY() - getSpeed());
    }

    public void directionLeft() {
        setX(getX() - getSpeed());
    }

    public void directionDown() {
        setY(getY() + getSpeed());
    }

    public void directionRight() {
        setX(getX() + getSpeed());
    }

    public CopyOnWriteArrayList<Bullet> getBulletPool() {
        return bulletPool;
    }

    public long getExtTime() {
        return extTime;
    }

    public void setExtTime(long extTime) {
        this.extTime = extTime;
    }
}
