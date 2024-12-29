package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDate;

public class ThaiBoxingSession extends Session {
    public ThaiBoxingSession(LocalDate dateTime, ForumType forum, Instructor instructor, int maxCapacity, int price){
        super(dateTime, forum, instructor, maxCapacity, price);

        }
        public String getSessionType() {
        return "Thai Boxing";
        }
    }



