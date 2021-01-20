import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
//        Задание 5.1.1
//        String text = "Каждый охотник желает знать, где сидит фазан";
//        String[] colors = text.split(",?\\s+");
//        String[] reverseColors = new String[colors.length];
//        for(int a = 0; a < colors.length; a++)
//        {
//            reverseColors[a] = colors[colors.length - 1 - a];
//        }
//        for (int i = 0; i < reverseColors.length; i++)
//        {
//            System.out.println(reverseColors[i]);
//        }
        //Задание 5.1.2
//        int min = 320;
//        int max = 400;
//        double[] temp = new double[30];
//        int healthyPatients = 0;
//        double averageTemp = 0.0;
//
//        for(int i = 0; i < temp.length; i++)
//        {
//            temp[i] = randomTemp(min, max)/10;
//            System.out.println(temp[i]);
//            averageTemp = averageTemp + temp[i];
//            if(temp[i] >= 36.2 && temp[i] <= 36.9)
//            {
//                healthyPatients = healthyPatients + 1;
//            }
//        }
//        System.out.println(healthyPatients);
//        System.out.println(averageTemp/temp.length);
//    }
//    public static double randomTemp(double min, double max)
//    {
//        max -= min;
//        return (int) (Math.random() * ++max) + min;
        // Задание 5.1.3
        System.out.println("Введите количество строк для формирования крестика");
        Scanner scanner = new Scanner(System.in);
        int numOfLines = Integer.parseInt(scanner.nextLine());
        String[][] result = new String[numOfLines][numOfLines];
        for (int a = 0; a < result.length; a++) {
            for (int b = 0; b < result.length; b++) {
                if (a == b || a == result.length - b - 1) {
                    result[a][b] = "x";
                } else result[a][b] = " ";
            }
            System.out.println(String.join("", result[a]));
        }
    }
}