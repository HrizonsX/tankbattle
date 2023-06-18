package stage.body;

public class Boom {
    // 只是记录draw boom的左上角的x、y
    private int lx;
    private int ly;
    boolean alive;
    boolean play;
    int drawTimes;  // draw 7 times 2.0

    public Boom(int x, int y) {
        super();
        this.lx = x;
        this.ly = y;
        this.play = false;
        this.drawTimes = 7;
        this.alive = true;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }

    public int getLy() {
        return ly;
    }

    public void setLy(int ly) {
        this.ly = ly;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public void cut() {
        if (drawTimes > 0) {
            drawTimes -= 1;
            return;
        }
        alive = false;
    }


}
