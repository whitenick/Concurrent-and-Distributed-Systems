import java.util.*;
import java.io.*;

public class FairThread implements Runnable {
    FairReadWriteLock fairLock;
    boolean readThread;
    int sec;

    public FairThread(FairReadWriteLock lockObj, boolean read, int sec) {
        this.fairLock = lockObj;
        this.readThread = read;
        this.sec = sec*1000;
    }

    public void run() {
        if(readThread) {
            fairLock.beginRead();
            System.out.println("Reader thread owns lock");
            try {
                Thread.sleep(sec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fairLock.endRead();
            System.out.println("Reader thread Done");
        } else {
            fairLock.beginWrite();
            System.out.println("Writer thread owns lock");
            try {
                Thread.sleep(sec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fairLock.endWrite();
            System.out.println("Writer thread Done");
        }

    }
}
