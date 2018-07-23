/**
 * @author Bryan Carl
 */

//Introducing a buffer class for Synchronized buffer to rewrite
public interface Buffer {
	//Put function for blocking
    public void blockingPut(String value) throws InterruptedException;
    
    //Getter function that will have an overwritten blocking function
    public String blockingGet() throws InterruptedException;
    
    //Checking if it's done
    public boolean isDone() throws InterruptedException;
}
