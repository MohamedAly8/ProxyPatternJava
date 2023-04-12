
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; // Import the Scanner class to read text files

public class FIM_Proxy implements FIM_Interface {

    private Client client;
    private String client_id;
    private String client_newPassword;
    private boolean is_valid_password;
    private FIM FIM;
    private String Date1;
    private String Date2;


    public FIM_Proxy(Client client) {
        this.client = client;
        this.client_id = this.client.getId();
        this.client_newPassword = client.get_newPassword();
        this.is_valid_password = true;
        this.FIM = new FIM(this.client);
    }
    

    private boolean verify_password()  {

        File local_record = new File("./resources/Local_Record.txt");
        try {

        Scanner sc = new Scanner(local_record);

        sc.nextLine();

        // scan the local password file for verification of new password
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            String[] row_data = row.split(",");

            // if the clientID matches and the password has already been used
            if (row_data[0].equals(this.client_id) && row_data[1].equals(this.client_newPassword)){
                this.is_valid_password = false;
                // save the dates where the password was used before
                Date1 = row_data[2]; Date2 = row_data[3];
                break;
            }
        }
        sc.close();
    }
    catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
        return this.is_valid_password;
    }


    public void change_password() {

        if (verify_password()){  
            // proxy passes on change_password job to FIM after verifying the new passwords is good
            this.FIM.change_password();

            // update the client that the password has been successfully changed
            update_client(true);

            return;
        }

        update_client(false);   
    }


    private void update_client(boolean status) {
        if(status){
            this.client.notify_client("Your Request has been Approved. Password changed successfully");
        }
        else {
            this.client.notify_client("Error: Password has already been used between " + Date1 + " and " + Date2);
        }
    }
    

}