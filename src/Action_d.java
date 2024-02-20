import java.util.ArrayList;

public class Action_d extends Dice
{
    @Override
    public int get_value() {
        return 0;
    }

    public enum Action_dice_type
    {
        STANDARD,
        RUNE_STEALER,
        RING,
        CUSTOM,
        PLACEHOLDER;

        @Override
        public String toString() {
            return switch (this) {
                case STANDARD -> "Standard Action Dice";
                case RUNE_STEALER -> "Rune Stealer Dice";
                case RING -> "Ring Dice";
                case CUSTOM -> "Custom Dice";
                case PLACEHOLDER -> "Placeholder lmao";
            };
        }
    }

    Action_dice_type type;

    public Action_d(Action_dice_type type) {
        super(type.toString(), 6);
        ArrayList<Face> faces = super.getFaces();
        this.type = type;
        if (type == Action_dice_type.CUSTOM)
            return;
        faces.set(1, Face.MAGIC);
        faces.set(2, Face.PROVISIONS);
        faces.set(3, Face.DISCOVERY);
        faces.set(4, Face.MONSTER);
        switch (type) {
            case STANDARD -> faces.set(5, Face.WEAPONRY);
            case RUNE_STEALER -> {
                faces.set(2, Face.RUNE_STEALER);
                faces.set(5, Face.RUNE_STEALER);
            }
            case RING -> {
                faces.set(2, Face.WHITE_TREE);
                faces.set(5, Face.RING);
            }
            default -> {
            }
        }
    }

    public Action_dice_type getType()
    {
        return type;
    }
}
