
public class Loader
{
    public static void main(String[] args)
    {
        Cat Petka = new Cat();
        Petka.getWeight();
        System.out.println(Petka.getWeight());

        Cat Borka = new Cat();
        for (;Borka.getWeight() < Borka.maxWeight;Borka.feed(2.3));
        System.out.println(Borka.getStatus());

        Cat Murka = new Cat();
        for (;Murka.getWeight() >= Murka.minimumWeight;Murka.meow());

        System.out.println(Murka.getStatus());

    }
}