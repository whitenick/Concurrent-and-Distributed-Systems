public class PriorityThread implements Runnable {
    String name;
    int priority;
    PriorityQueue mainQueue;
    boolean toAdd;

    public PriorityThread(PriorityQueue queue, String name, int  number, boolean add) {
        this.name = name;
        this.priority = number;
        this.mainQueue = queue;
        this.toAdd = add;
    }

    public void run() {
        if(toAdd) {
            int returnInt = mainQueue.add(name, priority);
        }
        else {
            mainQueue.getFirst();
        }
    }
}
