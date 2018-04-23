public class FairReadWriteLock {
	int writers = 0;
	int readers = 0;
	int counter = 0;
	int nextThread = 0;
                        
	public synchronized void beginRead() {
		int threadID = counter;
		counter++;

		while(writers > 0 || threadID!=nextThread) {
			try{
				wait();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		nextThread++;
		readers++;
	}
	
	public synchronized void endRead() {
		readers--;
		notifyAll();
	}
	
	public synchronized void beginWrite() {
		int threadID = counter;
		counter++;

		while(readers > 0 || writers > 0 || threadID!=nextThread) {
			try{
				wait();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		nextThread++;
		writers++;
	}

	public synchronized void endWrite() {
		writers--;
		notifyAll();
	}
}
	
