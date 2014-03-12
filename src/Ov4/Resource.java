package Ov4;

/**
 * A resource with an associated lock that can be held by only one transaction at a time.
 */
class Resource
{
	/**
	 * The clock which specifies how long a resource can be locked, and performs time out
	 */
	private Clock clock = null;
	
  static final int NOT_LOCKED = -1;

  /**
   * The transaction currently holding the lock to this resource
   */
  private int lockOwner;
  

  /**
   * Creates a new resource.
   */
  Resource()
  {
    lockOwner = NOT_LOCKED;
  }

  /**
   * Gives the lock of this resource to the requesting transaction. Blocks
   * the caller until the lock could be acquired.
   *
   * Each time a lock is acquired, a new clock is initiated with the given timeout interval
   * and assigned to the clock of this resource
   *
   * @param transactionId The ID of the transaction that wants the lock.
   * @return Whether or not the lock could be acquired.
   */
  synchronized boolean lock(int transactionId)
  {

    if (lockOwner == transactionId) {
      System.err.println("Error: Transaction " + transactionId + " tried to lock a resource it already has locked!");
      return false;
    }
    
    while (lockOwner != NOT_LOCKED) {
      try {
        wait();
      } catch (InterruptedException ie) {
      }
    }

    lockOwner = transactionId;
	this.clock = new Clock(Globals.TIMEOUT_INTERVAL, this);
    return true;
  }

  /**
   * Releases the lock of this resource.
   *
   * @param transactionId The ID of the transaction that wants to release lock.
   *                      If this transaction doesn't currently own the lock an
   *                      error message is displayed.
   * @return Whether or not the lock could be released.
   */
  synchronized int unlock(int transactionId)
  {
    if (lockOwner == NOT_LOCKED) {
      //System.err.println("Error: Transaction " + transactionId + " tried to unlock a resource without owning the lock!");
      return 0;
    } else if (lockOwner != transactionId) {
    	return -1;
    }

    lockOwner = NOT_LOCKED;
    // Notify a waiting thread that it can acquire the lock
    notifyAll();
    return 1;
  }

  /**
   * Gets the current owner of this resource's lock.
   *
   * @return An Integer containing the ID of the transaction currently
   * holding the lock, or NOT_LOCKED if the resource is unlocked.
   */
  synchronized int getLockOwner()
  {
    return lockOwner;
  }

  /**
   * Unconditionally releases the lock of this resource.
   */
  synchronized void forceUnlock()
  {
    lockOwner = NOT_LOCKED;
    // Notify a waiting thread that it can acquire the lock
    notifyAll();
  }

  /**
   * Checks if this resource's lock is held by a transaction running on the specified server.
   *
   * @param serverId The ID of the server.
   * @return Whether or not the current lock owner is running on that server.
   */
  synchronized boolean isLockedByServer(int serverId)
  {
    return lockOwner != NOT_LOCKED && ServerImpl.getTransactionOwner(lockOwner) == serverId;
  }
}
