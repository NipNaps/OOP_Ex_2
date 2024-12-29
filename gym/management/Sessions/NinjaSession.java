package gym.management.Sessions;

import gym.management.Instructor;

public class NinjaSession extends Session {
    public NinjaSession(String dateTime, ForumType forum, Instructor instructor, int maxCapacity, int price) {
        super(SessionType.Ninja, dateTime, forum, instructor, maxCapacity, price);
    }
    public String getSessionType() {
        return "Ninja";
    }
}
