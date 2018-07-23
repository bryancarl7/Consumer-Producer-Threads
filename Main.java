/**
 * @author Bryan Carl
 */

import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        
        //Executor thread service to maintain a threadpool
        ExecutorService executor = Executors.newCachedThreadPool();
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(100);
        Buffer sharedLocation = new SynchronizedBuffer(queue);
        
        //Startup the Producer and then consumer threads
		executor.submit(new Producer(sharedLocation));
                executor.submit(new Consumer(sharedLocation,1));
                executor.submit(new Consumer(sharedLocation,2));
		executor.shutdown();
		//Always exit
    }
}