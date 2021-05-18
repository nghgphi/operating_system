package code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class Philosopher implements Runnable{
	private int idPhilo;
	static List<Fork> fork;
	static Semaphore mutex = new Semaphore(1);
	
	public Philosopher(int id) {
		this.idPhilo = id;
		fork = new ArrayList<Fork>();
	}
	public int getIdPhilo() {
		return idPhilo;
	}

	public void setIdPhilo(int idPhilo) {
		this.idPhilo = idPhilo;
	}	
	
	void eat() {
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
	void think() {
		Random rand = new Random();
		int time = rand.nextInt(2000) + 1;
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
		// TODO Auto-generated method stub
		try {
			while (true) {
				mutex.acquire();
				
				System.out.println("Philosopher " + idPhilo + " is picking up the left fork");
				fork.get(idPhilo).sem.acquire();
				System.out.println("Philosopher " + idPhilo + " is picking up the right fork");
				fork.get((idPhilo + 1) % 5).sem.acquire();
				
				mutex.release();
				eat();
				
				System.out.println("Philosopher " + idPhilo + " is putting down the left fork");
				System.out.println("Philosopher " + idPhilo + " is putting down the right fork");
				fork.get(idPhilo).sem.release();
				fork.get((idPhilo + 1) % 5).sem.release();
				
				think();
				
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
		
	}
}
