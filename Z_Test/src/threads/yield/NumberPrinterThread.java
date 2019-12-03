package threads.yield;

public class NumberPrinterThread implements Runnable
{

	@Override
	public void run()
	{
		String numberStr = "0123456789";
		for (int i = 0; i < numberStr.length(); i++)
		{
			synchronized (Lock.LOCK_OBJ)
			{
				System.out.print(numberStr.charAt(i));
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

		Lock.wait = false;
	}
}
