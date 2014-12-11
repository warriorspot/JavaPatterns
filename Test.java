
import java.util.*;
import java.lang.reflect.*;

public class Test {

	public boolean quit;
	
	public Test() {
		quit = false;
	}

	public static void main(String [] args) {
		Test t = new Test();
		Bar b = new Bar();

		NotificationCenter center = NotificationCenter.sharedInstance();
		center.addObserver(t, "backgroundDone", "foo");

		System.out.println("Start long running task");
		b.doBackgroundThing();
		
		while(t.quit == false) {
			try {	
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void foo(Notification n) {
		System.out.println("Background thing is done");
		quit = true;
	}
}

class Bar {

	public void doBackgroundThing() {
		Thread t = new Thread(new Baz());
		t.start();
		synchronized(t) {
			try {
        		t.wait();
        	} catch(InterruptedException e){
            	e.printStackTrace();
        	}
		
			NotificationCenter center = NotificationCenter.sharedInstance();
			center.postNotification("backgroundDone", null);
		}
	}

}

class Baz implements Runnable {
	
	public void run() {
		int i = 0;

		synchronized(this) {
			while(i < 10) {
				i++;
				try {
					Thread.sleep(200);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println(i);
			}
			notify();
		}
	}
}	
