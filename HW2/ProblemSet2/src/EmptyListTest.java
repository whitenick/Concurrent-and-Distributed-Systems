import static org.junit.Assert.*;

import org.junit.Test;

public class EmptyListTest {

	public static void main(String[] args) {
		PriorityQueue q = new PriorityQueue(2);
		PriorityThread th1 = new PriorityThread(q, "Thread 1", 4, true);
		PriorityThread th3 = new PriorityThread(q, "Thread 3", 2, true);
		PriorityThread th5 = new PriorityThread(q, "Thread 5", 9, true);
		new Thread(th1).start();
		new Thread(th3).start();
		new Thread(th5).start();
		System.out.println(q.size + " " + q.queueCount);
		
		System.out.println(q.getFirst());
		//q.add("Test thread", 9);
		//System.out.print(q.toString());
		//System.out.println(q.getFirst());
		
		//new Thread(th1).start();
		//q.add("Thread test", 1);
		
		
		
		/*System.out.println(q.getFirst());
		try {
			Thread.sleep(2000);
			new Thread(th1).start();
		}catch (Exception e){
			 e.printStackTrace();
		}
		//new Thread(th1).start();
		
		System.out.println(q.getFirst());*/
		
	}

}
