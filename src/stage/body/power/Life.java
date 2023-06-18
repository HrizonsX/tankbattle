package stage.body.power;

import stage.body.tank.Tank;

public class Life extends Power {

    public Life(int x, int y) {
        super(x, y);
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                Tank.tanks[x + i][y + j] = 7;
            }
        }
        this.setType(2);
    }

}
