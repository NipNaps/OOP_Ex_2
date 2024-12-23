package gym.management;

import gym.customers.Person;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private double balance;

    private Gym() {
        this.balance = 0; // Default gym balance.
    }
    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Secretary getSecretary() {
        return secretary;
    }
    public void setSecretary(Person perosn, double salary) {
        this.secretary = new Secretary(perosn, salary);
    }
    public double getBalance() {
        return balance;
    }
    public void updateBalance(double amount) {
        this.balance += amount;
    }
    public String toString() {
        return "Gym Name " + name " | Gym Balance: " + balance + "\nSecretary: " + secretary;
    }
}
