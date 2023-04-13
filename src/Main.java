import java.io.FileNotFoundException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        

        
        System.out.println("Welcome to the FIM System");


        while(true){
        
            // open scanner 
        Scanner sc = new Scanner(System.in);

        System.out.println("IDs in System: 0001, 0002, 0003, 0004, 0005, 0006");
        System.out.println("Please enter your ID: ");
        String id = sc.nextLine();
        System.out.println("Please enter your desired new password: ");
        String newPassword = sc.nextLine();
        

        Client client2 = new Client(id, newPassword);
        FIM_Proxy proxy2 = new FIM_Proxy(client2);
        proxy2.change_password();

        System.out.println("Enter (1) to Continue or any other character to Exit!");

        if (!sc.nextLine().equals("1")) {
            System.out.println("Good bye!");
            sc.close();
            break;
        }

            System.out.println("\n \n \n \n");

        
    }
}   
    
}
