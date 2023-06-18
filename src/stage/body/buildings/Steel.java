package stage.body.buildings;

public class Steel extends BuildingsObject {

    public Steel(int x, int y) {
        super(x, y);
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
