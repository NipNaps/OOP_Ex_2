package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Secretary extends Person {
    private double salary;
    private List<String> actionHistroy;
    private List<Client> clients;
    private List<Instructor> instructors;
    private List<Session> sessions;
    private List<Client> observers;

    // Constructor
    public Secretary(Person person, double salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate());
        this.salary = salary;
        this.actionHistroy = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }

    // Method for client registration the gym
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (person.getAge() < 18) {
            throw new InvalidAgeException("Error: Client age must be at least 18 years old");
        }
        for (Client client : clients) {
            if (client.getName().equals(person.getName())) {
                throw new DuplicateClientException("Error: Client is already registered");
            }
        }
        Client client = new Client(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate());
        clients.add(client);
        logAction("Registered new client: " + client.getName());
        return client;

    }

    // Method that removes client from the gym
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!clients.contains(client)) {
            throw new ClientNotRegisteredException("Error: Client is not registered");
        }
        clients.remove(client);
        logAction("Unregistered client: " + client.getName());
    }

    // Method that add instructors to the gym
    public Instructor hireInstructor(Person person, double salaryPerHour, List<SessionType> certifications) {
        Instructor instructor = new Instructor(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate(), salaryPerHour, certifications);
        instructors.add(instructor);
        logAction("Hired new instructor: " + instructor.getName() + " with salary per hour: " + salaryPerHour);
        return instructor;
    }

    // Method that add sessions to the gym
    public Session addSession(SessionType sessionType, String date, ForumType forum, Instructor instructor)
            throws InstructorNotQualifiedException {
        if (!instructor.isCertified(sessionType)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }
        int maxCapacity;
        double price;
        switch (sessionType) {
            case Pilates:
                maxCapacity = 30;
                price = 60;
                break;
            case MachinePilates:
                maxCapacity = 10;
                price = 80;
                break;
            case ThaiBoxing:
                maxCapacity = 20;
                price = 100;
                break;
            case Ninja:
                maxCapacity = 5;
                price = 150;
                break;
            default:
                throw new IllegalArgumentException("Error: Unknown session type");
        }
        Session session = new Session(
                sessionType, LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                forum,
                instructor,
                0,
                price
        );
        sessions.add(session);
        logAction("Created new session: " + sessionType + " on " + date + " with instructor " + instructor.getName());
        return session;
    }

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException {
        if (!clients.contains(client)) {
            throw new ClientNotRegisteredException("Error: Client is not registered");
        }
        if (!session.addParticipant(client)) {
            throw new DuplicateClientException("Error: Client is already registered");
        }
        logAction("Registered new client: " + client.getName() + " to session: " + session);

    }

    public void notify(String message) {
        for (Client client : clients) {
            client.update(message);
        }
        logAction("A message was sent to all gym clients: " + message);
    }

    public void notify(String date, String message) {
        for (Client client : clients) {
            client.update("[" + date + "] " + message);
        }
        logAction("Notified all client with date : [" + date + "] " + message);
    }
    public void notify(Session session, String message) {
        for (Client client : session.getParticipants()) {
            client.update(message);
        }
        logAction("A message was sent to participants of session: " + session.getType() + " - " + message);
    }


    public void paySalaries() {
        double totalSalary = 0;
        for (Instructor instructor : instructors) {
            totalSalary += instructor.getSalaryPerHour();
        }
        Gym.getInstance().updateBalance(-totalSalary);
        logAction("Salaries have been paid to all employees");
    }

    public void logAction(String action) {
        actionHistroy.add(action);
    }

    public void printActions() {
        for (String action : actionHistroy) {
            System.out.println(action);
        }
    }

    public String toString() {
        return super.toString() + " | Role: Secretary | Salary per month: " + salary;
    }
}

