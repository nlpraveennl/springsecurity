package threads.yield;


public class MainClass
{
	public static void main(String[] args) throws InterruptedException
	{
		NumberPrinterThread num = new NumberPrinterThread();
		CapitalPrinterThread cap = new CapitalPrinterThread();
		LowerCasePrinterThread low = new LowerCasePrinterThread();
		
		Thread thNum = new Thread(num);
		Thread thCap = new Thread(cap);
		Thread thLow = new Thread(low);
		
		System.out.println("Starting threads");
		thNum.start();
		thCap.start();
		thLow.start();
		System.out.println("\nFinished threads");
	}
}
