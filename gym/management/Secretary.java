package gym.management;
import gym.Gym;
import gym.customers.*;
import gym.Exception.*;
import gym.management.Sessions.*;

import java.util.*;

public class Secretary extends Person implements Manageable {
    private final HashMap<Client , Stack<String>> messageMap = new HashMap<>(); // maybe use registry and factory instead?
    private final Queue<String> actionPrints = new LinkedList<>();

    public Secretary(String name, int balance, Gender gender, String birthDate) {
        super(name, balance, gender, birthDate);

    }

    public Queue<String> getActionPrints() {
        return actionPrints;
    }

    @Override
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (!isCurrentSecretary())
            return null;

        Client newClient = ClientFactory.createClient(person);
        ClientRegistry.getInstance().addClient(newClient);
        actionPrints.add("Registered new client: " + person.getName());

        return newClient;
    }

    @Override
    public void unregisterClient(Client client) throws ClientNotRegisteredException { // need to remove from every session and so as well.
        if (!isCurrentSecretary())
            return;

        if (!ClientRegistry.getInstance().isClientRegistered(client))
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");

        ClientRegistry.getInstance().removeClient(client);
        this.actionPrints.add("Unregistered client: " + client.getName());
    }

    @Override
    public Instructor hireInstructor(Person person, int hourSalary, ArrayList<SessionType> sessionList) {
        if (!isCurrentSecretary())
            return null;

        Instructor instructor = InstructorFactory.createInstructor(person, hourSalary, sessionList);
        if (!InstructorRegistry.getInstance().isInstructorRegistered(instructor)) {
            InstructorRegistry.getInstance().addInstructor(instructor);
            actionPrints.add("Hired new instructor: " + person.getName() + " with salary per hour: " + hourSalary);
            return instructor;
        }

        return  null;
    }

    @Override
    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!isCurrentSecretary())
            return null;

        Session session = SessionFactory.createSession(sessionType, date, forumType, instructor);
        if (!SessionRegistry.getInstance().isSessionRegistered(session)) {
            SessionRegistry.getInstance().addSession(session);
            actionPrints.add("Created new session: " + sessionType + " on " + date + " with instructor: " + instructor);
            return session;
        }

        return null;
    }

    @Override
    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException, NullPointerException {
        if (!isCurrentSecretary())
            throw new NullPointerException();

        if (!this.sessionMap.containsKey(s1)) {
            return;
        }

        if (this.sessionMap.get(s1).contains(c1)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
        if (!this.clientList.contains(c1)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }

        boolean senior = c1.isSenior();
        Gender clientGender = c1.getGender();
        boolean hasPast = s1.hasPast();
        int sessionCapacity = s1.getSessionType().getCapacity();
        boolean sessionIsFull = this.sessionMap.get(s1).size() >= sessionCapacity;
        ForumType forumType = s1.getForumType();
        int sessionPrice = s1.getSessionType().getPrice();
        boolean canPay = c1.getBalance() >= sessionPrice;

        if (!hasPast && !sessionIsFull && canPay) {
            switch (forumType) {
                case All -> this.sessionMap.get(s1).add(c1);

                case Male -> {
                    if (clientGender.equals(Gender.Male))
                        this.sessionMap.get(s1).add(c1);
                }
                case Female -> {
                    if (clientGender.equals(Gender.Female))
                        this.sessionMap.get(s1).add(c1);
                }
                case Seniors -> {
                    if (senior) {
                        this.sessionMap.get(s1).add(c1);
                    }
                }
            }
        }

        c1.subtractFromBalance(sessionPrice);
        Gym gym = Gym.getInstance();
        gym.addToGymBalance(sessionPrice);
    }

    public void unRegisterClientToLesson(Client c1, Session s1) {

    }

    public void notify(Session s1, String message) {
        for (Client client : this.sessionMap.get(s1)) {
            this.messageMap.get(client).push(message);
        }
    }

    public void notify(String date, String message) {

        for (Session session : this.sessionMap.keySet()) {
            if (session.getDate().substring(0, 9).equals(date)) {
                for (Client client : this.sessionMap.get(session)) {
                    this.messageMap.get(client).push(message);
                }
            }
        }
    }

    public void notify(String message) {
        for (Client client : this.messageMap.keySet())
            this.messageMap.get(client).push(message);
    }

    public void paySalaries() {
        Gym gym = Gym.getInstance();
        this.balance += gym.getSecretarySalary();
        gym.subtractFromGymBalance(gym.getSecretarySalary());

        for (Instructor instructor : this.instructorsList) {
            instructor.balance += instructor.getHourSalary();
            gym.subtractFromGymBalance(instructor.getHourSalary());
        }
    }

    public void printActions() {

    }

    private boolean isCurrentSecretary() {
        return Gym.getInstance().getSecretary().equals(this);
    }
}