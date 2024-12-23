package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private SessionType type;
    private LocalDateTime dateTime;
    private ForumType forum;
    private Instructor instructor;
    private List<Client> participants;
    private int maxCapacity;
    private  double price;

    public Session(SessionType type, LocalDateTime dateTime, ForumType forum, Instructor instructor, int maxCapacity, double price) {

        this.type = type;
        this.dateTime = LocalDateTime.parse(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.forum = forum;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.price = price;
        this.participants = new ArrayList<>();
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
}
