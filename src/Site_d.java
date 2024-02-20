import java.util.ArrayList;

public class Site_d extends Dice
{
    public Site_d() {
        super("site", 6);
        ArrayList<Face> faces = super.getFaces();
        faces.set(0, Face.CASTLE);
        faces.set(1, Face.VILLAGE);
        faces.set(2, Face.TOWER);
        faces.set(3, Face.FORT);
        faces.set(4, Face.HUT);
        faces.set(5, Face.HILL_FORT);
    }

    public int  get_value() {
        return switch (super.getCurrentFace()) {
            case CASTLE -> 3;
            case VILLAGE, HUT -> 1;
            case TOWER, FORT, HILL_FORT -> 2;
            default -> 0;
        };
    }
}
