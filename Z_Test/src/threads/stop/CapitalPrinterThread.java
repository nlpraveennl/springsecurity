package threads.stop;

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
						Lock.LOCK_OBJ.notify();
					}
					else
					{
						Thread.sleep(100);
						Lock.LOCK_OBJ.notify();
						System.out.println(Thread.currentThread().activeCount());
						if(Thread.currentThread().getThreadGroup().activeCount() > 2)
						{
							Lock.LOCK_OBJ.wait();
						}
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
