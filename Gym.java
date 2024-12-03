public class Gym {
    private static final Gym gym = new Gym();
    private String name;
    private Secretary secretary;
    private int gymBalance;

    private Gym(){}

    public static Gym getInstance(){
        return gym;
    }

    public void setName(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setSecretary(Person secretary, int salary){
        if (secretary instanceof Secretary) {
            this.secretary = (Secretary) secretary;
        }
        else {
            this.secretary = new Secretary(secretary.getName(), secretary.getBalance(), secretary.getGender(), secretary.getBirthDate());
        }

        this.secretary.setSalary(salary);
    }

    public Secretary getSecretary(){
        return this.secretary;
    }

    public int getGymBalance() {
        return gymBalance;
    }

    public void addToGymBalance(int amount) {
        this.gymBalance += amount;
    }

    public void substuctFromGymBalance(int amount) {
        this.gymBalance -= amount;
    }
}
