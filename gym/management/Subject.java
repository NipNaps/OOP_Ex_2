package gym.management;

import gym.customers.Client;

public interface Subject {
    void attach(Client client);
    void detach(Client client);
    void notifyObservers(String message);
}
