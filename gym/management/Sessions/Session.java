package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final SessionType type;
    private final String dateTime;
    private final ForumType forum;
    private final Instructor instructor;
    private final List<Client> participants;
    private final int maxCapacity;
    private final int price;

    // Constructor
    public Session(SessionType type, String dateTime, ForumType forum, Instructor instructor, int maxCapacity, int price) {
        if (maxCapacity <= 0) { // maxCapacity of a session must be greater than 0 for it to be created
            throw new IllegalArgumentException("maxCapacity must be greater than 0");
        } // Throwing an exception when trying to set a price that's lower than 0
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

    public SessionType getType() {
        return type;
    }

    // Get date + time of ssession
    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(this.dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    // Get date of session
    public LocalDate getDate() {
        return getDateTime().toLocalDate();
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

    public int getPrice() {
        return price;
    }

    public List<Client> getParticipants() {
        return participants;
    }

// Adding participants and making sure that we don't pass the max capacity for each one.
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
        return "Session Type: " + type +
                " | Date: " + dateTime +
                " | Forum: " + forum +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + participants.size() + "/" + maxCapacity;

    }
}
