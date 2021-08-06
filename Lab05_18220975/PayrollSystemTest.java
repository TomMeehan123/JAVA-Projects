/**
 * The PayrollSystemsTest is to the wage for all employees.
 * 
 * author: Tom Meehan
 * Date: 19/02/19
 * ID Number: 18220975
 */
public class PayrollSystemTest 
{
   public static void main( String[] args ) 
   {
     /**
      * Step 1: Create a new subclass oblect for a salariedEmployee.
      * Step 2: Create a new subclass object for an hourlyEmployee.
      * Step 3: Create a new subclass object for a ComissionEmployee.
      * Step 4: Create a new subclass object for a BasePlusCommissionEmployee.
      * Step 5: Create a new subclass object for a PieceWorker.
      * Step 6: Create a new subclass object for any employee.
      * Step 7: Create a new subclass object for any employee.
      * Step 8: Print all of the information for each employee.
      * Step 9: Calculate the wage for each employee using the earnings method.
      * Step 10: Create a 7 element array.
      * Step 11: Initiliaze the array to take all of the values for each employee.
      */
       
     // create subclass objects
      SalariedEmployee salariedEmployee = 
         new SalariedEmployee( "John", "Smith", "111-11-1111", 800.00 );
      HourlyEmployee hourlyEmployee = 
         new HourlyEmployee( "Karen", "Price", "222-22-2222", 16.75, 40 );
      CommissionEmployee commissionEmployee = 
         new CommissionEmployee( 
         "Sue", "Jones", "333-33-3333", 10000, .06 );
      BasePlusCommissionEmployee basePlusCommissionEmployee = 
         new BasePlusCommissionEmployee( 
         "Bob", "Lewis", "444-44-4444", 5000, .04, 300 );
      PieceWorker pieceWorker = new PieceWorker("Di", "Gress", "333-33-3333", 2.25, 60);
      SalariedEmployee salariedEmployee2 = 
         new SalariedEmployee( "Per", "Pear", "111-22-3333", 900.00 ); 
      HourlyEmployee hourlyEmployee2 =
         new HourlyEmployee( "Tom", "Meehan", "18220975", 18.50, 56 );
     
      
      System.out.println( "Employees processed individually:\n" );
      
      System.out.printf( "%s\n%s: €%,.2f\n\n", 
         salariedEmployee, "earned", salariedEmployee.earnings() );
      System.out.printf( "%s\n%s: €%,.2f\n\n",
         hourlyEmployee, "earned", hourlyEmployee.earnings() );
      System.out.printf( "%s\n%s: €%,.2f\n\n",
         commissionEmployee, "earned", commissionEmployee.earnings() );
      System.out.printf( "%s\n%s: €%,.2f\n\n", 
         basePlusCommissionEmployee, 
         "earned", basePlusCommissionEmployee.earnings() );
      System.out.printf( "%s\n%s: €%,.2f\n\n",
         pieceWorker, "earned", pieceWorker.earnings() );
      System.out.printf( "%s\n%s: €%,.2f\n\n", 
         salariedEmployee2, "earned", salariedEmployee2.earnings() );
      System.out.printf( "%s\n%s: €%,.2f\n\n",
         hourlyEmployee2, "earned", hourlyEmployee2.earnings() );
      
      
      // create 7-element Employee array
      Employee[] employees = new Employee[ 7 ]; 

      // initialize array with Employees
      employees[ 0 ] = salariedEmployee;
      employees[ 1 ] = hourlyEmployee;
      employees[ 2 ] = commissionEmployee; 
      employees[ 3 ] = basePlusCommissionEmployee;
      employees[ 4 ] = pieceWorker;
      employees[ 5 ] = salariedEmployee2;
      employees[ 6 ] = hourlyEmployee2;
      
      System.out.println( "Employees processed polymorphically:\n" );
      
      // generically process each element in array employees
      for ( Employee currentEmployee : employees ) 
      {
         System.out.println( currentEmployee ); // invokes toString

         // determine whether element is a BasePlusCommissionEmployee
         if ( currentEmployee instanceof BasePlusCommissionEmployee ) 
         {
            // downcast Employee reference to 
            // BasePlusCommissionEmployee reference
            BasePlusCommissionEmployee employee = 
               ( BasePlusCommissionEmployee ) currentEmployee;

            employee.setBaseSalary( 1.10 * employee.getBaseSalary() );

            System.out.printf( 
               "new base salary with 10%% increase is: €%,.2f\n",
               employee.getBaseSalary() );
         } // end if

         System.out.printf( 
            "earned €%,.2f\n\n", currentEmployee.earnings() );
      } // end for

      // get type name of each object in employees array
      for ( int j = 0; j < employees.length; j++ )
         System.out.printf( "Employee %d is a %s\n", j, 
            employees[ j ].getClass().getName() ); 
   } // end main
} // end class PayrollSystemTest