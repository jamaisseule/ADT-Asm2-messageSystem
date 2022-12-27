import java.util.Scanner;

public class messageSystem {
    private static Stack<String> messStack = new Stack<>();
    private static Queue<String> messQueue = new Queue<>();
    private static Stack<String> sentStack = new Stack<>();
    private static Queue<String> sentQueue = new Queue<>();
    static Scanner scanner = new Scanner(System.in);

    public static boolean newMessStack() {
        System.out.print("New message: ");
        String message = scanner.nextLine();
        if (message != null && message.length() <=  250 && message.length() > 0) {
            try {
                messStack.push(message);
                System.out.println("Your message: [" + message + "]");
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            System.out.println("ERROR! The message is not syntactically correct.");
        }
        return false;
    }

    public static boolean removeLastMess() {
        Stack<String> stack = messStack;
        if (!stack.isEmpty()) {
            try {
                System.out.println("Removed: [" + stack.pop() + "]");
                return true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
        }
        System.out.println("There is no message here!");
        return false;
    }

    public static boolean removeFirstSent() {
        Stack<String> stack = sentStack;
        if (!stack.isEmpty()) {
            try {
                System.out.println("Removed: [" + stack.pop() + "]");
                return true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
        }
        System.out.println("There is no message here!");
        return false;
    }

    public static boolean viewMessageStack() {
        if (messStack.isEmpty()) {
            System.out.println("Empty!");
            return false;
        }
        try {
            System.out.print("Your Message: ");
            String[] temp = new String[messStack.size()];
            for (int i = 0; messStack.size() >= 1; i++) { //cần sử dụng kích thước message Stack vì nó bị giảm trong trường hợp này.
                temp[i] = messStack.pop();   //lấy tất cả phần tử ra một mảng để trình bày nó rồi đẩy tất cả trở lại sau.
                System.out.print("[" + temp[i] + (messStack.size() != 0 ? "], " : "].\n"));
            }
            for (int i = temp.length - 1; messStack.size() < temp.length; i--) {
                messStack.push(temp[i]); // push tất cả trở lại bên trong
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    public static boolean sendMessage() {
        if (messStack.isEmpty()) {
            return false;
        }
        if (messStackToQueue()) {
            if (messToInboxQueue()) {
                if (inboxQueueToStack()) {
                    System.out.println("Message sent successfully");
                    return true;
                }
            }
        }
        return false;
    }

    //chuyển mess từ stack sang queue
    public static boolean messStackToQueue() {
        try {
            for (int i = 0; messStack.size() >= 1; i++) {
                messQueue.offer(messStack.pop());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    //từ mess của queue chuyển qua inbox của queue
    public static boolean messToInboxQueue() {
        try {
            for (int i = 0; messQueue.size() >= 1; i++) {
                sentQueue.offer(messQueue.poll());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    //chuyển tất cả từ inbox queue sang inbox của stack
    public static boolean inboxQueueToStack() {
        try {
            for (int i = 0; sentQueue.size() >= 1; i++) {
                sentStack.push(sentQueue.poll());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public static boolean sent() {
        String[] temp = new String[sentStack.size()];
        System.out.println("This is the message sent!" );
        System.out.printf(sentStack.isEmpty() ? "Empty!\n" : "(%d messages):\n", sentStack.size());

        try {
            for (int i = 0; sentStack.size() >= 1; i++) {
                temp[i] = sentStack.pop();
                System.out.print("[" + temp[i] + (sentStack.size() != 0 ? "], " : "].\n")); //giống ở phía trên
            }
            for (int i = temp.length - 1; sentStack.size() < temp.length; i--) {
                sentStack.push(temp[i]); //push tất cả trở lại bên trong
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }



    public static boolean clearSentMess() {
        if (sentStack.clear() == true) {
            System.out.println("Delete all messages successfully!");
            return true;
        } else {
            System.out.println("Delete message failed!");
            return false;
        }
    }


}
