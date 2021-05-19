package code;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Semaphore;
import java.util.Random;

public class Philosopher implements Runnable{
	private final int MAX_FORK = 5;
	private int idPhilo;
	static Fork[] fork;
	static Fork mutex = new Fork();
	
	public Philosopher(int id) {
		this.idPhilo = id;
		fork = new Fork[MAX_FORK];
	}
	public int getIdPhilo() {
		return idPhilo;
	}

	public void setIdPhilo(int idPhilo) {
		this.idPhilo = idPhilo;
	}	
	
	synchronized void eat() {
		Random rand = new Random();
		int time = rand.nextInt(2000) + 1;
		try {
			System.out.println("Philosopher " + this.idPhilo + " is eating...");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
	synchronized void think() {
		Random rand = new Random();
		int time = rand.nextInt(2000) + 1;
		try {
			System.out.println("Philosopher " + this.idPhilo + " is thinking...");
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}
	@Override
	public void run() {
		while (true) {
			mutex.waitt();
			
			System.out.println("Philosopher " + idPhilo + " is picking up the left fork");
			fork[idPhilo].waitt();
			System.out.println("Philosopher " + idPhilo + " is picking up the right fork");
			fork[(idPhilo + 1) % 5].waitt();
			
			mutex.signal();
			eat();
			
			System.out.println("Philosopher " + idPhilo + " is putting down the left fork");
			fork[idPhilo].signal();
			System.out.println("Philosopher " + idPhilo + " is putting down the right fork");
			fork[(idPhilo + 1) % 5].signal();
			
			think();
			
		}
		
	}
}
