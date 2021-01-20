package Company;

import java.util.ArrayList;
import java.util.Collections;

public class Company {
    private ArrayList<Employee> staff;
    private static int income;

    public Company (){
        staff = new ArrayList<>();
        this.setIncome(0);
        }

    public void setStaff(ArrayList<Employee> staff) {

        this.staff = staff;
    }

    public void setIncome(int income) {

        this.income = income;
    }

    public void hire(Employee employee){
        staff.add(employee);

        if(employee instanceof Manager) {
            income = income + ((Manager)employee).getSales();
        }
    }

    public void hireAll(ArrayList<Employee> list){
        for (int i = 0;i < list.size(); i++){
            hire(list.get(i));
        }
    }

    public void fire(int indexOfEmployee){

        staff.remove(staff.get(indexOfEmployee));
        if(staff.get(indexOfEmployee) instanceof Manager){
            income = income - ((Manager)staff.get(indexOfEmployee)).getSales();
        }
    }

    public void getStaff(){
        for(Employee name : staff){
            System.out.println(name + " с окладом " + name.getMonthSalary() + " руб.");
        }
    }

    public int getIncome(){
        System.out.println("Прибыль компании составляет: "  + income + " руб.");
        return income;
    }

    public ArrayList<Employee> sortSalaryStaff(int count)
    {
        if(count > staff.size()){
            count = staff.size();
            System.out.println("Так как кол-во сотрудников в компании меньше запрашиваемых - мы предоставим отсортированный список всех зарплат");
        }
        ArrayList<Employee> sortSalaryStaff = new ArrayList<>();
        for(int i = 0;i < staff.size();i++){
            sortSalaryStaff.add(staff.get(i));
        }
        Collections.sort(sortSalaryStaff);

        return sortSalaryStaff;
    }

    public void getTopSalaryStaff(int count) {
        sortSalaryStaff(count);
        for (int i = 0; i < count; i++) {
            System.out.println(sortSalaryStaff(count).get(i) + " - " + sortSalaryStaff(count).get(i).getMonthSalary());
        }
    }

    public void getLowSalaryStaff(int count){
        sortSalaryStaff(count);
        for(int i = sortSalaryStaff(count).size() - 1; i >= (sortSalaryStaff(count).size() - count); i--){
            System.out.println(sortSalaryStaff(count).get(i) + " - " + sortSalaryStaff(count).get(i).getMonthSalary());
        }
    }
}
