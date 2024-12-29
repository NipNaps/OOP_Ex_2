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
    private  double balance;
    private final List<Client> clients;
    private final List<Instructor> instructors;
    private final List<Session> sessions;

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
        if (secretary == null) {
            throw new IllegalArgumentException("Error: secretary has not been set");
        }
        return secretary;
    }

    public void setSecretary(Person person, int salary) {
        this.secretary = new Secretary(person, salary);
        Secretary.logAction("A new secretary has started working at the gym: " + secretary.getName());
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
        balance += amount;
    }

    public String toString() {
        StringBuilder clientsData = new StringBuilder();
        for (Client client : getClients()) {
            clientsData.append(client).append("\n");
        }
        StringBuilder employeesData = new StringBuilder();

        for (Instructor instructor : getInstructors()) {
            employeesData.append(instructor).append("\n");
        }
        employeesData.append(getSecretary()).append("\n");

        StringBuilder sessionsData = new StringBuilder();
        for (Session session : getSessions()) {
            sessionsData.append(session).append("\n");
        }
        return "Gym Name: " + name + "\n" +
                "Gym Secretary: " + secretary + "\n" +
                "Gym Balance: " + (int)balance + "\n\n" +
                "Clients Data:\n" + clientsData.toString().trim() + "\n\n" +
                "Employees Data:\n" + employeesData.toString().trim() + "\n\n" +
                "Sessions Data:\n" + sessionsData.toString().trim();
    }
}
