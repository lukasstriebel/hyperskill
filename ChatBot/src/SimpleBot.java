import java.util.Scanner;

public class SimpleBot {
    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! My name is Aid.");
        System.out.println("I was created in 2018.");
        System.out.println("Please, remind me your name.");
        String name = in.next();
        System.out.printf("What a great name you have, %s!\n", name);
        System.out.println("Let me guess your age.");
        System.out.println("Say me remainders of dividing your age by 3, 5 and 7.");
        int rem3 = in.nextInt();
        int rem5 = in.nextInt();
        int rem7 = in.nextInt();
        int age = (rem3 * 70 + rem5 * 21 + rem7 * 15) % 105;
        System.out.printf("Your age is %d: that's a good time to start programming!\n", age);
        System.out.println("Now I will prove to you that I can count to any number you want.");
        int number = in.nextInt();
        for (int i = 0; i <= number; i++) {
            System.out.printf("%d!\n", i);
        }
        System.out.println("Let's test your programming knowledge.");
        System.out.println("Why do we use methods?");
        System.out.println("1. To repeat a statement multiple times.");
        System.out.println("2. To decompose a program into several small subroutines.");
        System.out.println("3. To determine the execution time of a program.");
        System.out.println("4. To interrupt the execution of a program.");
        while (true) {
            int guess = in.nextInt();
            if (guess == 2) {
                break;
            }
            System.out.println("Please, try again.");
        }
        System.out.println("Congratulations, have a nice day!");
    }
}
