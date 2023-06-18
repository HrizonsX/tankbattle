package stage.body.power;

import stage.body.tank.Tank;

public class Power {
    private int x;
    private int y;
    private int type;
    Boolean alive;

    public Power(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.type = 0;
        this.alive = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
        if (this.getAlive() == false) {
            // release
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    if (Tank.tanks[this.getX() + i][this.getY() + j] >= 6) {
                        Tank.tanks[this.getX() + i][this.getY() + j] = 0;
                    }
                }
            }
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
