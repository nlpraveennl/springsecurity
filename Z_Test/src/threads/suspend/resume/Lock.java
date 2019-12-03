package threads.suspend.resume;


public class Lock
{
	public static final Object LOCK_OBJ = new Object();
	public static boolean suspended = false;
	
	public static void suspend()
	{
		suspended = true;
		synchronized (LOCK_OBJ)
		{
			LOCK_OBJ.notify();	
		}
	}
	
	public static void resume()
	{
		System.out.println("Resuming");
		suspended = false;
		synchronized (LOCK_OBJ)
		{
			LOCK_OBJ.notify();	
		}
	}
}
