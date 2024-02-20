import java.util.ArrayList;
public class Landscape_d extends Dice
{
    public Landscape_d(int variant) {
        super("landscape", 6);
        ArrayList<Face> faces = super.getFaces();
        faces.set(0, Face.SWAMP);
        faces.set(1, Face.RIVER);
        faces.set(2, Face.MOUNTAIN);
        faces.set(3, Face.DOOMLAND);
        faces.set(4, Face.DOOMLAND);
        if (variant == 1)
            faces.set(5, Face.FOREST);
        else
            faces.set(5, Face.MIXED_TERRAIN);
    }

    public int  get_value() {
        return switch (super.getCurrentFace()) {
            case SWAMP, FOREST, MIXED_TERRAIN -> 2;
            case RIVER -> 1;
            case MOUNTAIN -> 3;
            default -> 0;
        };
    }
}
