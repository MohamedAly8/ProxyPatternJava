import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        // Client 1 has attributes id and newPassword when making a request to FIM
         Client client1 = new Client("0001", "newpassword123");

        // FIM_Proxy is the proxy that will verify the password
        FIM_Proxy proxy1 = new FIM_Proxy(client1);

        // Here, proxy verify newPassword and then if verified,
        // if password is valid, FIM will change the password and 
        // Client will be updated of password change status (Error with Date1&Date2 or Success)
        // the Passwords.txt will update if Sucess
         proxy1.change_password();
    }
    
}
