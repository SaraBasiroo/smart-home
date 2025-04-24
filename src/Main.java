import java.util.*;
import classes.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CentralSystem controller = new CentralSystem();
        System.out.println("Enter num + commands : \n");
        int q = sc.nextInt();
        sc.nextLine();
        System.out.println();
        for (int i = 0; i < q; i++) {
            String temp = sc.nextLine();
            String[] input = temp.split(" ");
            String command = input[0];

            switch (command) {
                case "add_device" -> System.out.println(controller.addDevice(input[1], input[2], input[3]));
                case "set_device" -> System.out.println(controller.setDevice(input[1], input[2], input[3]));
                case "remove_device" -> System.out.println(controller.removeDevice(input[1]));
                case "list_devices" -> System.out.println(controller.listDevices());
                case "add_rule" -> System.out.println(controller.addRule(input[1], input[2], input[3]));
                case "check_rules" -> System.out.println(controller.checkRules(input[1]));
                case "list_rules" -> System.out.println(controller.listRules());
            }
        }
        sc.nextLine();
        sc.close();
    }
}
