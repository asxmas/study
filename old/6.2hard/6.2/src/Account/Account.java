package Account;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Account
{
    TreeMap<String, Integer> ordersAmount = new TreeMap<>();

    public void openAccount()
    {
        int lastThreeNumberOfBik = 332;
        int firstOrderBalanceAccountNumber = 0;
        int accountCurrency = 0;
        int putDigit = 0;
        int branchNumber = 0;
        int clientNumber = 0;
        System.out.println("Введите фамилию");
        Scanner scannerName = new Scanner(System.in);
        String name = scannerName.nextLine();
        System.out.println("Кому вы хотите открыть расчетный счет: Физическое лицо, ИП, Организация?");
        Scanner scannerFace = new Scanner(System.in);
        String face = scannerFace.nextLine();
        switch (face)
        {
            case ("Физическое лицо"):
                firstOrderBalanceAccountNumber = 40817;
                break;
            case ("ИП"):
                firstOrderBalanceAccountNumber = 40802;
                break;
            case ("Организация"):
                firstOrderBalanceAccountNumber = 40702;
                break;
            default:
                System.out.println("Наш банк обслуживает только вышеупомянутых лиц");
                break;
        }
        System.out.println("Укажите валюту счета (доллар, евро, рубль");
        Scanner scannerValue = new Scanner(System.in);
        String value = scannerValue.nextLine();
        switch (value)
        {
            case ("рубль"):
                accountCurrency = 810;
                break;
            case ("доллар"):
                accountCurrency = 840;
                break;
            case ("евро"):
                accountCurrency = 978;
                break;
            default:
                System.out.println("Мы не проводим операции с данной валютой");
        }
        System.out.println(branchNumber);
        System.out.println("Укажите отеделение банка, в котором будет вестись счет (Западное, Северное, Южное)");
        Scanner scannerBranch = new Scanner(System.in);
        String branch = scannerBranch.nextLine();
        switch (branch)
        {
            case ("Западное"):
                branchNumber = 453;
                break;
            case ("Северное"):
                branchNumber = 45;
                break;
            case ("Южное"):
                branchNumber = 4231;
                break;
            default:
                System.out.println("Такого отделения у нашего банка не существует");
                break;
        }
        ArrayList<String> bankOrdersList = new ArrayList<>(ordersAmount.keySet());
        clientNumber = bankOrdersList.size() + 1;
        String numberForDigitNumber = Integer.toString(branchNumber) + Integer.toString(firstOrderBalanceAccountNumber) + Integer.toString(accountCurrency) + Integer.toString(putDigit) + numberOfDigits(branchNumber, 4) + numberOfDigits(clientNumber, 7);
        String [] numberForDigitNumbers = numberForDigitNumber.split("");
        int [] coefficients = {7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1};
        int sum = 0;
        for(int i = 0;i < coefficients.length; i++)
        {
            sum += coefficients[i] * Integer.parseInt(numberForDigitNumbers[i]) % 10;
        }
        putDigit = ((sum % 10 * 3) * 3) % 10;
        String accountNumber = Integer.toString(firstOrderBalanceAccountNumber) + Integer.toString(accountCurrency) + Integer.toString(putDigit) + numberOfDigits(branchNumber, 4) + numberOfDigits(clientNumber, 7);
        ordersAmount.put(accountNumber, 0);
    }

    public void replenishAccount(String accountNumber)
    {
            System.out.println("Введите сумму пополнения для счета " + accountNumber);
            Scanner scannerAmount = new Scanner(System.in);
            int amount = Integer.parseInt(scannerAmount.nextLine());
            ordersAmount.put(accountNumber, ordersAmount.get(accountNumber) + amount);
    }
    public void withdrawAccount(String accountNumber)
    {
        System.out.println("Введите сумму, которую хотели бы снять со счета " + accountNumber);
        Scanner scannerAmount = new Scanner(System.in);
        int amount = Integer.parseInt(scannerAmount.nextLine());
        if(amount <= ordersAmount.get(accountNumber))
        {
            ordersAmount.put(accountNumber, ordersAmount.get(accountNumber) - amount);
        }
        else System.out.println("У вас недостаточно средств на счете");
    }
    public void balance(String accountNumber)
    {
        String valueCode = accountNumber.substring(5, 8);
        String value = "";
        switch (valueCode)
        {
            case ("810"):
                value = "RUR";
                break;
            case ("840"):
                value = "USD";
                break;
            case ("978"):
                value = "EUR";
        }
        System.out.println("На счете " + accountNumber + " " + value);
    }

    private static String numberOfDigits (int numberInInt, int characters)
    {
        String number = Integer.toString(numberInInt);
        for(;number.length() < characters;number = "0" + number);
        return number;
    }
}
