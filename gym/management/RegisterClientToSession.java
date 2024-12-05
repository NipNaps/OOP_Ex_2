package gym.management;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Gym;
import gym.customers.Client;
import gym.customers.Gender;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegisterClientToSession {
    private static RegisterClientToSession instance;
    private final Map<Session, Set<Client>> clientToSessionListMap;

    private RegisterClientToSession() {
        this.clientToSessionListMap = new HashMap<>();
    }

    public static RegisterClientToSession getInstance() {
        if (instance == null) {
            instance = new RegisterClientToSession();
        }
        return instance;
    }

    public Map<Session, Set<Client>> getClientListMap() {
        return clientToSessionListMap;
    }

    public void addToMap(Session s1, Client c1) {

        if (isClientRegisteredToSession(c1, s1)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
        if (!ClientRegistry.getInstance().isClientRegistered(c1))
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");

       if (!s1.hasPast() && !isFull(s1) && canPay(c1, s1) && isForumMatched(c1, s1.getForumType())) {
            c1.getPerson().subtractFromBalance(s1.getSessionType().getPrice());
            Gym.getInstance().addToGymBalance(s1.getSessionType().getPrice());
            this.clientToSessionListMap.get(s1).add(c1);
        }
    }

    private boolean isClientRegisteredToSession(Client c1, Session s1) {
        return this.clientToSessionListMap.get(s1).contains(c1);

    }

    private boolean isForumMatched(Client c1, ForumType forumType) {
        switch (forumType) {
            case All -> {
                return true;
            }

            case Male -> {
                if (c1.getPerson().getGender().equals(Gender.Male))
                    return true;
            }
            case Female -> {
                if (c1.getPerson().getGender().equals(Gender.Female))
                    return true;
            }
            case Seniors -> {
                if (c1.getPerson().isSenior()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFull(Session s1) {
        return this.clientToSessionListMap.get(s1).size() >= s1.getSessionType().getCapacity();
    }

    private boolean canPay(Client c1, Session s1) {
        return c1.getPerson().getBalance() >= s1.getSessionType().getPrice();
    }


}








