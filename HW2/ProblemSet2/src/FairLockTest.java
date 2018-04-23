
public class FairLockTest {
    public static void main(String[] args) {
        // define global object for threads to access for functions

        FairReadWriteLock lockClass = new FairReadWriteLock();
        FairThread thread1 = new FairThread(lockClass, true, 4);
        FairThread greatWriter = new FairThread(lockClass, false, 5);
        FairThread read1 = new FairThread(lockClass, true, 2);
        FairThread writer1 = new FairThread(lockClass, false, 3);
        FairThread thread2 = new FairThread(lockClass, false, 1);

        Thread superThread1 = new Thread(thread1);
        Thread superGreat = new Thread(greatWriter);
        Thread superThread2 = new Thread(thread2);
        Thread superReader = new Thread(read1);
        Thread superWriter = new Thread(writer1);

        superThread1.start();
        superGreat.start();
        superReader.start();
        superWriter.start();
        superThread2.start();

        try{
            superThread1.join();
            superGreat.join();
            superReader.join();
            superWriter.join();
            superThread2.join();
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(lockClass.counter);

    }
}
