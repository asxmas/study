import java.util.Scanner;

public class Shipment
{
    private static final int MAX_CONTEINERS = 12;
    private static final int MAX_BOXES = 27;
    private static int quantityBoxes;
    private static int needTrucks;
    private static int needConteiners;
    private static int leftTrucks;
    private static int leftConteners;
    private static int leftBoxes;


    public static void boxesQuantity()
    {
        Scanner quantity = new Scanner(System.in) ;
        System.out.println("Введите количество пришедших ящиков");
        quantityBoxes = quantity.nextInt();
        System.out.println("Всего ящиков: " + quantityBoxes);
    }
    public void needResources()
    {
        needConteiners = (int) Math.ceil((double) quantityBoxes / MAX_BOXES);
        needTrucks = (int) Math.ceil((double) needConteiners / MAX_CONTEINERS);
        System.out.println("Необходимо контейнеров: " + needConteiners);
        System.out.println("Необходимо грузовиков: " + needTrucks);
    }
    public static void loadTrucks()
    {
        leftTrucks = needTrucks;
        leftConteners = needConteiners;
        leftBoxes = quantityBoxes;
        int numberTruck = 1;
        int numberConteiner = 1;
        int numberBox = 1;

            for (; leftTrucks > 0; )
            {
                System.out.println("Номер грузовика: " + numberTruck);
                for (; leftConteners > 0; )
                {
                    if (numberConteiner > MAX_CONTEINERS * numberTruck)
                        break;
                    System.out.println("Номер контейнера: " + numberConteiner);

                    for (; leftBoxes > 0; )
                    {
                        if (numberBox > MAX_BOXES * numberConteiner)
                            break;
                        System.out.println("Номер ящика: " + numberBox);
                        leftBoxes = leftBoxes - 1;
                        numberBox = numberBox + 1;
                    }
                    leftConteners = leftConteners - 1;
                    numberConteiner = numberConteiner + 1;

                }
                leftTrucks = leftTrucks - 1;
                numberTruck = numberTruck + 1;

            }
    }
}

