package java8.interfaces;


public class Dog implements Animal, Life
{

	@Override
	public void eat()
	{
		System.out.println("Dog eats both veg and non veg");
	}

	@Override
	public void foodDetail()
	{
		System.out.println("Fod details of Dog");
	}

}
