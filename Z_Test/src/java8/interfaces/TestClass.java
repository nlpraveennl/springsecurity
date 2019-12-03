package java8.interfaces;

public class TestClass
{
	public static void main(String[] args)
	{
		Animal animal = new Dog();
		animal.eat();
		
		animal.foodDetail();
		
		Animal animal2 = new Cow();
		animal2.eat();
		animal2.foodDetail();
		Animal.something();
		
	}
}
