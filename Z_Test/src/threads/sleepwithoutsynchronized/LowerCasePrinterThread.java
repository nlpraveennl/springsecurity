package threads.sleepwithoutsynchronized;

public class LowerCasePrinterThread implements Runnable
{

	@Override
	public void run()
	{
		String capitalStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerStr = capitalStr.toLowerCase();

		for (int i = 0; i < lowerStr.length(); i++)
		{
			System.out.print(lowerStr.charAt(i));
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
