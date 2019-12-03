package collections.list;

import java.util.Stack;

public class StackEx
{
	public static void main(String[] args)
	{
		Stack<String> stack = new Stack<>();
		stack.push("Praveen");
		System.out.println(stack);
		stack.push("Vedanta");
		System.out.println(stack);
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
//		System.out.println(stack.pop());
	}
}
