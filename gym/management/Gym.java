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

    public void setName(String name) {
        this.name = name;
    }
    public Secretary getSecretary() {
        return secretary;
    }
    public void setSecretary(Person person, double salary) {
        this.secretary = new Secretary(person, salary);
        this.secretary.logAction("A new secretary has started working at the gym: " + secretary.getName());
    }
    public List<Client> getClients() {
        return clients;
    }
    public List<Instructor> getInstructors() {
        return instructors;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }
    public String toString() {
        String clientsData = "";
        for (Client client : getClients()) {
            clientsData += client.toString() + "\n";
        }
        String employeesData = "";
        employeesData += getSecretary() + "\n";
        for (Instructor instructor : getInstructors()) {
            employeesData += instructor.toString() + "\n";
        }
        String sessionsData = "";
        for (Session session : getSessions()) {
            sessionsData += session.toString() + "\n";
        }
        return "Gym Name " + name + "\n" +
                "Gym Secretary " + secretary + "\n" +
                "  Gym Balance: " + balance + "\n\n" +
                "Clients: Data" +  clientsData.trim() + "\n" +
                "Sessions Data" + sessionsData.trim();
    }
}
