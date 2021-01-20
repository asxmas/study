package Company;

public abstract class AbstractEmployee implements Employee {

        private int salary;
        private Company company;

    public AbstractEmployee(Company company){
        this.company = company;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
        public double getMonthSalary () {
            return salary;
        }

        @Override
        public int compareTo (Employee employee){
            if (getMonthSalary() > employee.getMonthSalary()) {
                return -1;
            }
            if (getMonthSalary() < employee.getMonthSalary()) {
                return 1;
            }
            return 0;
        }

}
