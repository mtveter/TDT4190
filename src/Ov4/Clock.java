package Ov4;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of timeout for locking of resources
 * Creates a timer which works as a count-down with a task that is run at timeout
 */
public class Clock {
	/**
	 * The timer that handles counting, tasks and the implemented timeout
	 */
	private Timer timer;
	/**
	 * The transaction which is associated with the timer
	 */
	private Transaction transaction;
	/**
	 * Creates a new transaction object.
	 *
	 * @param milliseconds 	The amount of ms a transaction is allowed to lock a resource(timeout)
	 * @param transation  	The transaction which the timer is associated with.
	 */
	public Clock(long milliseconds, Transaction transaction){
		this.transaction = transaction;
		timer = new Timer();  // At this line a new Thread will be created
		timer.schedule(new RemindTask(), milliseconds); //scheduled timeout
	}
	/**
	 * A task is run at the schedule timeout specified in clock
	 */
	 class RemindTask extends TimerTask {
	        public void run() {
	        	// Aborts the transaction and notifies the transaction
	            if(!transaction.getCommitTransaction()) {
	            	System.out.println("going for the abortion");
	            	transaction.abort();
	            	transaction.setAbortTransaction(true);
	            }
	            timer.cancel();
	        }
	 }
}
