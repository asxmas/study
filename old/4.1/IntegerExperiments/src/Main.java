import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;

public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        int number = 123123;
        sumDigits(number);

    }

    public static Integer sumDigits(Integer number)
        {
            int n = 0;
            int sum = 0;
            for (; n < number.toString().length();n++)
            {
                String num = Character.toString(number.toString().charAt(n));
                sum = Integer.parseInt(num) + sum;
            }
            System.out.println(sum);
            return sum;
        }
}
