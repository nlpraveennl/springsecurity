package collections.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQCapRestr
{

	public static void main(String args[])
	{
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
		queue.add(4);
		queue.add(2);
		queue.add(3);
		queue.add(1);
		queue.add(5);
		try
		{
			queue.add(6);	
		}
		catch (Exception e) 
		{
			System.out.println(e.getLocalizedMessage());
		}
		System.out.println(queue.offer(6));
		
		System.out.println(queue);
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue);
		System.out.println(queue.peek());
//		System.out.println(queue.poll());
		
		try
		{
			queue.element();	
		}
		catch (Exception e) 
		{
			System.out.println("queue.element()"+e.getLocalizedMessage());
		}
		
		try
		{
			queue.remove();	
		}
		catch (Exception e) 
		{
			System.out.println("queue.remove()"+e.getLocalizedMessage());
		}
		
		queue.add(null);
	}
}
