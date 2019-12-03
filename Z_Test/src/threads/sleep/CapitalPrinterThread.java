package threads.sleep;

public class CapitalPrinterThread implements Runnable
{

	@Override
	public void run()
	{
		String capitalStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < capitalStr.length(); i++)
		{
			synchronized (Lock.LOCK_OBJ)
			{
				System.out.print(capitalStr.charAt(i));
				try
				{
					Thread.sleep(200);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

	}
}
