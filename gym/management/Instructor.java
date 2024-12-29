package gym.management;

import gym.customers.Gender;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.List;

public class Instructor extends Person {
    private final List<SessionType> qualifications;
    private int sessionsNum = 0;
    private int salaryPerHour;

    public Instructor(Person person, int salary, List<SessionType> qualifications) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate());
        this.setId(person.getId());
        this.salaryPerHour = salary;
        this.qualifications = qualifications;
    }

    public void addSession() {
        sessionsNum++;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public void paySalary() {
        for (Person p: people) {
            if (this.getName().equals(p.getName())) {
                p.setBalance(p.getBalance() + this.getSalary());
            }
        }
    }

    public int getSalary() {
        return salaryPerHour * sessionsNum;
    }

    public List<SessionType> getQualification() {
        return qualifications;
    }

    public boolean isCertified(SessionType sessionType) {
        return qualifications.contains(sessionType);
    }

    public String toString() {
        String certifications = String.join(", ", getQualification().stream().map(Enum::name).toList());
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthdate() +
                " | Age: " + getAge() +
                " | Balance : " + getBalance() +
                " | Role: Instructor" +
                " | Salary per Hour: " + getSalaryPerHour() +
                " | Certified Classes: " + certifications;

    }
}
