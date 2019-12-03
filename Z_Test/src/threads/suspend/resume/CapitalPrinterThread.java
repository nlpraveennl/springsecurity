package threads.suspend.resume;

public class CapitalPrinterThread implements Runnable
{

	@Override
	public void run()
	{
		String capitalStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < capitalStr.length();)
		{
			System.out.print(capitalStr.charAt(i++));
			try
			{
				Thread.sleep(100);
				synchronized (Lock.LOCK_OBJ)
				{
					Lock.LOCK_OBJ.notify();
					if (i != capitalStr.length())
						Lock.LOCK_OBJ.wait();
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		Lock.resume();

	}
}
