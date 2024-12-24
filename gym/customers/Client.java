package gym.customers;

import java.util.ArrayList;
import java.util.List;


public class Client extends Person {
    private List<String> notifications;

    public Client(String name, double balance, Gender gender, String birthdate) {
        super(name, balance, gender, birthdate);
        this.notifications = new ArrayList<>();

    }
    public void addNotification(String message) {
        notifications.add(message);
    }
    public List<String> getNotifications() {
        return notifications;
    }
    public String toString() {
        return super.toString() + "\nNotifications: " + notifications;
    }
}
