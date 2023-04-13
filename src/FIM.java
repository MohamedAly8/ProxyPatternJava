import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FIM implements FIM_Interface {
  

    private String client_id;
    private String client_newPassword;

    public FIM (String client_id, String client_newPassword) {
         this.client_id = client_id;
         this.client_newPassword = client_newPassword;
    }
    public void change_password() {
        
        
        File remote_db = new File("Passwords.txt");
        File temp_db = new File("TempPasswords.txt");

        try {

        BufferedWriter writer = new BufferedWriter(new FileWriter(temp_db, false));
        writer.write("id, password");
        writer.newLine();

        
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
                String addrow = row_data[0] + "," + this.client_newPassword;
                writer.write(addrow);
                writer.newLine();
            }
            else {
                // Unchanged data row
                writer.write(row);
                writer.newLine();
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


        // delte Passwords.txt
        remote_db.delete();
        // rename TempPasswords.txt to Passwords.txt 
        temp_db.renameTo(remote_db);
        

    }


}