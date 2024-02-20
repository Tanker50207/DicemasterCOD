import java.util.ArrayList;

public class Player {
    private final Character_d characterDice;
    private final Weapon_d primaryWeaponDice;
    private final Weapon_d secondaryWeaponDice;

    public Player() {
        this.characterDice = new Character_d();
        this.primaryWeaponDice = new Weapon_d(Weapon_d.Slot.PRIMARY);
        this.secondaryWeaponDice = new Weapon_d(Weapon_d.Slot.SECONDARY);
        characterDice.roll();
        //If the character rolls a trained version, change it to an untrained version
        switch (characterDice.getCurrentFace()) {
            case HUMAN_TRAINED -> characterDice.setCurrentFace(0);
            case ELF_TRAINED -> characterDice.setCurrentFace(2);
            case DWARF_TRAINED -> characterDice.setCurrentFace(4);
            default -> {
            }
        }
        switch (characterDice.getCurrentFace()) {
            case HUMAN -> {primaryWeaponDice.setCurrentFace(0); secondaryWeaponDice.setCurrentFace(2);}
            case ELF -> {primaryWeaponDice.setCurrentFace(2); secondaryWeaponDice.setCurrentFace(4);}
            case DWARF -> {primaryWeaponDice.setCurrentFace(4); secondaryWeaponDice.setCurrentFace(0);}
            default -> {
            }
        }
    }

    public ArrayList<Dice> getDice() {
        ArrayList<Dice> dice = new ArrayList<Dice>();
        dice.add(characterDice);
        dice.add(primaryWeaponDice);
        dice.add(secondaryWeaponDice);
        return dice;
    }

    public static Face trainCharacter(Character_d characterDice) {
        switch (characterDice.getCurrentFace()) {
            case HUMAN -> characterDice.setCurrentFace(1);
            case ELF -> characterDice.setCurrentFace(3);
            case DWARF -> characterDice.setCurrentFace(5);
            default -> {
            }
        }
        return characterDice.getCurrentFace();
    }

    public static Face enhanceWeapon(Weapon_d weapon_dice) {
        switch (weapon_dice.getCurrentFace()) {
            case SWORD -> weapon_dice.setCurrentFace(1);
            case BOW -> weapon_dice.setCurrentFace(3);
            case AXE -> weapon_dice.setCurrentFace(5);
            default -> {}
        }
        return weapon_dice.getCurrentFace();
    }

    // getters and setters for each field
    public Character_d getCharacterDice() {
        return characterDice;
    }

    public Weapon_d getPrimaryWeaponDice() {
        return primaryWeaponDice;
    }

    public Weapon_d getSecondaryWeaponDice() {
        return secondaryWeaponDice;
    }
}