package gym.customers;

import gym.management.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person implements Observer {

    private final List<String> notifications;
    private final List<LocalDate> sessionsDate = new ArrayList<>();

    // Constructor
    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate());
        this.setId(person.getId());
        this.notifications = new ArrayList<>();
    }

    //Method that add the dates of the registered sessions
    public void addSessionDate(LocalDate date) {
        sessionsDate.add(date);
    }

    //Method that returns the dates of the registered sessions
    public List<LocalDate> getSessionsDate() {
        return sessionsDate;
    }

    //upadte the notifications of that client
    public void update(String message) {
        notifications.add(message);
    }

    // Client using observer to receive messages.
    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthdate() +
                " | Age: " + getAge() +
                " | Balance: " + getBalance();
    }

}
