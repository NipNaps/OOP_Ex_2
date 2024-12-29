package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDateTime;

public class MachinePilatesSession extends Session {
    public MachinePilatesSession(LocalDateTime dateTime, ForumType forum, Instructor instructor, int maxCapacity, int price){
        super(SessionType.MachinePilates, String.valueOf(dateTime), forum, instructor, maxCapacity, price);
        }
        public String getSessionType(){
        return "Machine Pilates";
        }
    }

