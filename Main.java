import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FlutteroidFarm farm = new FlutteroidFarm();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Flutteroid Farm Main Menu:")
            for (int loop = 0; loop < 25; loop++) {
                System.out.print("-");
            }
            System.out.println("\n 1. Display Farm Flutteroids (Ascending)");
            System.out.println(" 2. Display Farm Flutteroids (Descending)");
            System.out.println(" 3. Display Farm Flutteroids (Structure)");
            System.out.println(" 4. Display Flutteroid Escapees");
            System.out.println(" 5. Build Farm");
            System.out.println(" 6. Update Farm");
            System.out.println(" 7. Find Flutteroid");
            System.out.println(" 8. Quit Program");
            for (int loop = 0; loop < 25; loop++) {
                System.out.print("-");
            }
            String input = scanner.nextLine();
            switch (input) {
                case "1": {
                    String str = "-=: Zebestation Farm Flutteroids - Ascending: (" + farm.getNumberOfFlutteroids() + ") :=-";
                    System.out.println(str);
                    for (int loop = 0; loop < str.length(); loop++) {
                        System.out.print("-");
                    }
                    farm.displayFlutteroidFarmAscending();
                }
                case "2": {
                    String str = "-=: Zebestation Farm Flutteroids - Descending: (" + farm.getNumberOfFlutteroids() + ") :=-";
                    System.out.println(str);
                    for (int loop = 0; loop < str.length(); loop++) {
                        System.out.print("-");
                    }
                    farm.displayFlutteroidFarmDescending();
                }
                case "3": {
                    String str = "-=: Zebestation Farm Flutteroids - Structure: (" + farm.getNumberOfFlutteroids() + ") :=-";
                    System.out.println(str);
                    for (int loop = 0; loop < str.length(); loop++) {
                        System.out.print("-");
                    }
                    farm.displayFlutteroidFarmStructure();
                }
                case "4": {
                    farm.displayEscapedFlutteroids();
                }
                case "5": {
                    System.out.print("Enter the number of flutteroids to start with: ");
                    int amount = scanner.nextInt();
                    for (int loop = 0; loop < amount; loop++) {
                        Flutteroid flut = Flutteroid.generateFlutteroid();
                        System.out.print(FlutteroidFarm.indent);
                        System.out.print("Added to Farm: {");
                        System.out.print(flut.getNameID());
                        System.out.print(": ");
                        System.out.print(flut.getLocation().toString());
                        System.out.println("}");
                    }
                }
                case "7": {
                    System.out.print("Enter a flutteroid name to locate: ");
                    String nameID = scanner.nextLine();
                    Flutteroid flut = farm.findFlutteroid(nameID);
                    if (flut != null) {
                        System.out.println("\nFlutteroid Located:\n");
                        String str = "-=: Flutteroid Info :=-";
                        System.out.println(str);
                        for (int loop = 0; loop < str.length() + 3; loop++) {
                            System.out.print("-");
                        }

                        System.out.print(FlutteroidFarm.indent);
                    }
                }
            }
            System.out.println("\n");
        }
    }
}
