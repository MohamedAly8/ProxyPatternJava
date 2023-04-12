
import java.io.InputStream;
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
    

    private boolean verify_password(){
        // use scanner to scan fiel Local_Record.txt line by line 
        // if client_id is found, check if client_password is the same as the password in the file
        // get txt file from resources using InputSteam
        InputStream local_record = Main.class.getClassLoader().getResourceAsStream("./Local_Record.txt");


 
        Scanner sc = new Scanner(local_record);


        sc.nextLine();
        while (sc.hasNextLine()) {

            String row = sc.nextLine();
            String[] row_data = row.split(",");

            // if the clientID matches and the password has already been used 
            if (row_data[0].equals(this.client_id) && row_data[1].equals(this.client_newPassword)) {
                this.is_valid_password = false;
                // save the dates where the password was used before
                Date1 = row_data[2]; Date2 = row_data[3];
                break;
            }
        }
    
        sc.close();
    
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


    private String update_client(boolean status) {
        if(status){
            return this.client.notify_client("Password changed successfully");
        }
        else {
            return this.client.notify_client("Error: Password has already been used between" + Date1 + " and " + Date2);
        }
    }
    

}