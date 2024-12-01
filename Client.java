public class Client extends Person{

    public Client(String name, int balance, Gender gender, String birthDate) {
        super(name, balance, gender, birthDate);
    }

    public Client(Person person) {
        super(person);
    }
}
