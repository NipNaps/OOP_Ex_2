package gym.customers;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {

    private static int idCounter = 1111; //Starting ID
    private int id; // Unique ID for each person
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final String name;
    private int balance;
    private final Gender gender;
    private final String birthdate;

    // Constructor
    public Person(String name, int balance, Gender gender, String birthdate) {
        this.id = idCounter++;
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Gender getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getAge() {
        LocalDate birthDate = LocalDate.parse(this.birthdate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return  "ID : " + id +
                " |Name: " + name +
                " | Gender: " + gender +
                " | Birthday: " + birthdate +
                " | Balance: " + balance;
    }

}
