import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args)
    {
        TreeMap<String, String> telephone2name = new TreeMap<>();
        telephone2name.put("9055951213", "Фронька");
        telephone2name.put("9055951212", "Лялька");
        telephone2name.put("9052084494", "Гулька");
        telephone2name.put("9018923132", "Сенька");

        System.out.println("Введите ФИО или номер телефона");
        Scanner scanner = new Scanner(System.in);
        for(;;)
        {
            String enter = scanner.nextLine();
            enter = enter.replaceAll("(list)|(LIST)|(лист)|(ЛИСТ)", "LIST");
            if(enter.equals("LIST"))
            {
                printMap(telephone2name);
                continue;
            }
            if(checkTelephone(enter))
            {
                String telephone = enter.replaceAll("[^0-9,]", "").replaceAll("^[8, 7]","");
                if(telephone2name.containsKey(telephone)) {
                    System.out.println(telephone2name.get(telephone) + " - +7" + telephone);
                }
                else
                {
                    System.out.println("Данный телефон отсутствует в телефонной книге. Введите имя контакта для данного телефона");
                    Scanner scanner1 = new Scanner(System.in);
                    String name = scanner1.nextLine();
                    telephone2name.put(telephone, name);
                    System.out.println("Вы добавили в книгу новый контакт: " + name + " - +7" + telephone);
                }
            }
            else
            {
                if(telephone2name.containsValue(enter))
                {
                    for(String i : telephone2name.keySet()) {
                        if (telephone2name.get(i).equals(enter)) {
                            System.out.println(enter + " - +7" + i);
                        }
                    }
                }
                else
                {
                    System.out.println("Данного имени нет в телефонной книге. Введите телефон для указаного имени");
                    Scanner scanner1 = new Scanner(System.in);
                    String telephone = scanner1.nextLine();
                    if(checkTelephone(telephone))
                    {
                        String trueTelephone = telephone.replaceAll("[^0-9]", "").replaceAll("^[8, 7]","");
                        telephone2name.put(trueTelephone, enter);
                        System.out.println("Вы добавили в книгу новый контакт: " + enter + ": +7" + trueTelephone);
                    }
                    else System.out.println("Вы ввели неправильное значение телефона");

                }
            }
        }
    }
    private static boolean checkTelephone(String telephone)
    {
        telephone = telephone.replaceAll("[^0-9,]", "").replaceAll("^[8, 7]","");
        Pattern pattern = Pattern.compile("[0-9]{10}");
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }

    private static void printMap(Map<String, String> map)
    {
        ArrayList<String> name2telephone = new ArrayList<>();
        for(String key : map.keySet())
        {
            name2telephone.add(map.get(key) + ": +7" + key);
        }
        Collections.sort(name2telephone);
        for(String i : name2telephone)
        {
            System.out.println(i);
        }

    }

}