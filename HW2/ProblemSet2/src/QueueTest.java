public class QueueTest {
    public static PriorityQueue queue = new PriorityQueue(10);
    public static void main(String[] args) {
        PriorityThread th1 = new PriorityThread(queue, "Thread 1", 1, true);
        PriorityThread th2 = new PriorityThread(queue, "Thread 2", 5, true);
        PriorityThread th3 = new PriorityThread(queue, "Thread 3", 9, true);
        PriorityThread clr1 = new PriorityThread(queue, "Clear 1", 0, false);
        PriorityThread th4 = new PriorityThread(queue, "Thread 4", 8, true);
        PriorityThread th5 = new PriorityThread(queue, "Thread 5", 2, true);
        PriorityThread clr2 = new PriorityThread(queue, "Clear 1", 0, false);

        Thread TH1 = new Thread(th1);
        Thread TH2 = new Thread(th2);
        Thread TH3 = new Thread(th3);
        Thread CLR1 = new Thread(clr1);
        Thread TH4 = new Thread(th4);
        Thread TH5 = new Thread(th5);
        Thread CLR2 = new Thread(clr2);


        TH1.start();
        TH2.start();
        TH3.start();
        CLR1.start();
        TH4.start();
        TH5.start();
        //CLR2.start();

        try {
            TH1.join();
            TH2.join();
            TH3.join();
            CLR1.join();
            TH4.join();
            TH5.join();
            //CLR2.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        print();

    }

    public static synchronized void print() {
        Node tempNode = queue.head;

        while(tempNode != null) {
            System.out.println("Name: "+tempNode.name+" Priority: "+tempNode.priority);
            tempNode = tempNode.next;
        }
    }
}
