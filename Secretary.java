import java.util.*;

public class Secretary extends Person implements Manageable {
    private final ArrayList<Client> clientList = new ArrayList<>();
    private final ArrayList<Instructor> instructorsList = new ArrayList<>();
    private final HashMap<Session, ArrayList<Client>> sessionMap = new HashMap<>();
    private final HashMap<Client , Stack<String>> messageMap = new HashMap<>();
    private final Queue<String> actionPrints = new LinkedList<>();

    public Secretary(String name, int balance, Gender gender, String birthDate) {
        super(name, balance, gender, birthDate);

    }

    public Queue<String> getActionPrints() {
        return actionPrints;
    }

    @Override
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {

        for (Client existingClient : this.clientList) {
            if (existingClient.getName().equals(person.getName()) && existingClient.getBirthDate().equals(person.getBirthDate())) {
                throw new DuplicateClientException("Error: Registration is required before attempting to unregister");
            }
        }

        if (person.isAboveEightTeen()) {
            Client client = new Client(person);
            this.clientList.add(client);
            this.messageMap.put(client, new Stack<>());
            return client;
        } else {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
    }

    @Override
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!this.clientList.remove(client))
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
    }

    @Override
    public Instructor hireInstructor(Person person, int hourSalary, ArrayList<SessionType> sessionList) {
        Instructor potential = new Instructor(person, hourSalary, sessionList);
        instructorsList.add(potential);
        return potential;
    }

    @Override
    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getSessionList().contains(sessionType)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");

        }

        boolean sessionNotRegistered = true;
        for (Session session : this.sessionMap.keySet()) {
            if (session.getSessionType().equals(sessionType) && session.getDate().equals(date) && session.getForumType().equals(forumType) && session.getInstructor().equals(instructor)) {
                sessionNotRegistered = false;
                break;
            }
        }

        Session session = null;
        if (sessionNotRegistered && this.instructorsList.contains(instructor)) {
             session = new Session(sessionType, date, forumType, instructor);
            this.sessionMap.put(session, new ArrayList<>());
        }

        return session;
    }

    @Override
    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException {
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



}