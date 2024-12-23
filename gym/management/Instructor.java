package gym.management;

import gym.customers.Person;
import gym.management.Sessions.SessionType;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private double salaryPerHour;
    private List<SessionType> certifiedClasses;

    public Instructor(Person person, double salaryPerHour, List<SessionType> certifiedClasses) {
        super(person.getName(), person.getBalance(), person.getGender(),person.getBirthdate());
        this.salaryPerHour = salaryPerHour;
        this.certifiedClasses = new ArrayList<>() certifiedClasses;
    }
}
