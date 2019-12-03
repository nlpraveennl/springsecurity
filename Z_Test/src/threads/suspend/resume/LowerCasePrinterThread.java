package threads.suspend.resume;

public class LowerCasePrinterThread implements Runnable
{

	@Override
	public void run()
	{
		String capitalStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerStr = capitalStr.toLowerCase();

		for (int i = 0; i < lowerStr.length();)
		{
			try
			{
				Thread.sleep(100);
				if (i == 3)
				{
					Lock.suspend();
				}

				while (Lock.suspended)
				{
					synchronized (Lock.LOCK_OBJ)
					{
						Lock.LOCK_OBJ.notify();
						Lock.LOCK_OBJ.wait();
					}
					Thread.sleep(50);
					System.out.println("suspended");
				}
				
				synchronized (Lock.LOCK_OBJ)
				{
					System.out.print(lowerStr.charAt(i++));
					Lock.LOCK_OBJ.notify();
					if (i != capitalStr.length() && Thread.activeCount() > 2)
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
