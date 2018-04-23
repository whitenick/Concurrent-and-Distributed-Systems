/*
 * EID's of group members
 * Daniel John - DCJ597
 * Nick White - NWW295
 */

public class MonitorThreadSynch {
	int partyNumber;
	Object monitor = new Object();
	int counter = 0;
	public MonitorThreadSynch(int parties) {
		partyNumber = parties;
	}
	
	public synchronized int await() throws InterruptedException {
           //int index = 0;
		
			counter++;
			if(counter == partyNumber) {
				counter = 0;
				notifyAll();
				return 0;
			}else {
				this.wait();
				return partyNumber-counter;
			}
		
           
          
	    
	}
}
