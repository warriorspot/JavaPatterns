
import java.awt.EventQueue;

public class Main implements ChangeListener, Runnable {

	private ValueHolder valueHolder;
	private QuitButtonExample ex;

	public Main() {
		valueHolder = new ValueHolder();
        ex = new QuitButtonExample();                                     
		valueHolder.addDependent(ex);
		valueHolder.addDependent(this);
	}
	
	public static void main(String [] args) {
		Main main = new Main();
		EventQueue.invokeLater(main);                                                   
	
		int i = 0;
		try {
			while(i <= 5) {
				Thread.sleep(1000);
				main.getValueHolder().setValue(String.valueOf(i));
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

    @Override                                                                               
    public void run() {                                                                     
        ex.setVisible(true);                                                                
    }                                                                                       

	public void update(Object value) {
		System.out.println("Value: " + value.toString());
	}

	public ValueHolder getValueHolder() {
		return this.valueHolder;
	}

}
