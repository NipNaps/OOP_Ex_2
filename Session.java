import java.time.*;

public class Session {
    private final SessionType sessionType;
    private String date;
    private final ForumType forumType;
    private Instructor instructor;

    public Session(SessionType sessionType, String date, ForumType forumType,Instructor instructor) {
        this.sessionType = sessionType;
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public ForumType getForumType() {
        return forumType;
    }

    public String getDate() {
        return date;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public boolean hasPast() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currYear = currentDateTime.getYear();
        int currMonth = currentDateTime.getMonthValue();
        int currDay = currentDateTime.getDayOfMonth();
        int currentHours = currentDateTime.getHour();
        int currentMinutes = currentDateTime.getMinute();

        int year = Integer.parseInt(this.date.substring(6,9));
        int month = Integer.parseInt(this.date.substring(3, 4));
        int day = Integer.parseInt(this.date.substring(0, 1));
        int hour = Integer.parseInt(this.date.substring(11, 12));
        int minutes = Integer.parseInt(this.date.substring(14, 15));

        int yearRemainder = currYear - year;
        int monthRemainder = currMonth - month;
        int dayRemainder = currDay - day;
        int hourRemainder = currentHours - hour;
        int minutesRemainder = currentMinutes - minutes;

        return yearRemainder > 0 && monthRemainder > 0 && dayRemainder > 0 && hourRemainder > 0 && minutesRemainder > 0;
    }
}
