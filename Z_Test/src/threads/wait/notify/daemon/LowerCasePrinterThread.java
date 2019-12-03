package threads.wait.notify.daemon;

public class LowerCasePrinterThread implements Runnable
{

	@Override
	public void run()
	{
		String capitalStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerStr = capitalStr.toLowerCase();
		
		for (int i = 0; i < lowerStr.length(); i++)
		{
			synchronized (Lock.LOCK_OBJ)
			{
				System.out.print(lowerStr.charAt(i));
				try
				{
					Thread.sleep(200);
					Lock.LOCK_OBJ.notify();
					if(Lock.wait)
					{
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
