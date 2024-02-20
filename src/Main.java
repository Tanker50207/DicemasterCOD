import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Dice> player_1_reserve = new ArrayList<Dice>();
    static ArrayList<Dice> player_2_reserve = new ArrayList<Dice>();
    static ArrayList<Dice> player_1_play = new ArrayList<Dice>();
    static ArrayList<Dice> player_2_play = new ArrayList<Dice>();
    static ArrayList<Dice> player_1_rolling = new ArrayList<Dice>();
    static ArrayList<Dice> player_2_rolling = new ArrayList<Dice>();
    static ArrayList<Dice> player_1_active = new ArrayList<Dice>();
    static ArrayList<Dice> player_2_active = new ArrayList<Dice>();
    static ArrayList<Dice> adventure_route = new ArrayList<Dice>();
    static Identity_d player_1_identity = new Identity_d();
    static Identity_d player_2_identity = new Identity_d();

    private static void print_adventure_route()
    {
        System.out.println("Adventure route:\n\nSite value: " + site_sum() + "\nDoom value: " + doom_value() + "\n\nDice:");
        for (Dice d : adventure_route)
        {
            if (d.getCurrentFace() == Face.DOOMLAND)
                System.out.println(d.getCurrentFace().toString() + " " + doom_value());
            else if (d instanceof Landscape_d)
                System.out.println(d.getCurrentFace().toString() + " " + d.get_value() + " (" + (site_sum() + d.get_value()) + ")");
            else
                System.out.println("\n" + d.getCurrentFace().toString() + " " + d.get_value() + "\n");
        }
    }

    private static void organize_adventure_route()
    {
        //Keeps all dice in order but put all doomland just before the destination
        int i = 1;
        int j = 0;
        while (j < 4)
        {
            if (adventure_route.get(i).getCurrentFace() == Face.DOOMLAND)
            {
                Dice temp = adventure_route.get(i);
                adventure_route.remove(i);
                adventure_route.add(adventure_route.size() - 1, temp);
            }
            else
                i++;
            j++;
        }
    }
    /*private static void printAdventureRoutePretty() {
    // Prepare the headers
    String[] headers = {"Dice Face", "Value"};

    // Prepare the data
    String[][] data = new String[adventure_route.size()][2];
    for (int i = 0; i < adventure_route.size(); i++) {
        Dice d = adventure_route.get(i);
        data[i][0] = d.getCurrentFace().toString();
        data[i][1] = Integer.toString(d.get_value());
    }

    // Print the table
    PrettyTable.print(headers, data);
}*/
    public static void printAdventureRoutePretty() {
    // Prepare the headers
        String[] headers = {"Location", "Value", "Total"};

    // Prepare the data
        String[][] data = new String[adventure_route.size()][3];
        for (int i = 0; i < adventure_route.size(); i++) {
            Dice d = adventure_route.get(i);
            data[i][0] = d.getCurrentFace().toString();
            data[i][1] = Integer.toString(d.get_value());
            if (d.getCurrentFace() == Face.DOOMLAND)
                data[i][2] = Integer.toString(doom_value());
            else if (d instanceof Landscape_d)
                data[i][2] = Integer.toString(site_sum() + d.get_value());
            else
                data[i][2] = Integer.toString(site_sum());

            // Check if the dice is a Site_d
            if (d instanceof Site_d) {
                // Append special characters or text to the dice face or value
                data[i][0] = PrettyTable.bold(data[i][0]); // This will make the dice face bold
                data[i][1] = PrettyTable.bold(data[i][1]); // This will make the dice value bold
                data[i][2] = PrettyTable.bold(data[i][2]); // This will make the dice value bold
            }
        }

        // Print the table
        PrettyTable.print(headers, data);
    }
    private static int site_sum()
    {
        //Sum of all site dice
        int value = 0;
        for (Dice d : adventure_route)
        {
            if (d instanceof Site_d)
                value += d.get_value();
        }
        return value;
    }

    private static int doom_value()
    {
        //Doomland's value is the sum of all adventure route dice
        int value = 0;
        for (Dice d : adventure_route)
        {
            value += d.get_value();
        }
        return value;
    }

    private static void makeAdventure()
    {
        if(adventure_route.size() == 0)
        {
            adventure_route.add(new Site_d());
            adventure_route.add(new Landscape_d(1));
            adventure_route.add(new Landscape_d(2));
            adventure_route.add(new Landscape_d(1));
            adventure_route.add(new Landscape_d(2));
            adventure_route.add(new Site_d());
            Dice.roll_area(adventure_route);
            organize_adventure_route();
            return;
        }
        int last_site = adventure_route.get(adventure_route.size() - 1).getCurrentFaceNum();
        Dice.roll_area(adventure_route);
        organize_adventure_route();
        adventure_route.get(0).setCurrentFace(last_site);
    }


    private static String[][] prepareData(ArrayList<Dice> zone) {
        String[][] data = zone.size() - 3 < 2 ? new String[2][3] : new String[zone.size() - 3][3];
        data[0][0] = zone.get(0).getCurrentFace().toString();
        for (int i = 1; zone.size() - 3 < 2 ? i < 2 : i < zone.size() - 3; i++) {
            data[i][0] = "";
        }
        data[0][1] = zone.get(1).getCurrentFace().toString();
        data[1][1] = zone.get(2).getCurrentFace().toString();
        for (int i = 2; i < zone.size() - 3; i++) {
            data[i][1] = "";
        }
        if (zone.size() - 3 < 2) {
            for (int i = 0; i < 2; i++)
                data[i][2] = "";
        }
        for (int i = 0; i < zone.size() - 3; i++) {
            data[i][2] = zone.get(i + 3).getCurrentFace().toString();
        }
        return data;
    }

    private static void print_play_area_pretty(ArrayList<Dice> zone1, ArrayList<Dice> zone2) {
        // Prepare the headers
        String[] headers = {"Character", "Weapons", "Prepared Dice"};
        for (int i = 0; i < 3; i++)
            headers[i] = PrettyTable.bold(headers[i]);

        // Prepare the data for Player 1
        String[][] data1 = prepareData(zone1);

        // Prepare the data for Player 2
        String[][] data2 = prepareData(zone2);

        System.out.printf("%-81s   %-81s\n", "\033[1;31mPlayer 1 area :\033[0m", "\033[1;31mPlayer 2 area :\033[0m");

        System.out.println(StringUtils.repeat("-", 23 * 3 + 1) + "   " + StringUtils.repeat("-", 23 * 3 + 1));
        // Print the headers
        System.out.printf("| %-31s | %-31s | %-31s |   | %-31s | %-31s | %-31s |\n", headers[0], headers[1], headers[2], headers[0], headers[1], headers[2]);

        // Print the rows
        int maxRows = Math.max(data1.length, data2.length);
        for (int i = 0; i < maxRows; i++) {
            System.out.println(StringUtils.repeat("-", 23 * 3 + 1) + "   " + StringUtils.repeat("-", 23 * 3 + 1));
            // Print Player 1's row
            if (i < data1.length) {
                System.out.printf("| %-20s | %-20s | %-20s |  ", data1[i][0], data1[i][1], data1[i][2]);
            } else {
                System.out.printf("| %-20s | %-20s | %-20s |  ", "", "", "");
            }

            // Print Player 2's row
            if (i < data2.length) {
                System.out.printf(" | %-20s | %-20s | %-20s |\n", data2[i][0], data2[i][1], data2[i][2]);
            } else {
                System.out.printf(" | %-20s | %-20s | %-20s |\n", "", "", "");
            }
        }
        System.out.println(StringUtils.repeat("-", 23 * 3 + 1) + "   " + StringUtils.repeat("-", 23 * 3 + 1));
    }
    private static void print_rolling_area_pretty(ArrayList<Dice> zone) {
        // Prepare the headers
        String[] headers = {"Dice Faces"};

        // Prepare the data
        String[][] data = new String[zone.size()][1];
        for (int i = 0; i < zone.size(); i++) {
            data[i][0] = zone.get(i).getCurrentFace().toString();
        }

        // Print the table
        PrettyTable.print(headers, data);
    }

    private static void sort_play_area(ArrayList<Dice> zone) {
        // Sort the active area
        for (int i = 0; i < zone.size() - 3; i++) {
            for (int j = i + 1; j < zone.size() - 3; j++) {
                if (zone.get(i + 3).getCurrentFace().ordinal() > zone.get(j + 3).getCurrentFace().ordinal()) {
                    Dice temp = zone.get(i + 3);
                    zone.set(i + 3, zone.get(j + 3));
                    zone.set(j + 3, temp);
                }
            }
        }
    }

    private static void innit_reserve()
    {
        for (int i = 0; i < 11; i++)
        {
            player_1_reserve.add(new Action_d(Action_d.Action_dice_type.STANDARD));
            player_2_reserve.add(new Action_d(Action_d.Action_dice_type.STANDARD));
        }
        for (int i = 0; i < 2; i++)
        {
            player_1_reserve.add(new Action_d(Action_d.Action_dice_type.RUNE_STEALER));
            player_2_reserve.add(new Action_d(Action_d.Action_dice_type.RUNE_STEALER));
        }
        for (int i = 0; i < 2; i++)
        {
            player_1_reserve.add(new Action_d(Action_d.Action_dice_type.RING));
            player_2_reserve.add(new Action_d(Action_d.Action_dice_type.RING));
        }
    }

    private static void print_reserve(ArrayList<Dice> reserve) {
        // Prepare the headers
        String[] headers = {"Standard", "Rune Stealer", "Ring"};
        int[] counts = new int[3];


        // Prepare the data
        String[][] data = new String[1][3];
        for (Dice d : reserve) {
            if (d instanceof Action_d ad) {
                switch (ad.getType()) {
                    case STANDARD -> counts[0]++;
                    case RUNE_STEALER -> counts[1]++;
                    case RING ->   counts[2]++;
                }
            }
        }
        data[0][0] = Integer.toString(counts[0]);
        data[0][1] = Integer.toString(counts[1]);
        data[0][2] = Integer.toString(counts[2]);

        // Print the table
        PrettyTable.print(headers, data);
    }

    private static void game_loop()
    {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        while (true)
        {
            System.out.println("\nPlayer 1 turn:\nSelect 7 dice from reserve:");
            for (int i = 0; i < 7; i++)
            {
                System.out.println("Select dice " + (i + 1) + ":");
                int index = 0;
                String in = scanner.next();
                try
                {
                    index = Integer.parseInt(in);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input");
                    i--;
                    continue;
                }
                player_1_rolling.add(player_1_reserve.get(index));
                player_1_reserve.remove(index);
            }
            Dice.roll_area(player_1_rolling);
            System.out.println("\nPlayer 1 rolling area:");
            print_rolling_area_pretty(player_1_rolling);
            System.out.println("\nSelect dices to keep:");
            while (true)
            {
                System.out.println("Select dice to keep:");
                String in = scanner.nextLine();
                int index = 0;
                if (in.equals("stop"))
                    break;
                try
                {
                    index = Integer.parseInt(in) - 1;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input");
                    continue;
                }
                player_1_play.add(player_1_rolling.get(index));
                player_1_rolling.remove(index);
                System.out.println("\nPlayer 1 rolling area:");
                print_rolling_area_pretty(player_1_rolling);
                print_play_area_pretty(player_1_play, player_2_play);
                if (player_1_rolling.size() == 0)
                    break;
            }


        }
    }
    public static void main(String[] args)
    {
        makeAdventure();
        printAdventureRoutePretty();
        System.out.println();
        player_1_play = new Player().getDice();
        player_2_play = new Player().getDice();
        player_1_identity = new Identity_d();
        player_2_identity = new Identity_d();
        innit_reserve();
        sort_play_area(player_1_play);
        sort_play_area(player_2_play);
        print_play_area_pretty(player_1_play, player_2_play);
        System.out.println("\nPlayer 1 reserve:");
        print_reserve(player_1_reserve);
        System.out.println("\nPlayer 2 reserve:");
        print_reserve(player_2_reserve);
        game_loop();
    }
}