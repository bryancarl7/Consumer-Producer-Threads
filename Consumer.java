/**
 * @author Bryan Carl
 */
import java.util.concurrent.*;
import java.util.Random;

public class Consumer implements Runnable{   
        private int count = 0,name;

    //Create a chared location
	private final Buffer sharedLocation;
	
    //Setup constructer with associated number
	public Consumer(Buffer sharedLocation, int number) {
		this.sharedLocation = sharedLocation;
                this.name = number;
	}	
    
    //Consumable thread itself    
    @Override
	public void run() {
            while (true){
                try {
                    //Initialize the random each time
                    Random random = new Random();

                    //Wait for values to come in
                    Thread.sleep(random.nextInt(10));

                    //Try the blockingGet() and increment
                    String holder = sharedLocation.blockingGet();
                    count++;

                    //If block to report every 100 units
                    if (count % 100 == 0)
                        System.out.println("Consumer "+name+": "+count+" events consumed");

                    //Check if it's done
                    if (sharedLocation.isDone())
                        break;
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            //Give final report
            System.out.println("Consumer "+name+": "+count+" events consumed");
        }
}
