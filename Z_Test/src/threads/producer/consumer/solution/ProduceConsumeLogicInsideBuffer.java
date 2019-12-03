package threads.producer.consumer.solution;

import java.util.LinkedList;

public class ProduceConsumeLogicInsideBuffer
{

	public static void main(String[] args) throws InterruptedException
	{
		final PC pc = new PC();

		// Create producer thread
		Thread producer = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					pc.produce();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		// Create consumer thread
		Thread consumer = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					pc.consume();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		// Start both threads
		producer.start();
		consumer.start();

		// producer finishes before consumer
		producer.join();
		consumer.join();
	}

	public static class PC
	{

		LinkedList<Integer>	list		= new LinkedList<>();
		int					capacity	= 2;

		public void produce() throws InterruptedException
		{
			int value = 0;
			while (true)
			{
				synchronized (this)
				{
					while (list.size() == capacity)
						wait();

					System.out.println("Producer produced - " + value);

					list.add(value++);

					notify();
					Thread.sleep(1000);
				}
			}
		}

		public void consume() throws InterruptedException
		{
			while (true)
			{
				synchronized (this)
				{
					while (list.size() == 0)
					{
						wait();
					}

					int val = list.removeFirst();
					System.out.println("Consumer consumed - " + val);

					notify();
					Thread.sleep(1000);
				}
			}
		}
	}
}
