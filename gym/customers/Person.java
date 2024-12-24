package gym.customers;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String name;
    private double balance;
    private Gender gender;
    private String birthdate;

    public Person(String name, double balance, Gender gender, String birthdate) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthdate = birthdate;
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Gender getGender() {
        return gender;
    }
    public String getBirthdate() {
        return birthdate;
    }
    public int getAge() {
        LocalDate birthDate = LocalDate.parse(birthdate, DATE_FORMAT);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    @Override
    public String toString() {
        return  "Name: " + name + " | Gender: " + gender + " | Birthday: " + birthdate + " | Balance: " + balance;
    }
}
