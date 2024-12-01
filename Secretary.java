import java.util.ArrayList;

public class Secretary extends Person implements SalarySetterSec {
    private final ArrayList<Client> clientList = new ArrayList<>();

    public Secretary(String name, int balance, Gender gender, String birthDate) {
        super(name, balance, gender, birthDate);
    }

    @Override
    public void setSalary(int balance) {
        this.balance = balance;
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {

        for (Client existingClient : this.clientList) {
            if (existingClient.getName().equals(person.getName()) && existingClient.getAge() == person.getAge()) {
                throw new DuplicateClientException("Error: Registration is required before attempting to unregister");
            }
        }

        if (person.getAge() >= 18) {
            Client client = new Client(person);
            this.clientList.add(client);
            return client;
        } else {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!clientList.remove(client))
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
    }

    public Instructor hireInstructor(Person person, int hourSalary, ArrayList<SessionType> sessionList) {
        return new Instructor(person, hourSalary, sessionList);
    }

    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getSessionList().contains(sessionType)){
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");

        }
        return new Session(sessionType,date,forumType,instructor);
    }

}
