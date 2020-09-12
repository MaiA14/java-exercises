public class HealthyBurger extends Hamburger
{
    private String healthyExtraName;
    private double healthyExtraPrice;

    public HealthyBurger(String meat, double price)
    {
        super("Healthy",meat,price,"Brown rye");
    }

    public void addHealthAdditon(String name, double price)
    {
        this.healthyExtraName = name;
        this.healthyExtraPrice = price;
    }

    @Override
    public double itemizeHamburger()
    {
        double hamburgerPrice =  super.itemizeHamburger();
        if (this.healthyExtraName != null)
        {
            hamburgerPrice += this.healthyExtraPrice;
            System.out.println("Added " + this.healthyExtraName + " for an extra " + this.healthyExtraPrice);
        }

        return hamburgerPrice;
    }
}
