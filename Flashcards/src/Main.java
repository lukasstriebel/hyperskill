package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static Scanner s = new Scanner(System.in);
    static Map<String, String> defToSol = new HashMap<>();
    static Map<String, String> solToDef = new HashMap<>();
    static Map<String, Integer> mistakes = new HashMap<>();
    static List<String> log = new ArrayList<>();

    public static void main(String[] args) {
        String exportFile = null;
        if (args != null && args.length > 1) {
            if ("-import".equals(args[0])) {
                importFile(args[1]);
                if (args.length > 3 && "-export".equals(args[2])) {
                    exportFile = args[3];
                }
            } else if ("-export".equals(args[0])) {
                exportFile = args[1];
            }
        }
        run();
        if (exportFile != null) {
            exportFile(exportFile);
        }
    }

    static void run() {
        while (true) {
            print("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String action = read();
            switch (action) {
                case "add":
                    addCard();
                    break;
                case "remove":
                    removeCard();
                    break;
                case "ask":
                    askCard();
                    break;
                case "import":
                    print("File name:");
                    String fileName = read();
                    importFile(fileName);
                    break;
                case "export":
                    print("File name:");
                    fileName = read();
                    exportFile(fileName);
                    break;
                case "log":
                    log();
                    break;
                case "hardest card":
                    hardestCard();
                    break;
                case "reset stats":
                    resetStats();
                    break;
                case "exit":
                    print("Bye bye!");
                    return;
            }
        }
    }
    static void log() {
        print("File name:");
        String fileName = read();
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (String line : log) {
                writer.write(line + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        print("The log has been saved.");
    }

    static void hardestCard() {
        int max = 0;
        String card = "";
        for (Map.Entry<String, Integer> entry : mistakes.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                card = entry.getKey();
            }
        }
        print(String.format("The hardest card is \"%s\". You have %s errors answering it.\n", card, max));
    }

    static void resetStats() {
        mistakes = new HashMap<>();
    }


    static void addCard() {
        print("The card:");
        String def = read();
        print("The definition of the card:");
        String sol = read();
        if (!defToSol.containsKey(def)) {
            solToDef.put(sol, def);
            defToSol.put(def, sol);
            print(String.format("The pair (\"%s\":\"%s\") is added.\n", def, sol));
        } else {
            print("The card already exists.");
        }
    }

    static void removeCard() {
        print("The card:");
        String def = read();
        if (defToSol.containsKey(def)) {
            solToDef.remove(defToSol.get(def));
            defToSol.remove(def);
            print(String.format("The card \"%s\" is removed.\n", def));
        } else {
            print(String.format("Can't remove \"%s\": there is no such card.", def));
        }
    }

    static void askCard() {
        print("How many times to ask?");
        int count = Integer.parseInt(read());
        Set<Map.Entry<String, String>> entries = defToSol.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            if (count-- == 0) {
                return;
            }
            String def = entry.getKey();
            String sol = entry.getValue();
            print(String.format("Print the definition of \"%s\":", def));
            String ans = read();
            if (ans.equals(sol)) {
                print("Correct answer. ");
            } else {
                if (solToDef.containsKey(ans)) {
                    print(String.format("Wrong answer (the correct one is \"%s\", you've just written a definition of \"%s\" card). ", sol, solToDef.get(ans)));
                } else {
                    print(String.format("Wrong answer (the correct one is \"%s\"). ", sol));
                }
                mistakes.put(def, mistakes.getOrDefault(def, 0) + 1);
            }
        }
    }

    static void exportFile(String fileName) {
        int count = defToSol.size();
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Map.Entry<String, String> entry : defToSol.entrySet()) {
                writer.write(entry.getKey() + "\n");
                writer.write(entry.getValue() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        print(String.format("%d cards have been saved.", count));
    }

    static void importFile(String fileName) {
        int count = 0;
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            String def = null;
            String sol = null;
            while (sc.hasNextLine()) {
                if (def == null) {
                    def = sc.nextLine();
                } else {
                    sol = sc.nextLine();
                    solToDef.put(sol, def);
                    defToSol.put(def, sol);
                    def = null;
                    sol = null;
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        print(String.format("%d cards have been loaded.", count));
    }

    static void print(String str) {
        System.out.println(str);
        log.add(str);
    }

    static String read() {
        String line = s.nextLine();
        log.add(line);
        return line;
    }

    void stage4() {
        Scanner s = new Scanner(System.in);
        print("Input the number of cards:");
        int n = s.nextInt();
        read();
        String[][] cards = new String[n][2];
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            print(String.format("The card #%d:", i+1));
            String def = read();
            while (map.containsValue(def)) {
                print(String.format("The card #%d:", i+1));
                def = read();
            }
            print(String.format("The definition of the card #%d:", i+1));
            String sol = read();
            cards[i] = new String[]{def, sol};
            map.put(sol, def);
        }
        for (int i = 0; i < n; i++) {
            String def = cards[i][0];
            String sol = cards[i][1];
            print(String.format("Print the definition of \"%s\":", def));
            String ans = read();
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
        print("Input the number of cards:");
        int n = s.nextInt();
        read();
        String[][] cards = new String[n][2];
        for (int i = 0; i < n; i++) {
            print(String.format("The card #%d:", i+1));
            String def = read();
            print(String.format("The definition of the card #%d:", i+1));
            String sol = read();
            cards[i] = new String[]{def, sol};
        }
        for (int i = 0; i < n; i++) {
            String def = cards[i][0];
            String sol = cards[i][1];
            print(String.format("Print the definition of \"%s\":", def));
            String ans = read();
            if (ans.equals(sol)) {
                System.out.print("Correct answer. ");
            } else {
                System.out.print(String.format("Wrong answer (the correct one is \"%s\"). ", sol));
            }
        }
    }

    void stage2() {
        Scanner s = new Scanner(System.in);
        String def = read();
        String sol = read();
        String ans = read();
        if (ans.equals(sol)) {
            print("Your answer is right!");
        } else {
            print("Your answer is wrong...");
        }
    }

    void stage1() {
        print("Card:\n" +
                "purchase\n" +
                "Definition:\n" +
                "buy");
    }
}
