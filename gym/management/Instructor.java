package gym.management;
import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.List;

public class Instructor extends Person {
    private int hourSalary;
    private List<SessionType> sessionList;


    public Instructor(Person person, int hourSalary, List<SessionType> sessionList) {
        super(person);
        this.hourSalary = hourSalary;
        this.sessionList = sessionList;
    }

    public int getHourSalary() {
        return hourSalary;
    }

    public List<SessionType> getSessionList() {
        return sessionList;
    }

    public boolean isQualifiedFor(SessionType type) {
        return this.getSessionList().contains(type);
    }
}
