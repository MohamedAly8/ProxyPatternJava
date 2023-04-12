public class Main {

    public static void main(String[] args) {
        
        // Client 1
        String classpath = System.getProperty("java.class.path");
        System.out.println(classpath);


         Client client1 = new Client("0001", "password");

         FIM_Proxy proxy1 = new FIM_Proxy(client1);

         // this SHOULD verify and then internally tell FIM to change the password AND PROVIDE STATUS MESSAGE
         proxy1.change_password();
        

    
    }
    
}
