package threads.yield;

public class LowerCasePrinterThread implements Runnable
{

	@Override
	public void run()
	{
		String capitalStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerStr = capitalStr.toLowerCase();

		for (int i = 0; i < lowerStr.length(); i++)
		{
			if (Lock.wait)
			{
				Thread.yield();
			}
			
			synchronized (Lock.LOCK_OBJ)
			{
				System.out.print(lowerStr.charAt(i));
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
