import java.beans.IntrospectionException;

public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5001 рублей, Петя -         7563              рубля, а Маша - 30000 рублей, а еще кто-то получил 23401, а еще 5001 рубль";
        String trueText1 = text.replaceAll("[^0-9\\s]" , "");
        String trueText = trueText1.replaceAll("\\s+", " ").trim();
        System.out.println(trueText);
        String[] summs = trueText.split(" ");
        int sum = 0;
        for(int i = 0; i < summs.length; i++)
        {
            sum = sum + Integer.parseInt(summs[i]);
        }
        System.out.println(sum);
    }
}