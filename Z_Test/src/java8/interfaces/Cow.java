package java8.interfaces;


public class Cow implements Animal, Life
{

	@Override
	public void eat()
	{
		System.out.println("Cow eats only Veg");
	}

	@Override
	public void foodDetail()
	{
		Life.super.foodDetail();
	}

}
