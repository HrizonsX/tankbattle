package stage.body.tank;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import stage.body.StageBody;

// don't let tanks overlap 4.0..
public class Tank {

    private int x;
    private int y;
    private int speed;
    private int direction;
    boolean alive;
    boolean visible;
    // whether they can move
    boolean moving;
    int life;
    // 用来标记标记区分开来敌我坦克 0 stand for player
    int flag;
    Boolean firstDraw;
    // whether beatable or not
    Boolean beatable;
    // faster bullet
    Boolean fastBullet;
    int whenBornPaintTimes = 4;

    public static int[][] tanks;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.firstDraw = true;
        this.flag = -1;
        // 出生后才能被摧毁
        this.beatable = false;
        this.fastBullet = false;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                tanks[x + i][y + j] = 1;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0 || x > 520 - 32) {
            return;
        }
        int diff = x - this.x;
        if (diff > 0) {
            // Right
            if (tanks[x + 31][this.y] < 6 && tanks[x + 31][this.y + 31] < 6) {
                if (tanks[x + 31][this.y] >= 1) {
                    return;
                } else if (tanks[x + 31][this.y + 31] >= 1) {
                    return;
                }
            } else {
                if (this.getFlag() == 0) {
                    StageBody.power.setAlive(false);
                    if (tanks[x + 31][this.y] == 7 || tanks[x + 31][this.y + 31] == 7) {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//addLife.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    } else if (tanks[x + 31][this.y] == 8 || tanks[x + 31][this.y + 31] == 8) {

                    } else {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//eat.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    }

                }
            }
        } else {
            // Left
            if (tanks[x][this.y] < 6 && tanks[x][this.y + 31] < 6) {
                if (tanks[x][this.y] >= 1) {
                    return;
                } else if (tanks[x][this.y + 31] >= 1) {
                    return;
                }
            } else {
                if (this.getFlag() == 0) {
                    StageBody.power.setAlive(false);
                    if (tanks[x][this.y] == 7 || tanks[x][this.y + 31] == 7) {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//addLife.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    } else if (tanks[x][this.y] == 8 || tanks[x][this.y + 31] == 8) {

                    } else {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//eat.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        // take in
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                if (tanks[x + i][this.y + j] == 0) {
                    tanks[x + i][this.y + j] = 1;
                }
            }
        }
        // release before move

        if (diff > 0) {
            //Right
            for (int i = 0; i < diff; i++) {
                for (int j = 0; j < 32; j++) {
                    if (tanks[this.x + i][this.y + j] == 1)
                        tanks[this.x + i][this.y + j] = 0;
                }
            }
        } else {
            //Left
            int m = this.x + 32 + diff;
            for (int i = 0; i < -diff; i++) {
                for (int j = 0; j < 32; j++) {
                    if (tanks[m + i][this.y + j] == 1)
                        tanks[m + i][this.y + j] = 0;
                }
            }
        }
        // in the last
        this.x = x;
//		}
    }

    public int getY() {
        return y;
    }

    // the same to x
    public void setY(int y) {
        if (y < 0 || y > 520 - 32) {
            return;
        }
        int diff = y - this.y;
        if (diff > 0) {
            //Down
            if (tanks[this.x][y + 31] < 6 && tanks[this.x + 31][y + 31] < 6) {
                if (tanks[this.x][y + 31] >= 1) {
                    return;
                } else if (tanks[this.x + 31][y + 31] >= 1) {
                    return;
                }
            } else {
                if (this.getFlag() == 0) {
                    StageBody.power.setAlive(false);
                    if (tanks[this.x][y + 31] == 7 || tanks[this.x + 31][y + 31] == 7) {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//addLife.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    } else if (tanks[this.x][y + 31] == 8 || tanks[this.x + 31][y + 31] == 8) {

                    } else {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//eat.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }

        } else {
            //Up
            if (tanks[this.x][y] < 6 && tanks[this.x + 31][y] < 6) {
                if (tanks[this.x][y] >= 1) {
                    return;
                } else if (tanks[this.x + 31][y] >= 1) {
                    return;
                }
            } else {
                if (this.getFlag() == 0) {
                    StageBody.power.setAlive(false);
                    if (tanks[this.x][y] == 7 || tanks[this.x + 31][y] == 7) {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//addLife.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    } else if (tanks[this.x][y] == 8 || tanks[this.x + 31][y] == 8) {

                    } else {
                        try {
                            AudioClip bgm = Applet.newAudioClip(new File("bgm//eat.wav").toURL());
                            bgm.play();
                        } catch (MalformedURLException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        // take in
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                if (tanks[this.x + i][y + j] == 0)
                    tanks[this.x + i][y + j] = 1;
            }
        }

        //release
        if (diff > 0) {
            //Down
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < diff; j++) {
                    if (tanks[this.x + i][this.y + j] == 1)
                        tanks[this.x + i][this.y + j] = 0;
                }
            }
        } else {
            //Up
            int n = 32 + diff + this.y;
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < -diff; j++) {
                    if (tanks[this.x + i][n + j] == 1)
                        tanks[this.x + i][n + j] = 0;
                }
            }
        }
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getLife() {
        return life;
    }

    public void setX(int x, int pass) {
        this.x = x;
    }

    public void setY(int y, int pass) {
        this.y = y;
    }

    public Boolean getFastBullet() {
        return fastBullet;
    }

    public void setFastBullet(Boolean fastBullet) {
        this.fastBullet = fastBullet;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public Boolean getBeatable() {
        return beatable;
    }

    public void setBeatable(Boolean beatable) {
        this.beatable = beatable;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Boolean getFirstDraw() {
        return firstDraw;
    }

    public void setFirstDraw(Boolean firstDraw) {
        this.firstDraw = firstDraw;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void cut() {
        if (this.whenBornPaintTimes > 0) {
            this.whenBornPaintTimes -= 1;
            return;
        }
    }

    public int getWhenBornPaintTimes() {
        return whenBornPaintTimes;
    }

    public void setWhenBornPaintTimes(int whenBornPaintTimes) {
        this.whenBornPaintTimes = whenBornPaintTimes;
    }

}
