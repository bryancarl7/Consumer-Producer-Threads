/**
 * @author Bryan Carl
 */
import java.util.Iterator;
import java.util.concurrent.*;

//SynchronizedBuffer class to implement/overwrite the Buffer Class
public class SynchronizedBuffer implements Buffer {
    //Setup buffer/flag variables
    private String buffer;
    private boolean occupied = false;
    private boolean done = false;
    private LinkedBlockingQueue<String> queue;
    
    //use the blank linkedBlockingQueue
    public SynchronizedBuffer(LinkedBlockingQueue<String> buffer){
        queue = buffer;
    }
    
    //Overwrite blockigPut to run/wait
    @Override
    public synchronized void blockingPut(String value) throws InterruptedException{
        while (occupied){
            wait();
        }
        buffer = value;
        occupied = true;
        notifyAll();
    }

    //Overwrite blockigGet to run/wait
    @Override
    public synchronized String blockingGet() throws InterruptedException{
       while (!occupied){
           wait();
       }
       occupied = false;
       notifyAll();
       return buffer;
    }
    
    //Have the global checker.
    @Override
    public boolean isDone(){
        done = queue.isEmpty();
        if (done)
            return true;
        else 
            return false;
    }
}
