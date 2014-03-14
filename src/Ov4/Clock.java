package Ov4;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of timeout for locking of resources
 * Creates a timer which works as a count-down with a task that is run at timeout
 */
public class Clock {
	Transaction t;
	/**
	 * The timer that handles counting, tasks and the implemented timeout
	 */
	private Timer timer;
	/**
	 * The resource which is associated with the timer
	 */
	private Resource resource;
	/**
	 * Creates a new transaction object.
	 *
	 * @param milliseconds 	The amount of ms a transaction is allowed to lock a resource(timeout)
	 * @param resource  	The resources which the timer is associated with.
	 */
	public Clock(long milliseconds, Transaction t){
		this.t = t;
		timer = new Timer();  //At this line a new Thread will be created
		timer.schedule(new RemindTask(), milliseconds); //scheduled timeout
	}
	/**
	 * A task is run at the schedule timeout specified in clock
	 */
	 class RemindTask extends TimerTask {
	        public void run() {
	            timer.cancel();
	        }
	 }
}
