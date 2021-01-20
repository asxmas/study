import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong telephone format. Correct format \nadd Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        if (checkEmail(components[2]) == false){
            throw new IllegalArgumentException("Wrong e-mail format. Correct e-mail format \nvasily.petrov@mail.com");
        }
        if (checkTelephone(components[3]) == false){
        throw new IllegalArgumentException("Wrong telephone number format. Correct telephone number is one of: \n+1-123-12-12, 1(123)123-12-12, 1231231212");
        }
        String name = components[0] + " " + components[1];
        if(checkName(name) == false){
            throw new IllegalArgumentException("Wrong name format. Correct format \nadd Имя Фамилия");
        }
        if (storage.containsKey(name)){
            throw new IllegalArgumentException("This name is listed. Enter a different first name last name or remove the given name from the list.");
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        if (storage.isEmpty()){
            throw new NullPointerException("The list is empty. Please add new customer");
        }
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        String[] components = name.split("\\s+");
        if (components.length != 2) {
            throw new IllegalArgumentException("Wrong format. Correct format \nremove Василий Петров");
        }
        if (storage.containsKey(name) == false){
            throw new IllegalArgumentException("This name is not in the list. Please, enter the name correctly");
        }
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }

    private boolean checkName(String name){
        Pattern pattern = Pattern.compile("(([А-Я][а-я]+)(-[А-Я][а-я]+)?\\s+){2}");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean checkEmail(String email){
        Pattern pattern = Pattern.compile("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean checkTelephone(String telephone) {
        Pattern pattern = Pattern.compile("(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}");
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }


}