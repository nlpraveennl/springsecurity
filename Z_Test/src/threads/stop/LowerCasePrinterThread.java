package threads.stop;

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
				if(i == 3)
				{
					Lock.LOCK_OBJ.notify();
					Thread.currentThread().stop();
				}
				
				System.out.print(lowerStr.charAt(i));
				try
				{
					Thread.sleep(100);
					
					if(i == (capitalStr.length()-1))
					{
						Lock.LOCK_OBJ.notify();
					}
					else
					{
						Thread.sleep(100);
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
