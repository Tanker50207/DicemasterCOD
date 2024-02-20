import java.util.ArrayList;
public class Weapon_d extends Dice
{
    @Override
    public int get_value() {
        return 0;
    }

    public enum Slot {
        PRIMARY,
        SECONDARY
    }
    private Slot slot;
    public Weapon_d(Slot s_slot) {
        super("Weapon", 6);
        ArrayList<Face> faces = super.getFaces();
        faces.set(0, Face.SWORD);
        faces.set(1, Face.SWORD_ENHANCED);
        faces.set(2, Face.BOW);
        faces.set(3, Face.BOW_ENHANCED);
        faces.set(4, Face.AXE);
        faces.set(5, Face.AXE_ENHANCED);
        slot = s_slot;
    }
}
