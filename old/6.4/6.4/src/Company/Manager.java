package Company;

public class Manager extends AbstractEmployee{

    private int sales;

    public Manager(Company company) {
        super(company);
        this.setSales((int)(3000 + Math.random()*1000)*100);
        this.setSalary((int)(((800 + Math.random()*100)*100) + Math.round(getSales()*0.05)));
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Override
    public double getMonthSalary() {
        return this.getSalary();
    }

}
