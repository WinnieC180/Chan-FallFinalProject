import java.util.Scanner;
public class Delivery {
    private final String YELLOW = "\033[0;93m";
    private final String BRIGHT_BLUE = "\033[0;94m";
    private final String WHITE = "\u001B[37m";
    private final String RED = "\033[0;31m";

    private final String CHECK = "\u2714";

    Scanner scan;
    Customer[] deliveryList;
    int coins;
    int x;
    int y;
    double difficulty; //makes it harder by increasing the chase of the user facing a rude customer

    public Delivery() {
        scan = new Scanner(System.in);
        coins = 0;
        x = 0;
        y = 0;
        Board.clearBoard();
        difficulty = 0.2;
    }

    public void play() {
        welcome();
        menu();
    }

    public void welcome() {
        System.out.println();
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.println();
        System.out.println("╔═.✵.════════════════════════════════════════╗" + "\n        Welcome to Speedy Food "
                + name + "!\n╚════════════════════════════════════════.✵.═╝\n");

        System.out.println("Instructions: \n- There are 3 modes in this game easy, normal, hard\n- In easy mode you will have to deliver less orders, and in hard mode you will have to deliver more\n- In the hard mode it is also easier to encounter a rude customer\n- And you will have to deliver the order based on the coordinates given by the customer");
        System.out.print("\nWhich mode do you choose? (E)asy (N)ormal (H)ard: ");
        String mode = scan.nextLine();

        /*The following if  and else if statement will create a deliveryList depending on the difficulty so
        if it is in hard mode the user will deliver 7 pizzas and for each element a new Customer object is created*/
        if (mode.equals("H") || mode.equals("h")) {
            System.out.println(RED + "\nYou have chosen the hard mode. " + WHITE);

            deliveryList = new Customer[7];
            for (int i = 0; i < deliveryList.length; i++) {
                deliveryList[i] = new Customer();
            }
            allDiffCoordinates();
            difficulty = 0.5;
        } else if (mode.equals("N") || mode.equals("n")) {
            System.out.println(YELLOW + "\nYou have chosen the normal mode. " + WHITE);

            deliveryList = new Customer[5];
            for (int i = 0; i < deliveryList.length; i++) {
                deliveryList[i] = new Customer();
            }
            allDiffCoordinates();
            difficulty = 0.3;
        } else {
            System.out.println(BRIGHT_BLUE + "\nYou have chosen the easy mode. " + WHITE);

            deliveryList = new Customer[3];
            for (int i = 0; i < deliveryList.length; i++) {
                deliveryList[i] = new Customer();
            }
            allDiffCoordinates();
        }
    }

    public void menu() {
        String choice = "";
        while (!determineWin() && !choice.equals("e")  && !choice.equals("E")) { //The menu will continue printing until the user delivered all the pizzas OR decide to exit
            System.out.println("\nMain Menu: \n═══════════════════════════════════════════════\n");
            System.out.println("(C)heck your list of orders you have to deliver");
            System.out.println("(S)tart delivering pizzas");
            System.out.println("(Y)our wallet");
            System.out.println("(E)xit game");
            System.out.println();
            System.out.println("═══════════════════════════════════════════════\n");
            System.out.print("Enter your choice: ");
            choice = scan.nextLine();
            processChoice(choice);
        }

        if (determineWin()) {
            System.out.println("\n══════════════════════════════════");
            System.out.println("You delivered all the pizzas yay :D");
            System.out.println("══════════════════════════════════");
            System.out.println("\nCoins earned: " + YELLOW + coins + WHITE);
        }
    }

    private boolean determineWin() { //helper method to check if all the pizzas are delivered
        for (Customer delivery : deliveryList) {
            if (!delivery.getDelivered()) {
                return false;
            }
        }

        return true;
    }

    private void processChoice(String choice) {
        if (choice.equals("C") || choice.equals("c")) {
            printCustomers();
        } else if (choice.equals("Y") || choice.equals("y")) {
            System.out.println();
            System.out.println("----------------------------");
            System.out.println("You have " + coins + " coins");
            if (coins == 0) {
                System.out.println("* To earn coins, deliver the pizzas *");
                System.out.println("----------------------------");
            }
        } else if (choice.equals("e") || choice.equals("E")) {
            System.out.println("\nBye bye");

        } else if (choice.equals("S") || choice.equals("s")) { //when the user decides to start delivering

            System.out.println("\nDelivery that is left to make: ");
            System.out.println("How to read the coordinates: (row, column) \n");

            for (int i =0; i < deliveryList.length; i++) { //prints out all the delivery that still need to be made
                if (!deliveryList[i].getDelivered()) {
                    System.out.println("Customer " + (i+1) + " is at (" + deliveryList[i].getX() + ", " + deliveryList[i].getY() + ")");
                }
            }

            Board.move(x, y);
            Board.printBoard();

            String yOrN = "yes";
            boolean stop = false;
            boolean out = false;

            while ((yOrN.equals("yes")) && !determineWin()) {
                while (!out) { //allows the user to continue to move along the board until they delivered one pizza
                    System.out.println();
                    System.out.println(BRIGHT_BLUE + "\nYou are currently at (" + x + ", " + y + ")"+ WHITE);
                    System.out.println("Which direction do you want to go to? ");
                    System.out.print("Enter w(up), s(down), d(right), a(left): ");
                    String direction = scan.nextLine();

                    System.out.println();
                    if (direction.equals("w") || direction.equals("W")) {
                        if (!(x-1 < 0)) {
                            x--;
                            out = true;
                            Board.clearBoard();
                            Board.move(x, y);
                            Board.printBoard();
                        } else {
                            System.out.println(RED + "That is outside the board!" + WHITE);
                        }
                    } else if (direction.equals("s") || direction.equals("S")) {
                        if (!(x+1 > 4)) {
                            x++;
                            out = true;
                            Board.clearBoard();
                            Board.move(x, y);
                            Board.printBoard();
                        } else {
                            System.out.println(RED + "That is outside the board!" + WHITE);
                        }

                    } else if ((direction.equals("d") || direction.equals("D"))) {
                        if (!(y+1 > 4)) {
                            y++;
                            out = true;
                            Board.clearBoard();
                            Board.move(x, y);
                            Board.printBoard();
                        } else {
                            System.out.println(RED + "That is outside the board!" + WHITE);
                        }
                    } else if (direction.equals("a") || direction.equals("A")) {
                        if (!(y-1 < 0)) {
                            y--;
                            out = true;
                            Board.clearBoard();
                            Board.move(x, y);
                            Board.printBoard();
                        } else {
                            System.out.println(RED + "That is outside the board!" + WHITE);
                        }
                    } else {
                        System.out.println(RED + "That is not an option-" + WHITE);
                    }
                }

                for (int i = 0; i < deliveryList.length; i++) {
                    if (deliveryList[i].getX() == x && deliveryList[i].getY() == y && !deliveryList[i].getDelivered()) {

                        deliveryList[i].setDelivered();
                        System.out.println(YELLOW + "You delivered customer " + (i+1) + "'s order" + WHITE);
                        if (difficulty == 0.5) { //hardMode
                            int ranNum = (int)((Math.random() * 10) + 1);
                            if (ranNum > 6) {
                                if (ranNum <= 2) {
                                    System.out.println("The customer complained you are too slow!");
                                    System.out.println("They refused to tip you :(");
                                } else {
                                    System.out.println("The customer complained the pizza is cold!");
                                    System.out.println("They refused to tip you :(");
                                }
                                coins += 3;
                                System.out.println("They paid you 3 coins");
                                stop = true;
                            } else {
                                int ranCoins = (int)((Math.random()*6)+5);
                                coins += ranCoins;
                                System.out.println("They paid you " + ranCoins + " coins!");
                                stop = true;
                            }
                        } else if (difficulty == 0.3) { //normalMode
                            int ranNum = (int)((Math.random() * 10) + 1);
                            if (ranNum < 4) {
                                if (ranNum <= 2) {
                                    System.out.println("The customer complained you are too slow!");
                                    System.out.println("They refused to tip you :(");
                                } else {
                                    System.out.println("The customer complained the pizza is cold!");
                                    System.out.println("They refused to tip you :(");
                                }
                                coins += 3;
                                System.out.println("They paid you 3 coins");
                                stop = true;
                            } else {
                                int ranCoins = (int)((Math.random()*6)+5);
                                coins += ranCoins;
                                System.out.println("They paid you " + ranCoins + " coins!");
                                stop = true;
                            }
                        } else { //easyMode
                            int ranNum = (int)((Math.random() * 10) + 1);
                            if (ranNum < 3) {
                                if (ranNum == 2) {
                                    System.out.println("The customer complained you are too slow!");
                                    System.out.println("They refused to tip you :(");
                                } else {
                                    System.out.println("The customer complained the pizza is cold!");
                                    System.out.println("They refused to tip you :(");
                                }
                                coins += 3;
                                System.out.println("They paid you 3 coins");
                                stop = true;
                            } else {
                                int ranCoins = (int)((Math.random()*6)+5);
                                coins += ranCoins;
                                System.out.println("They paid you " + ranCoins + " coins!");
                                stop = true;
                            }
                        }

                    }
                }

                if(!stop) { //When an order is delivered it will not print again
                    out = false;
                } else {
                    yOrN = "no";
                }
            }
        } else {
            System.out.println(RED + "That is not a menu option! Try again" + WHITE);
        }
    }

    private void printCustomers() { //if delivered turn text blue add checkmark //helper method used in processChoice()
        System.out.println();
        int i = 0;
        for (Customer delivery : deliveryList) {
            i++;

            if (delivery.getDelivered()) {
                System.out.println("Customer" + i + ": " + CHECK + " " + BRIGHT_BLUE + "(Delivered)" + WHITE + "\n══════════════════════\n"+ delivery.getOrder() + "\n");
            } else {
                System.out.println("Customer" + i + ": \n══════════════════════\n"+ delivery.getOrder() + "\n");
            }
        }
    }

    private void allDiffCoordinates() { //to prevent any repetitive coordinates of the Customer objects
        for (int i = 0; i < deliveryList.length; i++) {
            int lastX = deliveryList[i].getX();
            int lastY = deliveryList[i].getY();

            for (int j = 0; j < deliveryList.length; j++) {
                while (lastX == deliveryList[j].getX() && lastY == deliveryList[j].getY())
                {
                    deliveryList[j].randomCoord();
                }
            }
        }
    }



}

//    int length = 0;
//      for (int i =0; i < deliveryList.length; i++) {
//        if (!deliveryList[i].getDelivered()) {
//        length++;
//        System.out.println("Customer " + (i+1) + " is at (" + deliveryList[i].getX() + ", " + deliveryList[i].getY() + ")");
//        }
//        }
//
//        Customer[] leftToDeliver = new Customer[length];
//        int j = 0;
//        for (int i =0; i < deliveryList.length; i++) {
//        if (!deliveryList[i].getDelivered()) {
//        leftToDeliver[j] = deliveryList[i];
//        j++;
//        }
//        }