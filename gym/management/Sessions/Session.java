package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Subject;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session implements Subject {
    private SessionType type;
    private LocalDateTime dateTime;
    private ForumType forum;
    private Instructor instructor;
    private List<Client> participants;
    private int maxCapacity;
    private  double price;

    public Session(SessionType type, LocalDateTime dateTime, ForumType forum, Instructor instructor, int maxCapacity, double price) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("maxCapacity must be greater than 0");
        }
        if (price < 0) {
            throw new IllegalArgumentException("price must be greater than 0");
        }

        this.type = type;
        this.dateTime = LocalDateTime.parse(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.forum = forum;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.price = price;
        this.participants = new ArrayList<>();
    }
    @Override
    public void attach(Client client) {
        if(!participants.contains(client)) {
            participants.add(client);
        }
    }

    @Override
    public void detach(Client client) {
        participants.remove(client);
    }

    @Override
    public void notifyObservers(String message) {
        if(!participants.isEmpty()) {
            System.out.println("No participants to notify for session: " + type);
        }
        for(Client client : participants) {
            client.update(message);
        }
    }

    public SessionType getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ForumType getForum() {
        return forum;
    }

    public Instructor getInstructor() {
        return instructor;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public double getPrice() {
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
            System.out.println("Error: Participant is already registered for this session");
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
                " | Participants: " + participants.size() + "/" + maxCapacity +
                " | Price: " + price;

    }
}
