package java7.staticblock;

class NoMain
{
	public static int count = 10;
	static
	{
		System.out.println("Static block");
	}
}

public class StaticBlockWithoutMainMethod
{
	static
	{
		System.out.println("Static block");
	}
}
