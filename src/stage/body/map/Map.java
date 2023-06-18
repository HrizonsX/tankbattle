package stage.body.map;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import stage.body.buildings.Grass;
import stage.body.buildings.Ice;
import stage.body.buildings.Sea;
import stage.body.buildings.Steel;
import stage.body.buildings.Wall;
import stage.body.tank.Tank;

public class Map {

    CopyOnWriteArrayList<Wall> walls = new CopyOnWriteArrayList<>();
    ArrayList<Steel> steels = new ArrayList<>();
    ArrayList<Sea> seas = new ArrayList<>();
    ArrayList<Grass> grasses = new ArrayList<>();
    ArrayList<Ice> ices = new ArrayList<>();

    public Map() {

    }

    // in tanks array init walls location
    protected void initWallLocation() {
        for (int from = 0; from < this.walls.size(); from++) {
            Wall t = (Wall) this.walls.get(from);
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    Tank.tanks[t.getX() + i][t.getY() + j] = 4;
                }
            }
        }
    }

    protected void initHomeLocation() {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                Tank.tanks[244 + i][496 + j] = 3;
            }
        }
    }


    // in tanks array init seas location
    protected void initSeaLocation() {

        for (int from = 0; from < this.seas.size(); from++) {
            Sea t = (Sea) this.seas.get(from);
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    Tank.tanks[t.getX() + i][t.getY() + j] = 5;
                }
            }
        }
    }

    // in tanks array init steel location
    protected void initSteelLocation() {
        for (int from = 0; from < this.steels.size(); from++) {
            Steel t = (Steel) this.steels.get(from);
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    Tank.tanks[t.getX() + i][t.getY() + j] = 2;
                }
            }
        }
    }

    public CopyOnWriteArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Steel> getSteels() {
        return steels;
    }

    public ArrayList<Sea> getSeas() {
        return seas;
    }

    public ArrayList<Grass> getGrasses() {
        return grasses;
    }

    public ArrayList<Ice> getIces() {
        return ices;
    }


}
