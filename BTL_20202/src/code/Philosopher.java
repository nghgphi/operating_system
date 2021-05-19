package code;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Semaphore;
import java.util.Random;

public class Philosopher implements Runnable{
	final int MAX_FORK = 5;
	private int idPhilo;
	static Fork[] fork;
	static Fork mutex;
	
	public Philosopher(int id) {
		this.idPhilo = id;
		fork = new Fork[MAX_FORK];
		mutex = new Fork();
	}
	public int getIdPhilo() {
		return idPhilo;
	}

	public void setIdPhilo(int idPhilo) {
		this.idPhilo = idPhilo;
	}	
	
	void eat() {
		Random rand = new Random();
		int time = rand.nextInt(200) + 100;
		try {
			System.out.println("Philosopher " + this.idPhilo + " is eating...");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
	void think() {
		Random rand = new Random();
		int time = rand.nextInt(200) + 100;
		try {
			System.out.println("Philosopher " + this.idPhilo + " is thinking...");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
	@Override
	public void run() {
		while (true) {
			mutex.waitt();
			
			fork[idPhilo].waitt();
			System.out.println("Philosopher " + idPhilo + " is picking up the left fork");
			fork[(idPhilo + 1) % 5].waitt();
			System.out.println("Philosopher " + idPhilo + " is picking up the right fork");
			
			mutex.signal();
			eat();
			
		
			fork[idPhilo].signal();
			System.out.println("Philosopher " + idPhilo + " is putting down the left fork");
			fork[(idPhilo + 1) % 5].signal();
			System.out.println("Philosopher " + idPhilo + " is putting down the right fork");
			
			think();
		}
		
	}
}
