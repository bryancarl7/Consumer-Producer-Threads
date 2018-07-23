/**
 * @author Bryan Carl
 */
import java.util.concurrent.*;
import java.util.Random;

public class Producer implements Runnable {
        private final Buffer sharedLocation;
	
    //Constructor to setup sharedLocation
	public Producer(Buffer sharedLocation) {
		this.sharedLocation = sharedLocation;
        }
	
    //Runnable
	@Override
	public void run() {
        //Produce a thousand units to consume
		for (int i=0;i<1000;i++){

                    //Put in a blockingPut request
                    try{
                        sharedLocation.blockingPut(produce());
                    } 

                    //Print That Stack Trace, Baby
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    //Reporting "x" events 
                    if (i % 100 == 0)
                        System.out.println("Producer 1: "+i+" events produced");
                }
	}
        //Factor out a random doube function
        String produce(){
            return Double.valueOf(Math.random()).toString();
        }
}
