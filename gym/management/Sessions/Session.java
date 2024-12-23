package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Session {
    private SessionType type;
    private LocalDateTime dateTime;
    private ForumType forum;
    private Instructor instructor;

    public Session(SessionType type, LocalDateTime dateTime, ForumType forum, Instructor instructor) {
        this.type = type;
        this.dateTime = dateTime;
        this.forum = forum;
        this.instructor = instructor;
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
}
