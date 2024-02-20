public enum Face
{
    CASTLE,
    VILLAGE,
    TOWER,
    FORT,
    HUT,
    HILL_FORT,
    SWAMP,
    RIVER,
    FOREST,
    MOUNTAIN,
    MIXED_TERRAIN,
    DOOMLAND,
    BURNING_DICE,
    MAGIC,
    PROVISIONS,
    MONSTER,
    RUNE_STEALER,
    VOLCANO,
    FULL_MOON,
    PORTICULLIS,
    DISCOVERY,
    WEAPONRY,
    WHITE_TREE,
    RING,
    GLOWING_DICE,
    IDENTITY,
    HUMAN,
    HUMAN_TRAINED,
    ELF,
    ELF_TRAINED,
    DWARF,
    DWARF_TRAINED,
    SWORD,
    SWORD_ENHANCED,
    BOW,
    BOW_ENHANCED,
    AXE,
    AXE_ENHANCED,
    WOLF,
    GHOUL,
    SPIDER,
    OGRE,
    ORC,
    TROLL,
    MAGIC_TWO_WEAPON_EQUALS_FOUR,
    MAGIC_MAGIC_EQUALS_FIVE,
    MAGIC_WEAPON_TIMES_TWO,
    MAGIC_WEAPON_EQUALS_FOUR,
    MAGIC_TWO_WEAPONS_EQUALS_THREE,
    MAGIC_MAGIC_EQUALS_FOUR,
    BURNING_SKULL,
    PLACEHOLDER;

    @Override
    public String toString() {
        return switch (this) {
            case CASTLE -> "Castle";
            case VILLAGE -> "Village";
            case TOWER -> "Tower";
            case FORT -> "Fort";
            case HUT -> "Hut";
            case HILL_FORT -> "Hill Fort";
            case SWAMP -> "Swamp";
            case RIVER -> "River";
            case FOREST -> "Forest";
            case MOUNTAIN -> "Mountain";
            case MIXED_TERRAIN -> "Mixed Terrain";
            case DOOMLAND -> "Doomland";
            case PROVISIONS -> "Provisions";
            case MAGIC -> "Magic";
            case DISCOVERY -> "Discovery";
            case WEAPONRY -> "Weaponry";
            case MONSTER -> "Monster";
            case BURNING_DICE -> "Burning Dice";
            case RUNE_STEALER -> "Rune Stealer";
            case WHITE_TREE -> "White Tree";
            case RING -> "Ring";
            case GLOWING_DICE -> "Glowing Dice";
            case VOLCANO -> "Volcano";
            case FULL_MOON -> "Full Moon";
            case PORTICULLIS -> "Porticullis";
            case IDENTITY -> "Identity";
            case HUMAN -> "Human";
            case HUMAN_TRAINED -> "Human (Trained)";
            case ELF -> "Elf";
            case ELF_TRAINED -> "Elf (Trained)";
            case DWARF -> "Dwarf";
            case DWARF_TRAINED -> "Dwarf (Trained)";
            case SWORD -> "Sword";
            case SWORD_ENHANCED -> "Sword (Enhanced)";
            case BOW -> "Bow";
            case BOW_ENHANCED -> "Bow (Enhanced)";
            case AXE -> "Axe";
            case AXE_ENHANCED -> "Axe (Enhanced)";
            case WOLF -> "Wolf";
            case GHOUL -> "Ghoul";
            case SPIDER -> "Spider";
            case OGRE -> "Ogre";
            case ORC -> "Orc";
            case TROLL -> "Troll";
            case MAGIC_TWO_WEAPON_EQUALS_FOUR -> "Magic: Both Weapon = 4";
            case MAGIC_MAGIC_EQUALS_FIVE -> "Magic: Magic = 5";
            case MAGIC_WEAPON_TIMES_TWO -> "Magic: Single Weapon x 2";
            case MAGIC_WEAPON_EQUALS_FOUR -> "Magic: Single Weapon = 4";
            case MAGIC_TWO_WEAPONS_EQUALS_THREE -> "Magic: Both Weapons = 3";
            case MAGIC_MAGIC_EQUALS_FOUR -> "Magic: Magic = 4";
            case BURNING_SKULL -> "Burning Skull";
            case PLACEHOLDER -> "Placeholder";
        };
    }
}
