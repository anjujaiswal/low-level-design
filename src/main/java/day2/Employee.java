package day2;

//abstract class Employee
//Fields: id, name (decide which are final)
//Abstract method: double calculateMonthlyPay()

 public abstract class Employee {

     private final String id;
     private  String name;


     protected Employee(String id, String name) {
         this.id = id;
         this.name = name;

     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     protected  abstract  double  calculateMonthlyPay();

     public String getPaySlip(){
         double salary = calculateMonthlyPay();
         return "E" + id+ " - "+ name + ": Rs"+ salary;
     }
 }
