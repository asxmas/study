package Company;

public class TopManager extends AbstractEmployee{

    private double bonusKoefficient = 1;

    public TopManager(Company company) {
        super(company);
        if(company.getIncome() > 1000000){
            bonusKoefficient = 2.5;
        }
        this.setSalary((int)(((1700 + Math.random() * 300) * 100) * bonusKoefficient));
    }

    @Override
    public double getMonthSalary() {
        return this.getSalary();
    }
}
