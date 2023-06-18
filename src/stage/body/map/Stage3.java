package stage.body.map;

import stage.body.buildings.Grass;
import stage.body.buildings.Sea;
import stage.body.buildings.Steel;
import stage.body.buildings.Wall;
import stage.body.tank.Tank;

public class Stage3 extends Map {

    public Stage3() {

        Tank.tanks = new int[560][560];

        walls.add(new Wall(260 - 16 - 32, 520 - 32));
        walls.add(new Wall(260 - 16 - 32, 520 - 32 * 2));
        walls.add(new Wall(260 - 16, 520 - 32 * 2));
        walls.add(new Wall(260 + 16, 520 - 32));
        walls.add(new Wall(260 + 16, 520 - 32 * 2));
        initHomeLocation();

        grasses.add(new Grass(0 + 32, 80 - 32));
        grasses.add(new Grass(0 + 32, 80));
        grasses.add(new Grass(0 + 32 * 2, 80 - 32));
        grasses.add(new Grass(0 + 32 * 2, 80 - 32 * 2));
        grasses.add(new Grass(0 + 32 * 3, 80 - 32 * 2));
        grasses.add(new Grass(0 + 32 * 13, 80 - 32 * 2));
        grasses.add(new Grass(0 + 32 * 14, 80 - 32));
        grasses.add(new Grass(0 + 32, 80 + 32 * 9));
        grasses.add(new Grass(0 + 32, 80 + 32 * 10));
        grasses.add(new Grass(0 + 32 * 12, 80 + 32 * 10));
        grasses.add(new Grass(0 + 32 * 13, 80 + 32 * 10));
        grasses.add(new Grass(0 + 32 * 13, 80 + 32 * 9));
        grasses.add(new Grass(0 + 32 * 14, 80 + 32 * 9));
        grasses.add(new Grass(0 + 32 * 14, 80 + 32 * 8));

        steels.add(new Steel(0 + 32, 80 + 32 * 10));
        steels.add(new Steel(0 + 32 * 14, 80 + 32 * 10));
        steels.add(new Steel(0 + 32, 80 + 16));
        steels.add(new Steel(0 + 32 * 14, 80 + 16));
        steels.add(new Steel(0 + 32 * 5 + 16, 80 + 32 * 3));
        steels.add(new Steel(0 + 32 * 7 + 16, 80 + 32 * 3));
        initSteelLocation();

        seas.add(new Sea(0, 80 + 32 * 4));
        seas.add(new Sea(0 + 32, 80 + 32 * 4));
        seas.add(new Sea(0 + 32 * 13, 80 + 32 * 4));
        seas.add(new Sea(0 + 32 * 14, 80 + 32 * 4));
        initSeaLocation();

        walls.add(new Wall(0 + 32 * 2, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 3, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 4, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 5, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 6, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 7, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 8, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 9, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 10, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 11, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 12, 80 + 32 * 8));
        walls.add(new Wall(0 + 32 * 4, 80 + 32));
        walls.add(new Wall(0 + 32 * 5, 80 + 32));
        walls.add(new Wall(0 + 32 * 6, 80 + 32));
        walls.add(new Wall(0 + 32 * 7, 80 + 32));
        walls.add(new Wall(0 + 32 * 8, 80 + 32));
        walls.add(new Wall(0 + 32 * 9, 80 + 32));
        walls.add(new Wall(0 + 32 * 10, 80 + 32));
        walls.add(new Wall(0 + 32 * 11, 80 + 32));
        walls.add(new Wall(0 + 32 * 12, 80 + 32));
        walls.add(new Wall(0 + 32 * 13, 80 + 32));
        walls.add(new Wall(0 + 32 * 3, 80 + 32 * 3));
        walls.add(new Wall(0 + 32 * 3, 80 + 32 * 2));
        walls.add(new Wall(0 + 32 * 4, 80));
        walls.add(new Wall(0 + 32 * 5, 80 - 32));
        walls.add(new Wall(0 + 32 * 6, 80 - 32));
        walls.add(new Wall(0 + 32 * 7, 80));
        walls.add(new Wall(0 + 32 * 8, 80));
        walls.add(new Wall(0 + 32 * 9, 80));
        walls.add(new Wall(0 + 32 * 10, 80));
        walls.add(new Wall(0 + 32 * 3, 80 + 32 * 4));
        walls.add(new Wall(0 + 32 * 9, 80 + 32 * 4));
        walls.add(new Wall(0 + 32 * 10, 80 + 32 * 4));
        walls.add(new Wall(0 + 32 * 9, 80 + 32 * 3));
        walls.add(new Wall(0 + 32 * 10, 80 + 32 * 3));
        walls.add(new Wall(0 + 32 * 9, 80 + 32 * 2));
        walls.add(new Wall(0 + 32 * 10, 80 + 32 * 2));
        walls.add(new Wall(0 + 32 * 11, 80 + 32 * 2));
        walls.add(new Wall(0 + 32 * 2, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 3, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 4, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 5, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 6, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 7, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 8, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 9, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 10, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 11, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 12, 80 + 32 * 7));
        walls.add(new Wall(0 + 32 * 3, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 4, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 5, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 6, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 7, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 8, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 9, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 10, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 11, 80 + 32 * 6));
        walls.add(new Wall(0 + 32 * 3, 80 + 32 * 5));
        walls.add(new Wall(0 + 32 * 4, 80 + 32 * 5));
        walls.add(new Wall(0 + 32 * 5, 80 + 32 * 5));
        walls.add(new Wall(0 + 32 * 6, 80 + 32 * 5));
        walls.add(new Wall(0 + 32 * 7, 80 + 32 * 5));
        walls.add(new Wall(0 + 32 * 8, 80 + 32 * 5));
        walls.add(new Wall(0 + 32 * 9, 80 + 32 * 5));
        walls.add(new Wall(0 + 32 * 10, 80 + 32 * 5));
        walls.add(new Wall(0 + 32 * 11, 80 + 32 * 5));
        initWallLocation();


    }
}
