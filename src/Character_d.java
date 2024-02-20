import java.util.ArrayList;

public class Character_d extends Dice
{
    public Character_d() {
        super("Character", 6);
        ArrayList<Face> faces = super.getFaces();
        faces.set(0, Face.HUMAN);
        faces.set(1, Face.HUMAN_TRAINED);
        faces.set(2, Face.ELF);
        faces.set(3, Face.ELF_TRAINED);
        faces.set(4, Face.DWARF);
        faces.set(5, Face.DWARF_TRAINED);
    }

    @Override
    public int get_value() {
        return 0;
    }
}
