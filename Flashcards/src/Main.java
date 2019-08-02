package flashcards;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[][] cards = new String[n][2];
        Map<String, String> map = new HashMap<>();
        while (true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String action = s.nextLine();
            System.out.println("The card:");
            String def = s.nextLine();
            System.out.println("The definition of the card");
            String sol = s.nextLine();
            cards[i] = new String[]{def, sol};
            map.put(sol, def);
        }
        for (int i = 0; i < n; i++) {
            String def = cards[i][0];
            String sol = cards[i][1];
            System.out.println(String.format("Print the definition of \"%s\":", def));
            String ans = s.nextLine();
            if (ans.equals(sol)) {
                System.out.print("Correct answer. ");
            } else if (map.containsKey(ans)) {
                System.out.print(String.format("Wrong answer (the correct one is \"%s\", you've just written a definition of \"%s\" card). ", sol, map.get(ans)));
            } else {
                System.out.print(String.format("Wrong answer (the correct one is \"%s\"). ", sol));
            }
        }
    }
    void stage4() {
        Scanner s = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int n = s.nextInt();
        s.nextLine();
        String[][] cards = new String[n][2];
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("The card #%d:", i+1));
            String def = s.nextLine();
            while (map.containsValue(def)) {
                System.out.println(String.format("The card #%d:", i+1));
                def = s.nextLine();
            }
            System.out.println(String.format("The definition of the card #%d:", i+1));
            String sol = s.nextLine();
            cards[i] = new String[]{def, sol};
            map.put(sol, def);
        }
        for (int i = 0; i < n; i++) {
            String def = cards[i][0];
            String sol = cards[i][1];
            System.out.println(String.format("Print the definition of \"%s\":", def));
            String ans = s.nextLine();
            if (ans.equals(sol)) {
                System.out.print("Correct answer. ");
            } else if (map.containsKey(ans)) {
                System.out.print(String.format("Wrong answer (the correct one is \"%s\", you've just written a definition of \"%s\" card). ", sol, map.get(ans)));
            } else {
                System.out.print(String.format("Wrong answer (the correct one is \"%s\"). ", sol));
            }
        }
    }

    void stage3() {
        Scanner s = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int n = s.nextInt();
        s.nextLine();
        String[][] cards = new String[n][2];
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("The card #%d:", i+1));
            String def = s.nextLine();
            System.out.println(String.format("The definition of the card #%d:", i+1));
            String sol = s.nextLine();
            cards[i] = new String[]{def, sol};
        }
        for (int i = 0; i < n; i++) {
            String def = cards[i][0];
            String sol = cards[i][1];
            System.out.println(String.format("Print the definition of \"%s\":", def));
            String ans = s.nextLine();
            if (ans.equals(sol)) {
                System.out.print("Correct answer. ");
            } else {
                System.out.print(String.format("Wrong answer (the correct one is \"%s\"). ", sol));
            }
        }
    }

    void stage2() {
        Scanner s = new Scanner(System.in);
        String def = s.nextLine();
        String sol = s.nextLine();
        String ans = s.nextLine();
        if (ans.equals(sol)) {
            System.out.println("Your answer is right!");
        } else {
            System.out.println("Your answer is wrong...");
        }
    }

    void stage1() {
        System.out.println("Card:\n" +
                "purchase\n" +
                "Definition:\n" +
                "buy");
    }
}
