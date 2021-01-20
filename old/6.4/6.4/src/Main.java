import Company.Company;
import Company.Manager;
import Company.Operator;
import Company.TopManager;
import Company.Employee;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Company rogaikopita = new Company();
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            employees.add(new Manager(rogaikopita));
        }
        for (int i = 0; i < 10; i++) {
            employees.add(new TopManager(rogaikopita));
        }
        for (int i = 0; i < 180; i++) {
            employees.add(new Operator(rogaikopita));
        }

        rogaikopita.hireAll(employees);
        System.out.println("-");

        rogaikopita.getTopSalaryStaff(15);
        System.out.println("-");

        rogaikopita.getLowSalaryStaff(30);
        System.out.println("-");

        rogaikopita.getIncome();
        System.out.println("-");

        for(int i = 0; i < 135; i++){
            rogaikopita.fire(i);
        }

        rogaikopita.getTopSalaryStaff(15);
        System.out.println("-");

        rogaikopita.getLowSalaryStaff(30);
        System.out.println("-");

        rogaikopita.getIncome();
        System.out.println("-");
    }
}
