import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FIM implements FIM_Interface {
  
    private Client client;
    private String client_id;
    private String client_newPassword;

    public FIM (Client client) {
        this.client = client;
        this.client_id = this.client.getId();
        this.client_newPassword = client.get_newPassword();
        // constructor 
    }

    public void change_password() {
        // change password
        System.out.println("hello, you have reached FIM");

        File remote_db = new File("./resources/Passwords.txt");
        File temp_db = new File("./resources/TempPasswords.txt");

        try {

        BufferedWriter writer = new BufferedWriter(new FileWriter(temp_db, true));
        writer.write("id, password");
        
        // scan file with scanner 
        Scanner sc = new Scanner(remote_db);
        sc.nextLine();

        // scan the remote password file to update client password
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            String[] row_data = row.split(",");

            // Found Client
            if (row_data[0].equals(this.client_id)){
                // Update their data row
                writer.write(row_data[0] + this.client_newPassword);
            }
            else {
                // Unchanged data row
                writer.write(row);
            }

        }
        sc.close();
        writer.close();
    }
    catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }

        remote_db.delete();
        temp_db.renameTo(remote_db);
    }

}