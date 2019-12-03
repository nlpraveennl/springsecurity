package threads.wait.notify.daemon;


public class MainClass
{
	public static void main(String[] args)
	{
		CapitalPrinterThread cap = new CapitalPrinterThread();
		LowerCasePrinterThread low = new LowerCasePrinterThread();
		
		Thread thCap = new Thread(cap);
		Thread thLow = new Thread(low);
		
		thLow.setDaemon(true);
		
		System.out.println("Starting threads");
		thCap.start();
		thLow.start();
		System.out.println("Finished threads");
	}
}
