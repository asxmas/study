package Company;

public class Operator extends AbstractEmployee{

    public Operator(Company company) {
        super(company);
        this.setSalary((int)(300 + Math.random()*100)*100);
    }
    @Override
    public double getMonthSalary() {
        return this.getSalary();
    }
}
