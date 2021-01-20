import java.util.Scanner;

public class Cat
{

    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;
    private double foodWeight;
    private double fullfoodWeight;
    private double eatWeight;
    
    double maximumWeight = maxWeight;
    double minimumWeight = minWeight;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;

    }


    public void meow()
    {

        weight = weight - 1;
        System.out.println("Meow");
    }

    public void toilet()
    {
        weight = weight - 0.5;
        System.out.println("oops!");
    }


    public void feed(Double amount)
    {
        foodWeight = amount;
        weight = weight + amount;
        fullfoodWeight = fullfoodWeight + amount;
    }


    public void drink(Double amount)
    {
        foodWeight = amount;
        weight = weight + amount;
    }

    public Double getFoodWeight()
    {
        return fullfoodWeight;
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}