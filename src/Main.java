
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static messageSystem messageSystem = new messageSystem();
    public static void main(String[] args) {
        //time
        long startTime = System.currentTimeMillis();
        while (true) {
            System.out.println();
            System.out.println("======MENU======");
            System.out.println("1. New a message.");
            System.out.println("2. View messages.");
            System.out.println("3. Send all messages.");
            System.out.println("4. Remove last message.");
            System.out.println("5. View sent messages.");
            System.out.println("6. Delete first sent messages.");
            System.out.println("0. Exit.");
            System.out.println("================");
            System.out.println();
            System.out.print("Enter option to continue (0 -> 6): ");

            int op = scanner.nextInt();
            switch (op){
                case 1:
                    messageSystem.newMessStack();
                    break;
                case 2:
                    messageSystem.viewMessageStack();
                    break;
                case 3:
                    messageSystem.sendMessage();
                    break;
                case 4:
                    messageSystem.removeLastMess();
                    break;
                case 5:
                    messageSystem.sent();
                    break;
                case 6:
                    messageSystem.removeFirstSent();
                    break;
                case 0:
                    System.out.println("GoodBye!");
                    return;
            }
            long endTime = System.currentTimeMillis();
            double time = (endTime - startTime)/1000.0;
            System.out.println("Runtime in seconds: " + time);
        }
    }
}