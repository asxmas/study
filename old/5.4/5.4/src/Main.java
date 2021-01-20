import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String helpMessage = "Список доступных команд:" +
            "\nДля поиска контакта по имени введите имя контакта;" +
            "\nДля поиска телефона по телефону введите телефон, состоящий из 10-12 цифр (например: ***-***-**-**, +7-***-***-**-**, 8(***)***-**-** или в другом удобном Вам формате);" +
            "\nДля отражения всей записной книжки наберите одно из слов: лист, list, ЛИСТ, LIST;" +
            "\nДля завершения работы с телефонной книгой наберите одно из следующих слов: end. END. все, всё, ВСЕ, ВСЁ;" +
            "\nДля отражения списка комманд наберите одно из следующих слов: help, HELP, помощь, ПОМОЩЬ;";
    public static String nextCommand = "Введите команду, ФИО или телефон. Для списка всех команд напишите help, HELP, помощь или ПОМОЩЬ.";
    public static void main(String[] args)
    {
        TreeMap<String, String> telephone2name = new TreeMap<>();
        telephone2name.put("9055951213", "Фронька");
        telephone2name.put("9055951212", "Лялька");
        telephone2name.put("9052084494", "Гулька");
        telephone2name.put("9018923132", "Сенька");

        System.out.println(helpMessage + "\n" + nextCommand);
        Scanner scanner = new Scanner(System.in);
        for (int end = 0; end < 1; )
        {
            String enter = scanner.nextLine();
            enter = enter.
                    replaceAll("(list)|(LIST)|(лист)|(ЛИСТ)", "LIST").
                    replaceAll("(end)|(END)|(все)|(всё)|(ВСЕ)|(ВСЁ)", "END").
                    replaceAll("(help)|(HELP)|(помощь)|(ПОМОЩЬ)", "HELP");
            switch (enter)
            {
                case ("LIST"):
                    printMap(telephone2name);
                    System.out.println(nextCommand);
                    break;
                case ("HELP"):
                    System.out.println(helpMessage + "\n" + nextCommand);
                    break;
                case ("END"):
                    end = 1;
                    System.out.println("Спасибо за пользование телефонной книгой. До Свидания!");
                    break;
                case (""):
                    System.out.println("Не совсем понимаю что Вы имели ввиду. " + nextCommand + " Для списка команд наберите help, HELP, помощь или ПОМОЩЬ.");
                    break;
                default:
                    if (checkTelephone(enter))
                    {
                        String telephone = enter.replaceAll("[^0-9,]", "").replaceAll("^[8, 7]", "");
                        if (telephone2name.containsKey(telephone))
                        {
                            System.out.println(telephone2name.get(telephone) + ": +7" + telephone + "\n" + nextCommand);
                        }
                        else
                        {
                            System.out.println("Данный телефон отсутствует в телефонной книге. Введите имя контакта для данного телефона. Если вы ошиблись в указании телефона, наберите \"стоп\"");
                                Scanner scanner1 = new Scanner(System.in);
                                String name = scanner1.nextLine();
                            if (name.equals("стоп")) {
                                System.out.println(nextCommand);
                                break;
                            }
                            else {
                                telephone2name.put(telephone, name);
                                System.out.println("Вы добавили в книгу новый контакт: " + name + " : +7" + telephone + "\n" + nextCommand);
                            }
                            }
                    }
                    else
                    {
                        if (telephone2name.containsValue(enter))
                        {
                            for (String i : telephone2name.keySet())
                            {
                                if (telephone2name.get(i).equals(enter))
                                {
                                    System.out.println(enter + " : +7" + i);
                                }
                            }
                            System.out.println(nextCommand);
                        }
                        else
                        {
                            System.out.println("Данного имени нет в телефонной книге. Введите телефон для указаного имени. Если Вы ошиблись в вводе имени, наберите \"стоп\"");
                            for(boolean checkTel = false; checkTel = true;)
                            {
                                Scanner scanner1 = new Scanner(System.in);
                                String telephone = scanner1.nextLine();
                                if (telephone.equals("стоп"))
                                {
                                    checkTel = true;
                                    System.out.println(nextCommand);
                                    break;
                                }
                                else if (checkTelephone(telephone)) {
                                    if (telephone2name.containsKey(telephone))
                                    {
                                        System.out.println("Данный номер уже присутствует в телефонной книге: " + telephone2name.get(telephone) + ": +7" + telephone + "\n" + "Если вы ошиблись с именем, наберите \"стоп\" или введите другой телефон для указанного контакта");
                                    }
                                    else {
                                        String trueTelephone = telephone.replaceAll("[^0-9]", "").replaceAll("^[8, 7]", "");
                                        telephone2name.put(trueTelephone, enter);
                                        System.out.println("Вы добавили в книгу новый контакт: " + enter + ": +7" + trueTelephone + "\n" + nextCommand);
                                        checkTel = true;
                                        break;
                                    }
                                } else
                                {
                                    System.out.println("Вы ввели неправильное значение телефона. Телефон должен состоять из 10-11 цифр. Если вы ошиблись с именем контакта, введите \"стоп\"");
                                }
                            }
                            }
                    }
            }
        }
    }

    private static boolean checkTelephone(String telephone)
    {
        telephone = telephone.replaceAll("[^0-9]", "").replaceAll("^[8, 7]", "");
        Pattern pattern = Pattern.compile("[0-9]{10}");
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }

    private static void printMap(Map<String, String> map)
    {
        ArrayList<String> name2telephone = new ArrayList<>();
        for (String key : map.keySet())
        {
            name2telephone.add(map.get(key) + ": +7" + key);
        }
        Collections.sort(name2telephone);
        for (String i : name2telephone)
        {
            System.out.println(i);
        }

    }

}