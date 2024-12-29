package gym.customers;

import gym.management.Gym;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Person {

    private static int idCounter = 1111; //Starting ID
    protected static List<Person> people = new ArrayList<>();
    private int id; // Unique ID for each person
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final String name;
    protected int balance;
    private final Gender gender;
    private final String birthdate;

    // Constructor
    public Person(String name, int balance, Gender gender, String birthdate) {
        this.id = idCounter++;
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthdate = birthdate;
        people.add(this);
    }

    // getters
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    // set the balance of people, the for loop is for sync between clients-instructors-secretary-person
    public void setBalance(int balance) {
        for (Person p : people) {
            if (p.getName().equals(this.getName())) {
                p.balance = balance;
            }
        }
    }

    public Gender getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    // Method that calculae age
    public int getAge() {
        LocalDate birthDate = LocalDate.parse(this.birthdate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return Period.between(birthDate, LocalDate.now()).getYears();
    }


    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Gender: " + gender +
                " | Birthday: " + birthdate +
                " | Age: " + getAge() +
                " | Balance: " + balance;
    }

}
