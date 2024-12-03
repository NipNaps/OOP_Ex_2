package gym.management;
import gym.customers.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRegistry {
    private static ClientRegistry instance;
    private List<Client> clients;

    private ClientRegistry() {
        clients = new ArrayList<>();
    }

    public static ClientRegistry getInstance() {
        if (instance == null) {
            instance = new ClientRegistry();
        }
        return instance;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public boolean isClientRegistered(Person person) {
        return clients.stream().anyMatch(client -> client.getPerson().equals(person));
    }

    public boolean isClientRegistered(Client client) {
        return clients.contains(client);
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clients);
    }
}
