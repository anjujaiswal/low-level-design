package day2;

import java.util.List;
//abstract class Employee
//Fields: id, name (decide which are final)
//Abstract method: double calculateMonthlyPay()
//Concrete method: String getPaySlip() returns something like "E101 - Ravi: ₹50000.0". It should call calculateMonthlyPay() inside it.
//FullTimeEmployee: has monthlySalary
//Contractor: has hourlyRate and hoursWorked. Pay = rate × hours.
//        Validation: hours can’t be negative, and the rate must be positive.
//Main: put 2 full-timers and 2 contractors in a List<Employee>, loop, and print each pay slip. No instanceof, no if checking the type.
//Test one failure case: a contractor with negative hours should throw an exception.
public class Day2 {
    public static void main(String[] args) {



        FullTimeEmployee fulltimer1 = new FullTimeEmployee("101", "FE1", 30000);
        FullTimeEmployee fulltimer2 = new FullTimeEmployee("102", "FE2", 50000);

        Contractor contractor1 = new Contractor("103", "C1", 222, 3);
        Contractor contractor2 = new Contractor("104", "C2", 444, 4);
        List<Employee> employees= List.of(fulltimer1,fulltimer2,contractor1,contractor2)  ;

        for(Employee e: employees){
            System.out.println(e.getPaySlip());
        }

        try {
            contractor2.setHoursWorked(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            contractor2.setHourlyRate(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            fulltimer2.setSalary(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        contractor1.setHourlyRate(500);
        System.out.println(contractor1.getPaySlip());

        fulltimer1.setSalary(60000);
        System.out.println(fulltimer1.getPaySlip());
    }
}
