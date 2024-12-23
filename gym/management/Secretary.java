package gym.management;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import  gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.List;

    public class Secretary extends Person {
        private double salary;
        private List<String> actionHistroy;
        private List<Client> clients;
        private List<Instructor> instructors;
        private List<Session> sessions;

        public Secretary(Person person, double salary) {
            super(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate());
            this.salary = salary;
            this.actionHistroy = new ArrayList<>();
            this.clients = new ArrayList<>();
            this.instructors = new ArrayList<>();
            this.sessions = new ArrayList<>();
        }
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
        public void unregisterClient(Client client) throws ClientNotRegisteredException {
            if (!clients.contains(client)) {
                throw new ClientNotRegisteredException("Error: Client is not registered");
            }
            clients.remove(client);
            logAction("Unregistered client: " + client.getName());
        }
        public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor)
        throws InstructorNotQualifiedException {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");

        }
        Session session = new Session(sessionType, date, forum, instructor, 0, 0);

        public double getSalary() {
            return salary;
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

