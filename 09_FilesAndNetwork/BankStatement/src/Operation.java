import java.util.Date;
import java.util.List;

public class Operation {

    //   Тип счёта,Номер счета,Валюта,Дата операции,Референс проводки,Описание операции,Приход,Расход

    private String accountType;
    private String accountNumber;
    private String accountValue;
    private Date operationDate;
    private String reference;
    private String operationDescription;
    private Double income;
    private Double expense;
    private String operationType;

    public Operation(String accountType, String accountNumber, String accountValue, Date operationDate, String reference, String operationDescription, double income, double expense) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountValue = accountValue;
        this.operationDate = operationDate;
        this.reference = reference;
        this.operationDescription = operationDescription;
        this.income = income;
        this.expense = expense;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountValue() {
        return accountValue;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public String getReference() {
        return reference;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public Double getIncome() {
        return income;
    }

    public Double getExpense() {
        return expense;
    }

    public String getOperationType(){
        operationType = operationDescription.substring(operationDescription.length()-4);
        return operationType;
    }
}

