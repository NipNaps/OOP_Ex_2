package gym.management;

import gym.customers.Gender;
import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.List;

public class Instructor extends Person {
    private List<SessionType> qualification;
    private double salary;

    public Instructor(String name, double balance, Gender gender, String birthDate, double salary, List<SessionType> qualification) {
        super(name, balance, gender, birthDate);
        this.salary = salary;
        this.qualification = qualification;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<SessionType> getQualification() {
        return qualification;
    }
}
