package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Gender;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Secretary extends Person {
    private int salary;
    private static List<String> actionHistory = new ArrayList<>();
    private List<Client> clients;
    private List<Instructor> instructors;
    private List<Session> sessions;
    private List<Client> observers;

    // Constructor
    public Secretary(Person person, int salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate());
        this.setId(person.getId());
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    // Method for client registration the gym
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (person.getAge() < 18) {
            throw new InvalidAgeException("Error: Client age must be at least 18 years old to register");
        }
        for (Client client : Gym.getInstance().getClients()) {
            if (client.getName().equals(person.getName()) && client.getBirthdate().equals(person.getBirthdate())) {
                throw new DuplicateClientException("Error: The client is already registered");
            }
        }
        Client client = new Client(person);
        Gym.getInstance().getClients().add(client);
        logAction("Registered new client: " + client.getName());
        return client;

    }

    // Method that removes client from the gym
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!Gym.getInstance().getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        Gym.getInstance().getClients().remove(client);
        logAction("Unregistered client: " + client.getName());
    }

    // Method that add instructors to the gym
    public Instructor hireInstructor(Person person, int salaryPerHour, List<SessionType> certifications) {
        Instructor instructor = new Instructor(person, salaryPerHour, certifications);
        Gym.getInstance().getInstructors().add(instructor);
        logAction("Hired new instructor: " + instructor.getName() + " with salary per hour: " + salaryPerHour);
        return instructor;
    }

    // Method that add sessions to the gym
    public Session addSession(SessionType sessionType, String date, ForumType forum, Instructor instructor)
            throws InstructorNotQualifiedException {
        if (!instructor.isCertified(sessionType)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }
        int maxCapacity = 0;
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
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        Session session = new Session(
                sessionType,
                date,
                forum,
                instructor,
                maxCapacity,
                price
        );
        Gym.getInstance().getSessions().add(session);
        logAction("Created new session: " + sessionType + " on " + formattedDate + " with instructor: " + instructor.getName());
        return session;
    }

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException {

        if (!Gym.getInstance().getClients().contains(client)) { throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons"); }

        else if (session.getDateTime().isBefore(LocalDateTime.now()) || session.getDateTime().isEqual(LocalDateTime.now())) {
            logAction("Failed registration: Session is not in the future");

        } else if (session.getParticipants().size() >= session.getMaxCapacity()) {
            logAction("Failed registration: No available spots for session");

        } else if (client.getBalance() < session.getPrice()) {
            logAction("Failed registration: Client doesn't have enough balance");

        } else if (session.getForum().equals(ForumType.Male) && (client.getGender() != Gender.Male)) {
                logAction("Failed registration: Client's gender doesn't match the session's gender requirements");

        } else if (session.getForum().equals(ForumType.Female) && (client.getGender() != Gender.Female)) {
                logAction("Failed registration: Client's gender doesn't match the session's gender requirements");

        } else if (session.getForum().equals(ForumType.Seniors) && (client.getAge() < 65)) {
            logAction("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
        } else {
            if (!session.addParticipant(client)) { throw new DuplicateClientException("Error: The client is already registered for this lesson"); }
            logAction("Registered new client: " + client.getName() + " to session: " + session);
        }

// test test
    }

    public void notify(String message) {
        for (Client client : Gym.getInstance().getClients()) {
            client.update(message);
        }
        logAction("A message was sent to all gym clients: " + message);
    }

    public void notify(String date, String message) {
        for (Client client : Gym.getInstance().getClients()) {
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
        double totalInstructorSalaries =  Gym.getInstance().getInstructors().stream().mapToDouble(Instructor::getSalary).sum();
         double totalSalaries = totalInstructorSalaries + getSalary();
         Gym.getInstance().updateBalance(-totalSalaries);



        Gym.getInstance().updateBalance(-totalSalaries);
        logAction("Salaries have been paid to all employees");
    }

    public static void logAction(String action) {
        actionHistory.add(action);
    }

    public void printActions() {
        for (String action : actionHistory) {
            System.out.println(action);
        }
    }

    public String toString() {
        return super.toString() + " | Role: Secretary | Salary per month: " + salary;
    }
}

