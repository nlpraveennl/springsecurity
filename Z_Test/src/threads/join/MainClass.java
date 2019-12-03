package threads.join;


public class MainClass
{
	public static void main(String[] args) throws InterruptedException
	{
		CapitalPrinterThread cap = new CapitalPrinterThread();
		LowerCasePrinterThread low = new LowerCasePrinterThread();
		
		Thread thCap = new Thread(cap);
		thCap.setPriority(1);
		Thread thLow = new Thread(low);
		thCap.setPriority(2);
		
		System.out.println("Starting threads");
		thCap.start();
		thLow.start();
		thCap.join();
		thLow.join();
		System.out.println("\nFinished threads");
	}
}
