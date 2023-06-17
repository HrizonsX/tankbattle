package stage.body.power;

import stage.body.tank.Tank;

public class Clock extends Power {

	// 5s: enemys can't move
	int time = 5000;
	public Clock(int x, int y) {
		super(x, y);
		for(int i = 0; i < 32; i++) {
			for(int j = 0; j < 32; j++) {
				Tank.tanks[x + i][y + j] = 6;
			}
		}
		this.setType(1);
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	
}
