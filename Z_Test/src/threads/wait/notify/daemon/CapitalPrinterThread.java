package threads.wait.notify.daemon;

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
					if(i == (capitalStr.length()-1))
					{
						Lock.wait = false;
						Lock.LOCK_OBJ.notify();
					}
					else if (i % 4 == 0)
					{
						Thread.sleep(200);
						Lock.LOCK_OBJ.notify();
						Lock.LOCK_OBJ.wait();
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

	}
}
