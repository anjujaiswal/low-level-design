package day2;
//Contractor: has hourlyRate and hoursWorked. Pay = rate × hours.
//        Validation: hours can’t be negative, and the rate must be positive.
public class Contractor  extends Employee {

    private double hourlyRate;
    private double hoursWorked ;

    public Contractor(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name);
       setHourlyRate(hourlyRate);
       setHoursWorked(hoursWorked);
    }

    public double getHourlyRate() {

        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {

            if (hourlyRate <= 0) {
                throw new IllegalArgumentException("HourlyRate must be positive");
            }

            this.hourlyRate = hourlyRate;

    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked (double hoursWorked){
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours cant be negative");
        }
        this.hoursWorked = hoursWorked;
    }


    @Override
    protected double calculateMonthlyPay() {
        return hourlyRate* hoursWorked;
    }
}