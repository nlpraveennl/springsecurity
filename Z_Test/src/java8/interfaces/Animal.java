package java8.interfaces;

public interface Animal
{
	void eat();
	
	default void foodDetail()
	{
		System.out.println("In interface Animal food detail");
	}
	
	default void volumeDetail()
	{
		System.out.println("In interface Animal volume details");
	}
	
//	default String toString()
//	{
//		return "lll";
//	}
	
	static void something()
	{
		System.out.println("static method");
	}
}
