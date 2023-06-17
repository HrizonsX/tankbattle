package stage.body;

import stage.body.buildings.BuildingsObject;
import stage.body.buildings.Steel;
import stage.body.buildings.Wall;
import stage.body.tank.Tank;

public class Bullet implements Runnable {

    private int x;
    private int y;
    // 0: from player
    // 1: from enemy
    private int type;
    private int direction;
    private int speed = 12;
    private Boolean Alive;
    private Boolean hitBuilding;
    private Boolean hitSteel;
    private BuildingsObject hitObject;
    private BuildingsObject boomPlace;

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.Alive = true;
        this.type = -1;
        this.hitBuilding = false;
        this.hitSteel = false;
        this.direction = direction;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (direction) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x -= speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x += speed;
                    break;
                default:
                    break;
            }
            if (this.Alive == false) {
                return;
            }
            if (x < 0 || y < 0 || x > 520 - 8 || y > 520 - 8) {
                //if (this.getType() == 0) {
                //try {
                //    AudioClip bgm = Applet.newAudioClip(new File("bgm//hitLimit.wav").toURL());
                //    bgm.play();
                //} catch (MalformedURLException e2) {
                //    e2.printStackTrace();
                //}
                //}
                this.Alive = false;
                return;
            }
            switch (this.getDirection()) {
                case 0:
                    if ((Tank.tanks[x][y] == 2 || Tank.tanks[x + 7][y] == 2) ||
                            (Tank.tanks[x][y] == 3 || Tank.tanks[x + 7][y] == 3) ||
                            (Tank.tanks[x][y] == 4 || Tank.tanks[x + 7][y] == 4)) {
                        this.Alive = false;
                        if (Tank.tanks[x][y] == 2 || Tank.tanks[x + 7][y] == 2) {
                            this.hitBuilding = true;
                            this.hitSteel = true;
                            for (int from = 0; from < StageBody.steels.size(); from++) {
                                Steel wall = (Steel) StageBody.steels.get(from);
                                if (wall.inside(x, y) || wall.inside(x + 7, y)) {
                                    setHitTargetXY(wall.getX(), wall.getY());
                                    break;
                                }
                            }
                            setBoomPlaceXY(x - 9, getHitObject().getY() + 32 - 10);
                        } else if (Tank.tanks[x][y] == 3 || Tank.tanks[x + 7][y] == 3) {
                            StageBody.homeAlive = false;
                        } else if (Tank.tanks[x][y] == 4 || Tank.tanks[x + 7][y] == 4) {
                            for (int from = 0; from < StageBody.walls.size(); from++) {
                                Wall wall = (Wall) StageBody.walls.get(from);
                                if (wall.getAlive()) {
                                    if (wall.inside(x, y) || wall.inside(x + 7, y)) {
                                        wall.setAlive(false);
                                        this.setX(wall.getX());
                                        this.setY(wall.getY());
                                        break;
                                    }
                                }
                            }
                            this.hitBuilding = true;
                        }
                    }
                    break;

                case 1:
                    if ((Tank.tanks[x][y] == 2 || Tank.tanks[x][y + 7] == 2) ||
                            (Tank.tanks[x][y] == 3 || Tank.tanks[x][y + 7] == 3) ||
                            (Tank.tanks[x][y] == 4 || Tank.tanks[x][y + 7] == 4)) {
                        this.Alive = false;
                        if (Tank.tanks[x][y] == 2 || Tank.tanks[x][y + 7] == 2) {
                            this.hitBuilding = true;
                            this.hitSteel = true;
                            for (int from = 0; from < StageBody.steels.size(); from++) {
                                Steel wall = (Steel) StageBody.steels.get(from);
                                if (wall.inside(x, y) || wall.inside(x, y + 7)) {
                                    setHitTargetXY(wall.getX(), wall.getY());
                                    break;
                                }
                            }
                            setBoomPlaceXY(getHitObject().getX() + 32 - 10, y - 7);
                        } else if (Tank.tanks[x][y] == 3 || Tank.tanks[x][y + 7] == 3) {
                            StageBody.homeAlive = false;
                        } else if (Tank.tanks[x][y] == 4 || Tank.tanks[x][y + 7] == 4) {
                            for (int from = 0; from < StageBody.walls.size(); from++) {
                                Wall wall = (Wall) StageBody.walls.get(from);
                                if (wall.getAlive()) {
                                    if (wall.inside(x, y) || wall.inside(x, y + 7)) {
                                        wall.setAlive(false);
                                        this.setX(wall.getX());
                                        this.setY(wall.getY());
                                        break;
                                    }
                                }
                            }
                            this.hitBuilding = true;
                        }
                    }
                    break;

                case 2:
                    if ((Tank.tanks[x][y + 7] == 2 || Tank.tanks[x + 7][y + 7] == 2) ||
                            (Tank.tanks[x][y + 7] == 3 || Tank.tanks[x + 7][y + 7] == 3) ||
                            (Tank.tanks[x][y + 7] == 4 || Tank.tanks[x + 7][y + 7] == 4)) {
                        this.Alive = false;
                        if (Tank.tanks[x][y + 7] == 2 || Tank.tanks[x + 7][y + 7] == 2) {
                            this.hitBuilding = true;
                            this.hitSteel = true;
                            for (int from = 0; from < StageBody.steels.size(); from++) {
                                Steel wall = (Steel) StageBody.steels.get(from);
                                if (wall.inside(x, y + 7) || wall.inside(x + 7, y + 7)) {
                                    setHitTargetXY(wall.getX(), wall.getY());
                                    break;
                                }
                            }
                            setBoomPlaceXY(x - 11, getHitObject().getY() - 10);
                        } else if (Tank.tanks[x][y + 7] == 3 || Tank.tanks[x + 7][y + 7] == 3) {
                            StageBody.homeAlive = false;
                        } else if (Tank.tanks[x][y + 7] == 4 || Tank.tanks[x + 7][y + 7] == 4) {
                            for (int from = 0; from < StageBody.walls.size(); from++) {
                                Wall wall = (Wall) StageBody.walls.get(from);
                                if (wall.getAlive()) {
                                    if (wall.inside(x, y + 7) || wall.inside(x + 7, y + 7)) {
                                        wall.setAlive(false);
                                        this.setX(wall.getX());
                                        this.setY(wall.getY());
                                        break;
                                    }
                                }
                            }
                            this.hitBuilding = true;
                        }
                    }
                    break;

                case 3:
                    if ((Tank.tanks[x + 7][y] == 2 || Tank.tanks[x + 7][y + 7] == 2) ||
                            (Tank.tanks[x + 7][y] == 3 || Tank.tanks[x + 7][y + 7] == 3) ||
                            (Tank.tanks[x + 7][y] == 4 || Tank.tanks[x + 7][y + 7] == 4)) {
                        this.Alive = false;
                        if (Tank.tanks[x + 7][y] == 2 || Tank.tanks[x + 7][y + 7] == 2) {
                            this.hitBuilding = true;
                            this.hitSteel = true;
                            for (int from = 0; from < StageBody.steels.size(); from++) {
                                Steel wall = (Steel) StageBody.steels.get(from);
                                if (wall.inside(x + 7, y) || wall.inside(x + 7, y + 7)) {
                                    setHitTargetXY(wall.getX(), wall.getY());
                                    break;
                                }
                            }
                            setBoomPlaceXY(getHitObject().getX() - 10, y - 6);
                        } else if (Tank.tanks[x + 7][y] == 3 || Tank.tanks[x + 7][y + 7] == 3) {
                            StageBody.homeAlive = false;
                        } else if (Tank.tanks[x + 7][y] == 4 || Tank.tanks[x + 7][y + 7] == 4) {
                            for (int from = 0; from < StageBody.walls.size(); from++) {
                                Wall wall = (Wall) StageBody.walls.get(from);
                                if (wall.getAlive()) {
                                    if (wall.inside(x + 7, y) || wall.inside(x + 7, y + 7)) {
                                        wall.setAlive(false);
                                        this.setX(wall.getX());
                                        this.setY(wall.getY());
                                        break;
                                    }
                                }
                            }
                            this.hitBuilding = true;
                        }
                    }
                    break;
                default:
                    break;
            }

        }

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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    public Boolean getHitSteel() {
        return hitSteel;
    }

    public void setHitSteel(Boolean hitSteel) {
        this.hitSteel = hitSteel;
    }

    public Boolean isAlive() {
        return Alive;
    }

    public void setAlive(Boolean alive) {
        Alive = alive;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Boolean isHitBuilding() {
        return hitBuilding;
    }

    public void setHitBuilding(Boolean hitBuilding) {
        this.hitBuilding = hitBuilding;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BuildingsObject getHitObject() {
        return hitObject;
    }

    public void setHitObject(BuildingsObject hitObject) {
        this.hitObject = hitObject;
    }


    public void setHitTargetXY(int x, int y) {
        if (this.hitObject == null) {
            this.hitObject = new BuildingsObject();
        }
        this.hitObject.setX(x);
        this.hitObject.setY(y);
    }

    public BuildingsObject getBoomPlace() {
        return boomPlace;
    }

    public void setBoomPlace(BuildingsObject boomPlace) {
        this.boomPlace = boomPlace;
    }

    public void setBoomPlaceXY(int x, int y) {
        if (this.boomPlace == null) {
            this.boomPlace = new BuildingsObject();
        }
        this.boomPlace.setX(x);
        this.boomPlace.setY(y);
    }
}
