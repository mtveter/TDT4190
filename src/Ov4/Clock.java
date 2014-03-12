package Ov4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
Timer timer;
Resource resource;
	
	public Clock(long milliseconds, Resource resource){
		this.resource = resource;
		timer = new Timer();  //At this line a new Thread will be created
		timer.schedule(new RemindTask(), milliseconds); //delay in milliseconds
	}
	
	 class RemindTask extends TimerTask {
	        public void run() {
	            resource.unlock(resource.getLockOwner()); //prevents creating new customers.
	            System.out.println("timer started");
	            timer.cancel();
	        }
	 }
	 
	 public static String getTime(){
		// get current time
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime()) ;
	}
}
