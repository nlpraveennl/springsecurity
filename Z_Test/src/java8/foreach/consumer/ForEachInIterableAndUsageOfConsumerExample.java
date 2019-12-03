package java8.foreach.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ForEachInIterableAndUsageOfConsumerExample
{
	public static void main(String[] args)
	{
		List<String> list = new ArrayList<>();
		list.add("1");list.add("2");list.add("3");list.add("4");
		list.forEach((x) -> {
			System.out.println(x);
		});
		
		list.forEach(new StringConsumer(5));
	}
}

//Consumer implementation that can be reused
class StringConsumer implements Consumer<String>
{

	private int x;

	public StringConsumer(int x)
	{
		this.x = x;
	}
	public void accept(String t)
	{
		System.out.println(x + ": Consumer impl Value::" + t);
	}
}
