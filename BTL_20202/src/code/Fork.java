package code;
//import java.util.concurrent.Semaphore;

public class Fork{
	private int value;
	private int id;
	
	public Fork(int i) {
		this.id = i;
		this.value = 1;
	}
	public Fork() {
		this.value = 1;
	}
	void waitt() {
		while (this.value <= 0);
		this.value--;
	}
	
	void signal() {
		this.value++;
	}
}
