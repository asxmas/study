import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args)
    {
        // Задание 4.5.2
//        String text = "London is the capital of Great Britain, its political, economic and cultural centre. It's one of the largest cities in the world. Its population is more than million people. London is situated on the river Thames. The city is very old and beautiful. It was founded more than two thousand years ago. Traditionally London is divided into several parts: the City, the West End, the East End and Westminster. The City is the oldest part of London, its financial and business centre. The heart of the City is the Stock Exchange. Westminster is the most important part of the capital. It's the administrative centre. The Houses of Parliament, the seat of the British Government, are there. It's a very beautiful building with two towers and a very big clock called Big Ben. Big Ben is really the bell which strikes every quarter of an hour. Opposite the Houses of Parliament is Westminster Abbey. It's a very beautiful church built over 900 years ago. The tombs of many great statesmen, scientists and writers are there. To the west of Westminster is West End. Here we find most of the big shops, hotels, museums, art galleries, theatres and concert halls. Picadilly Circus is the heart of London West End. In the West End there are wide streets with beautiful houses and many parks, gardens and squares. To the east of Westminster is the East End, an industrial district of the capital. There are no parks or gardens in the East End and you can't see many fine houses there. Most of the plants and factories are situated there. London has many places of interest. One of them is Buckingham Palace. It's the residence of the Queen. The English are proud of Trafalgar Square, which was named so in memory of the victory at the battle. There in 1805 the English fleet defeated the fleet of France and Spain. The last place of interest I should like to mention, is the British Museum, the biggest museum in London. The museum is famous for its library -one of the richest in the world. All London long-past history is told by its streets. There are many streets in London which are known all over the\" world. Among them Oxford Street, Downing Street and a lot of others can be mentioned. And tourists are usually attracted not only by the places of interest but by the streets too. In conclusion I should say if you are lucky enough to find yourself in London some day you will have a lot to see and enjoy there.";
//        String formatText = text.
//                replaceAll("'s", " is").
//                replaceAll("'m", " am").
//                replaceAll("'re", " are").
//                replaceAll("[^a-zA-Z]"," ").
//                replaceAll("\\s+", " ");
//        String[] words = formatText.split(" ");
//        for(int i = 0; i < words.length; i++)
//        {
//            System.out.println(words[i]);
//        }
//         Задание 4.5.3
        System.out.println("Введите Фамилию, Имя, Отчество");
        Scanner scanner = new Scanner(System.in);
        String fullName = scanner.nextLine();

            String[] list = {"Фамилия: ", "Имя: ", "Отчество: "};
            String[] part = fullName.split(" ",3);

            for (int i = 0; i < part.length; i++)
            {
                if(checkFormat(part[i]))
                    System.out.println(list[i] + part[i]);
                else
                    System.out.println(list[i] + "Ошибка ввода. Просьба начинать с большой буквы");
            }
    }
        private static boolean checkFormat(String s)
        {
        Pattern pattern = Pattern.compile("[А-Я][а-я]+|[А-Я][а-я]+\\-[А-Я][а-я]+|[А-Я][а-я]+( оглы| кызы)");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
        }

    // Задание 4.5.4

//        System.out.println("Введите номер телефона");
//        Scanner scanner = new Scanner(System.in);
//        String phone = scanner.nextLine();
//        String phoneNumber = phone.replaceAll("[^0-9+]", "");
//        String firstSymbol = phoneNumber.substring(0, 1);
//        System.out.println(phoneNumber.length());
//        switch (phoneNumber.length())
//        {
//            case 10:
//                System.out.println("+7 " + phoneNumber.substring(0, 3) + " " + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6, 8) + "-" + phoneNumber.substring(8, 10));
//                break;
//            case 11:
//                System.out.println("+7 " + phoneNumber.substring(1, 4) + " " + phoneNumber.substring(4, 7) + "-" + phoneNumber.substring(7, 9) + "-" + phoneNumber.substring(9, 11));
//                break;
//            case 12:
//                if(firstSymbol.equals("+"))
//                System.out.println("+7 " + phoneNumber.substring(2, 5) + " " + phoneNumber.substring(5, 8) + "-" + phoneNumber.substring(8, 10) + "-" + phoneNumber.substring(10, 12));
//                else System.out.println("Вы ввели неверный формат телефона, повторите попытку");
//                break;
//            default:
//            System.out.println("Вы ввели неверный формат телефона, повторите попытку");
//            break;
//        }

    }

