package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Subject;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session implements Subject {
    private SessionType type;
    private String dateTime;
    private final ForumType forum;
    private Instructor instructor;
    private List<Client> participants;
    private final int maxCapacity;
    private final int price;

    public Session(SessionType type, String dateTime, ForumType forum, Instructor instructor, int maxCapacity, int price) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("maxCapacity must be greater than 0");
        }
        if (price < 0) {
            throw new IllegalArgumentException("price must be greater than 0");
        }

        this.type = type;
        this.dateTime = dateTime;
        this.forum = forum;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.price = price;
        this.participants = new ArrayList<>();
    }

    @Override
    public void attach(Client client) {
        if (!participants.contains(client)) {
            participants.add(client);
        }
    }

    @Override
    public void detach(Client client) {
        participants.remove(client);
    }

    @Override
    public void notifyObservers(String message) {
        if (!participants.isEmpty()) {
            System.out.println("No participants to notify for session: " + type);
        }
        for (Client client : participants) {
            client.update(message);
        }
    }

    public SessionType getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(this.dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public LocalDate getDate() {
        return getDateTime().toLocalDate();
    }

//    public boolean sessionInDate(String date, Client client) {
//        boolean result = false;
//    }

    public ForumType getForum() {
        return forum;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getPrice() {
        return price;
    }

    public List<Client> getParticipants() {
        return participants;
    }

    public boolean addParticipant(Client client) {
        if (participants.size() >= maxCapacity) {
            System.out.println("Error: No avaiable spots for this session");
            return false;
        }
        if (participants.contains(client)) {
            return false;
        }
        participants.add(client);
        return true;
    }

    public String toString() {
        return "Session type: " + type +
                " | Date: " + dateTime +
                " | Forum: " + forum +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + participants.size() + "/" + maxCapacity;

    }
}
