package java7.diamond.operator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Test
{

	public static void main(String[] args)
	{
		Animal<String> cat = new Cat<>("Cat");
		System.out.println(cat.feed());
		Animal<String> dog = new Dog<>("dog");
		System.out.println(dog.feed());

		Animal<Integer> cat2 = new Cat<>(1);
		System.out.println(cat2.feed());
		Animal<Integer> dog2 = new Dog<>(2);
		System.out.println(dog2.feed());

		Set<? extends Object> SetOfObject = new HashSet<String>();
		
		HashMap<String, Set<Integer>> contacts = newContacts();
	}

	public static <K, V> HashMap<K, V> newContacts()
	{
		return new HashMap<K, V>();
	}
}

interface Animal<E extends Object>
{

	E feed();
}

class Cat<E> implements Animal<E>
{

	private E elemt;

	public Cat(E e)
	{
		this.elemt = e;
	}

	@Override
	public E feed()
	{
		return this.elemt;
	}
}

class Dog<E> implements Animal<E>
{

	private E elemt;

	public Dog(E e)
	{
		this.elemt = e;
	}

	@Override
	public E feed()
	{
		return this.elemt;
	}
}

interface Bird
{
}

class Pegion implements Bird
{
}

class Crow implements Bird
{
}
