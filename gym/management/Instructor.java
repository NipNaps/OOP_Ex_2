package gym.management;

import gym.customers.Gender;
import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.List;

public class Instructor extends Person {
    private List<SessionType> qualifications;
    private double salary;

    public Instructor(String name, double balance, Gender gender, String birthDate, double salary, List<SessionType> qualifications) {
        super(name, balance, gender, birthDate);
        this.salary = salary;
        this.qualifications = qualifications;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<SessionType> getQualification() {
        return qualifications;
    }

    // Add a new qualification
    public void addQualification(SessionType qualification) {
        if (!qualifications.contains(qualification)) {
            qualifications.add(qualification);
        }
    }

    // Remove a qualification
    public void removeQualification(SessionType qualification) {
        qualifications.remove(qualification);
    }

    public boolean isCertified(SessionType sessionType) {
        return qualifications.contains(sessionType);
    }
}
