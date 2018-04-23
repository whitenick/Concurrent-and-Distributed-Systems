/*
 * EID's of group members
 * Daniel John - DCJ597
 * Nick White - NWW295
 * 
 */
import java.util.concurrent.Semaphore; // for implementation using Semaphores
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.SSLContext;
import javax.rmi.CORBA.Util;

public class ThreadSynch {
	int partyNumber;
	Semaphore s;
	Semaphore cs;
	Semaphore count;
	//Semaphore rel;
	AtomicInteger atomicCounter;
	int counter = 0;
	int round = 0;
	
	public ThreadSynch(int parties) {
		partyNumber = parties;
		s = new Semaphore(0);
		cs = new Semaphore(1);
	
		//count = new Semaphore(1);
		//rel = new Semaphore(1);
	}
	
	public int await() throws InterruptedException {
        //System.out.println("Available permits: " + s.availablePermits());
		
		cs.acquire();	//critical section starts
		
		//count.acquire();

		counter++;
		//System.out.println("Counter is: " + counter); - debug statement
		int index = partyNumber - counter;
		
		/*if(counter == partyNumber) {
			counter = 0;
			cs.release();
			s.release(partyNumber);
		}else {
			cs.release();
			s.acquire();
		}*/
		
		
		if(counter < partyNumber) {
			//System.out.println("Waiting on last thread"); - debug statement
			
		cs.release();
		s.acquire();
		}else {
		//System.out.println("Last thread here"); - debug statement
		//counter = 0;
		round++;
		counter = 0;
		cs.release();
		s.release(partyNumber);
		//count.acquire();
		//counter = 0;
		//count.release();
		}
		s = new Semaphore(0);
		return index;
		
		
		
		
			
			
		}
		
		
}
