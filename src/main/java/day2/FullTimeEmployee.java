package day2;
//FullTimeEmployee: has monthlySalary
public class FullTimeEmployee extends  Employee{

    private  double salary;


   public FullTimeEmployee(String id, String name, double salary ){
       super(id, name);
       setSalary(salary);
   }

   public double monthlySalary(){
       return salary;
   }

    public void setSalary(double salary) {
        if(salary <= 0.0){
            throw new IllegalArgumentException("Salary must be positive");
        }
        this.salary = salary;
    }

    @Override
    protected double calculateMonthlyPay() {
        return this.salary;
    }


}
