package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private double balance;
    private List<Client> clients;
    private List <Instructor> instructors;
    private List<Session> sessions;

    private Gym() {
        this.clients = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.balance = 0; // Default gym balance.
    }
    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Secretary getSecretary() {
        return secretary;
    }
    public void setSecretary(Person perosn, double salary) {
        this.secretary = new Secretary(perosn, salary);
    }
    public double getBalance() {
        return balance;
    }
    public void updateBalance(double amount) {
        this.balance += amount;
    }
    public String toString() {
        return "Gym Name " + name + "\n" +
                "  Gym Balance: " + balance + "\n" +
                "Clients: Data" +  clients + "\n" +
                "Sessions Data" + sessions;
    }
}
