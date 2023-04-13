
public class Client {
    // a client had an id and a password 
    private String id;
    private String newPassword;

    public Client(String id, String newPassword) {
        this.id = id;
        this.newPassword = newPassword;
    }

    // make getters and setters for id and password  
    public String getId() {
        return this.id;
    }

    public String get_newPassword() {
        return this.newPassword;
    }

    // notify function 
    public void notify_client(String message) {
        System.out.println("Client Notified:");
        System.out.println(message);
    }

}
