import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        // Client 1
         Client client1 = new Client("0001", "password123");


         FIM_Proxy proxy1 = new FIM_Proxy(client1);


         // Here, proxy verify newPassword and then if verified,
        // internally tell FIM to change the password if AND PROVIDE STATUS MESSAGE to client
         proxy1.change_password();
    }
    
}
