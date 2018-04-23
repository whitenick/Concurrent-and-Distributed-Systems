import java.util.concurrent.locks.ReentrantLock;

public class Node {
    String name;
    int priority;
    Node next;
    ReentrantLock nodeLock = new ReentrantLock();

    public Node(String nodeName, int priority) {
        this.name = nodeName;
        this.priority = priority;
    }
}
