public class DeluxeBurger extends Hamburger
{
    public DeluxeBurger()
    {
        super("Deluxe", "Sausage", 14.54,"white");
        super.addHamburgerAddition("Chips",2.75);
    }

    @Override
    public void addHamburgerAddition(String name, double price)
    {
        System.out.println("Can\'t add additional items to a deluxe burger");
    }
}
