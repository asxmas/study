
public class Loader
{
    public static void main(String[] args)
    {
        Cat Petka = new Cat();
        {
            Petka.getWeight();
            System.out.println(Petka.getWeight());
        }

        Cat Borka = new Cat();
        {
            for (; Borka.getWeight() < Borka.maximumWeight; Borka.feed(2.3)) ;
            System.out.println(Borka.getStatus());
        }

        Cat Murka = new Cat();
        {
            for (; Murka.getWeight() >= Murka.minimumWeight; Murka.meow()) ;
            System.out.println(Murka.getStatus());
        }

        Cat Kitty = new Cat();
        {
            System.out.println("Вес Kitty: " + Kitty.getWeight() + "грамм");
            Kitty.toilet();
            Kitty.feed(5.3);
            System.out.println("Вес Kitty: " + Kitty.getWeight() + "грамм");
        }

        Cat Finik = new Cat();
        {
            System.out.println("Вес Finik: " + Finik.getWeight() + "грамм");
            Finik.toilet();
            System.out.println("Вес Finik: " + Finik.getWeight() + "грамм");
            Finik.feed(1.2);
            Finik.feed(3.5);
            System.out.println("Котенок скушал " + Finik.getFoodWeight() + " грамм корма");
            System.out.println("Вес Finik: " + Finik.getWeight() + "грамм");
        }

    }
}