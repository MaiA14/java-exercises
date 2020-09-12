class Car
{
    private boolean engine;
    private int cylinders;
    private String name;
    private int wheels;

    public Car(int cylinders, String name)
    {
        this.engine = true;
        this.cylinders = cylinders;
        this.name = name;
        this.wheels = 4;
    }

    private int getCylinders()
    {
        return cylinders;
    }

    private String getName()
    {
        return name;
    }

    public String startEngine()
    {
        return "Car -> startEngine()";
    }

    public String accelerate()
    {
        return "Car -> accelerate()";
    }

    public String brake()
    {
        return "Car -> brake()";
    }
}

class Mitsuishi extends Car
{
    public Mitsuishi(int cylinders, String name)
    {
        super(cylinders, name);
    }

    @Override
    public String startEngine()
    {
        return "Mitsuishi -> startEngine()";
    }

    @Override
    public String accelerate()
    {
        return "Mitsuishi -> accelerate()";
    }

    @Override
    public String brake()
    {
        return "Mitsuishi -> brake()";
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Car car = new Car(8,"Base car");
        System.out.println(car.startEngine());
        System.out.println(car.accelerate());
        System.out.println(car.brake());

        Mitsuishi mitsuishi = new Mitsuishi(8,"Mitsuishi car");
        System.out.println(mitsuishi.startEngine());
        System.out.println(mitsuishi.accelerate());
        System.out.println(mitsuishi.brake());
    }
}
