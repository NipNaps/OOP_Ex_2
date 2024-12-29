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
// using this method to calculate the payment for the instructor
    public void addSession() {
        sessionsNum++;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public void paySalary() {
        int curBalance = this.balance + this.getSalary();
        for (Person p: people) {
            if (this.getName().equals(p.getName())) {
                p.setBalance(curBalance);
            }
        }
    }

    public int getSalary() {
        return salaryPerHour * sessionsNum;
    }
// Method that gets what type of sessions the instructor is qualified to do
    public List<SessionType> getQualification() {
        return qualifications;
    }
// Checking if the instructor is qualified
    public boolean isCertified(SessionType sessionType) {
        return qualifications.contains(sessionType);
    }
// ssss
    public String toString() {
        String certifications = String.join(", ", getQualification().stream().map(Enum::name).toList());
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthdate() +
                " | Age: " + getAge() +
                " | Balance: " + getBalance() +
                " | Role: Instructor" +
                " | Salary per Hour: " + getSalaryPerHour() +
                " | Certified Classes: " + certifications;

    }
}
