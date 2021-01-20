import Account.Account;

import java.util.Scanner;
import java.util.TreeMap;

public class Main
{
    public static void main(String[] args)
    {
        for(int i = 0; i != 0;) {
            System.out.println("Здравствуйте! Что бы Вы хотели сделать в нашем банке (наберите нужную цифру):" +
                    "\n1. Открыть счет" +
                    "\n2. Пополнить счет" +
                    "\n3. Снять деньги со счета" +
                    "\n4. Узнать баланс счета" +
                    "\n5. Я просто смотрю");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine();
            switch (action)
            {
                case ("1"):
                    System.out.println("Назовите фамилию");
                    Scanner scannerName = new Scanner(System.in);
                    String name = scannerName.nextLine();
                    Account scannerName.next() = new Account();
                    vasiliy.openAccount();
                case ("2"):

            }
        }
    }
}
