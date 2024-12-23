package gym.management;

import gym.customers.Person;

import java.util.ArrayList;
import java.util.List;

    public class Secretary extends Person {
        private double salary;
        private List<String> actionHistroy;

        public Secretary(Person person, double salary) {
            super(person.getName(), person.getBalance(), person.getGender(), person.getBirthdate());
            this.salary = salary;
            this.actionHistroy = new ArrayList<>();
        }
        public double getSalary() {
            return salary;
        }
        public void logAction(String action) {
            actionHistroy.add(action);
        }
        public void printActions() {
            for (String action : actionHistroy) {
                System.out.println(action);
            }
        }
        public String toString() {
            return super.toString() + " | Role: Secretary | Salary per month: " + salary;
        }
    }

