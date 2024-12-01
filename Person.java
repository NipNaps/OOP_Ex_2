public class Person {
    protected final String name;
    protected int balance;
    protected final Gender gender;
    protected final String birthDate;

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

    public String getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        int year = Integer.parseInt(this.getBirthDate().substring(6));
        return 2024 - year;
    }

}
