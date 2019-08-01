
import java.util.Scanner;

public class CoffeeMachine {
    static int milk = 540, water = 1200, beans = 120, cups = 9, money = 550;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = s.next();
            switch (action) {
            case "take":
                takeMoney();
                break;
            case "fill":
                fill(s);
                break;
            case "buy":
                buy(s);
                break;
            case "remaining":
                printMachineState();
                break;
            case "exit":
                return;
            }
        }
    }

    static void takeMoney() {
        String text = "I gave you $%d\n";
        System.out.println(String.format(text, money));
        money = 0;
    }

    static void buy(Scanner s) {
        String text = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:";
        System.out.println(text);
        String choice = s.next();
        if ("back".equals(choice)) {
            return;
        }
        if (cups < 1) {
            System.out.println("Sorry, not enough cups");
            return;
        }
        if ("1".equals(choice)) {
            if (water < 250) {
                System.out.println("Sorry, not enough water");
                return;
            }
            if (beans < 16) {
                System.out.println("Sorry, not enough beans");
                return;
            }
            water -= 250;
            beans -= 16;
            money += 4;
        } else if ("2".equals(choice)) {
            if (water < 350) {
                System.out.println("Sorry, not enough water");
                return;
            }
            if (milk < 75) {
                System.out.println("Sorry, not enough milk");
                return;
            }
            if (beans < 20) {
                System.out.println("Sorry, not enough beans");
                return;
            }
            water -= 350;
            beans -= 20;
            milk -= 75;
            money += 7;
        } else {
            if (water < 200) {
                System.out.println("Sorry, not enough water");
                return;
            }
            if (milk < 100) {
                System.out.println("Sorry, not enough milk");
                return;
            }
            if (beans < 12) {
                System.out.println("Sorry, not enough beans");
                return;
            }
            water -= 200;
            beans -= 12;
            milk -= 100;
            money += 6;
        }
        cups--;
        System.out.println("I have enough resources, making you a coffee!\n");
    }

    static void fill(Scanner s) {
        System.out.println("Write how many ml of water do you want to add: ");
        water += s.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        milk += s.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        beans += s.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        cups += s.nextInt();
    }

    static void printMachineState() {
        String text = "The coffee machine has:\n" + "%d of water\n" + "%d of milk\n" + "%d of coffee beans\n"
                + "%d of disposable cups\n" + "$%d of money\n";
        System.out.println(String.format(text, water, milk, beans, cups, money));
    }
    
    void stage3() {
        Scanner s = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has: ");
        int water = s.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int milk = s.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        int beans = s.nextInt();
        System.out.println("Write how many cups of coffee you will need: ");
        int cups = s.nextInt();
        int max = Math.min(water / 200, Math.min(milk / 50, beans / 15));
        String text = String.format("No, I can make only %d cup(s) of coffee", max);
        if (max == cups) {
            text = "Yes, I can make that amount of coffee";
        } else if (max > cups) {
            text = String.format("Yes, I can make that amount of coffee (and even %d more than that)", max - cups);
        }
        System.out.println(text);
    }
    
    void stage2() {
        Scanner s = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need: ");
        int cups = s.nextInt();
        String text = "For %d cups of coffee you will need:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans";
        System.out.println(String.format(text, cups, cups * 200, cups * 50, cups * 15));
    }
    
    void stage1() {
        String text = "Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!";
        System.out.println(text);
    }
}
