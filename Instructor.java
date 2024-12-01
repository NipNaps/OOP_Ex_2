import java.util.ArrayList;

public class Instructor extends Person{
    private int hourSalary;
    private ArrayList<SessionType> sessionList;


    public Instructor(Person person, int hourSalary, ArrayList<SessionType> sessionList) {
        super(person);
        this.hourSalary = hourSalary;
        this.sessionList = sessionList;
    }

    public int getHourSalary() {
        return hourSalary;
    }

    public void setHourSalary(int hourSalary) {
        this.hourSalary = hourSalary;
    }

    public ArrayList<SessionType> getSessionList() {
        return sessionList;
    }

    public void setSessionList(ArrayList<SessionType> sessionList) {
        this.sessionList = sessionList;
    }
}
