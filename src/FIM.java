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

    }

}