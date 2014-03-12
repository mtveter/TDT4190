package Ov4;

public class Timeren
{
	public Timeren() {
		long wait = 500;
		try	{
			Thread.sleep(wait);
		} catch (InterruptedException ie)	{}
		System.out.println("halla");
	}
}
