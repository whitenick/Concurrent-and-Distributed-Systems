import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityQueue {
	int size;
	ReentrantLock headLock = new ReentrantLock();
	Condition isFull = headLock.newCondition();
	Condition isEmpty = headLock.newCondition();
	Node head;
	int queueCount = 0;


	public PriorityQueue(int maxSize) {
		this.size = maxSize;
	}

	public int add(String name, int priority) {

		int index = 0;

		Node newNode = new Node(name, priority);

		if((head == null) || (queueCount == size)) {
			boolean headAdd = false;
			headLock.lock();
			try {
				if(head == null) {
					head = newNode;   // initialize head of linked list if not already
					queueCount++;
					isEmpty.signalAll();
					headAdd = true;
				}
				while (queueCount == size) {
					isFull.await();                // if the linked list capacity is full, let the thread wait
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				headLock.unlock();
			}
			if(headAdd) {
				return 0;
			}
		}
			// Adds the name with its priority to this queue.
			// Returns the current position in the list where the name was inserted;
			// otherwise, returns -1 if the name is already present in the list.
			// This method blocks when the list is full.
		Node tempNode = head;
		Node lockNode = head;
		boolean addSuccess = false;
		while(tempNode != null) {
			tempNode.nodeLock.lock();
			try {
				if (tempNode.name == newNode.name) {
					tempNode.nodeLock.unlock();
					addSuccess = true;
					index = -1;			// tempNode is list head, if string name is in list already, return
				}
				else if ((tempNode.priority >= newNode.priority) && (tempNode.next == null)) {
					tempNode.next = newNode;
					queueCount++;
					addSuccess = true;
					index++;
				}
				else if ((tempNode.priority < newNode.priority) && (tempNode == head)) {
					head = newNode;
					newNode.next = tempNode;
				}

				else if ((tempNode.priority >= newNode.priority) && (tempNode.next.priority < newNode.priority)) {
					Node rightNode = tempNode.next;
					rightNode.nodeLock.lock();
					try {
						Node tempNext = tempNode.next;
						tempNode.next = newNode;
						newNode.next = tempNext;// to be done. replace the node in the list. put it before current node
						queueCount++;
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						rightNode.nodeLock.unlock();
					}
					addSuccess = true;
					index++;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				tempNode.nodeLock.unlock();
			}
			if(addSuccess) {
				return index;
			} else {
				tempNode = tempNode.next;        // else put it after current node
				index++;
			}
		}


		// this is to let getFirst know that there's at least something in the linked list
		return index;
	}


	public int search(String name) {
        // Returns the position of the name in the list;
        Node newNode = new Node(name, 0);
        Node temp = head;
		int counter = 0;
        while(temp != null) {
        	if (temp.name == newNode.name) {
        		return counter;
			}
			else {
        		temp = temp.next;
        		counter++;
			}
		}
		return counter;
	}

	public String getFirst() {
		String returnString = null;
		if((queueCount == size) || (queueCount == 0)) {
			headLock.lock();
			try {
				while (queueCount == 0) {
					isEmpty.await();
				}
				returnString = head.name;
				head = head.next;
				queueCount--;
				isFull.signalAll();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				headLock.unlock();
			}
		} else {
			Node tempHead = head;
			tempHead.nodeLock.lock();
			try {
				returnString = head.name;
				head = head.next;
				queueCount--;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				tempHead.nodeLock.unlock();
			}
		}


		return returnString;
	}
}

