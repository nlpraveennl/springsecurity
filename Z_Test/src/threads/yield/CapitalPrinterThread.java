package threads.yield;

public class CapitalPrinterThread implements Runnable
{

	@Override
	public void run()
	{
		String capitalStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < capitalStr.length(); i++)
		{
			if (Lock.wait)
			{
				Thread.yield();
			}
			synchronized (Lock.LOCK_OBJ)
			{
				System.out.print(capitalStr.charAt(i));
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
	}
}
