package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDateTime;

public class PilatesSession  extends Session {
    public PilatesSession(String dateTime, ForumType forum, Instructor instructor, int maxCapacity, int price) {
        super(SessionType.Pilates, dateTime, forum, instructor, maxCapacity, price);
    }
    public String getSessionType() {
        return "Pilates";
    }
}

