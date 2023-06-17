package stage.body.buildings;

import stage.body.tank.Tank;

public class Wall extends BuildingsObject {

    Boolean alive;

    public Wall(int x, int y) {
        super(x, y);
        this.alive = true;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
        if (alive == false) {
            //release
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    if (Tank.tanks[this.getX() + i][this.getY() + j] == 4) {
                        Tank.tanks[this.getX() + i][this.getY() + j] = 0;
                    }
                }
            }
        } else {
            // take in
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    Tank.tanks[this.getX() + i][this.getY() + j] = 4;
                }
            }
        }
    }

    public Boolean inside(int x, int y) {
        if (getX() <= x && getX() + 32 > x
                && getY() <= y && getY() + 32 > y) {
            return true;
        } else {
            return false;
        }
    }


}
