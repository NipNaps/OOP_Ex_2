package gym.management;

import gym.customers.Gender;
import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.List;

public class Instructor extends Person {
    private List<SessionType> qualifications;
    private int salary;

    public Instructor(String name, int balance, Gender gender, String birthDate, int salary, List<SessionType> qualifications) {
        super(name, balance, gender, birthDate);
        this.salary = salary;
        this.qualifications = qualifications;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
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

    public String toString() {
        String certifications = String.join(", ", getQualification().stream().map(Enum::name).toList());
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthdate() +
                " | Age: " + getAge() +
                " | Balance : " + getBalance() +
                " | Role: Instructor" +
                " | Salary per Hour: " + getSalary() +
                " | Certified Classes: " + certifications;

    }
}
