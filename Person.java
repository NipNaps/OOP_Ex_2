import java.time.*;

public class Person {
    private final String name;
    private int balance;
    private final Gender gender;
    private final String birthDate;

    public Person(String name , int balance, Gender gender , String birthDate){
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Person(Person other) {
        this.name = other.getName();
        this.balance = other.getBalance();
        this.gender = other.getGender();
        this.birthDate = other.getBirthDate();
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void substuctFromBalance(int amount) {
        this.balance -= amount;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isAboveEightTeen() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currYear = currentDateTime.getYear();
        int currMonth = currentDateTime.getMonthValue();
        int currDay = currentDateTime.getDayOfMonth();

        int year = Integer.parseInt(this.birthDate.substring(6,9));
        int month = Integer.parseInt(this.birthDate.substring(3, 4));
        int day = Integer.parseInt(this.birthDate.substring(0, 1));

        int yearRemainder = currYear - year;
        int monthRemainder = currMonth - month;
        int dayRemainder = currDay - day;

        if (yearRemainder > 18)
            return true;

        else return yearRemainder == 18 && monthRemainder >= 0 && dayRemainder >= 0;
    }


    public boolean isSenior(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currYear = currentDateTime.getYear();
        int currMonth = currentDateTime.getMonthValue();
        int currDay = currentDateTime.getDayOfMonth();

        int year = Integer.parseInt(this.birthDate.substring(6,9));
        int month = Integer.parseInt(this.birthDate.substring(3, 4));
        int day = Integer.parseInt(this.birthDate.substring(0, 1));

        int yearRemainder = currYear - year;
        int monthRemainder = currMonth - month;
        int dayRemainder = currDay - day;

        if (yearRemainder > 65)
            return true;

        else return yearRemainder == 65  && monthRemainder >= 0 && dayRemainder >= 0;
    }

}
