package code;
public class Test{
	public static void main(String args[]) {
		Philosopher philosopher_1 = new Philosopher(0);
		Philosopher philosopher_2 = new Philosopher(1);
		Philosopher philosopher_3 = new Philosopher(2);
		Philosopher philosopher_4 = new Philosopher(3);
		Philosopher philosopher_5 = new Philosopher(4);
		
		for (int i = 0; i < 5; i++) {
			Philosopher.fork[i] = new Fork(i);
		}
		
		Thread thread_1 = new Thread(philosopher_1);
		Thread thread_2 = new Thread(philosopher_2);
		Thread thread_3 = new Thread(philosopher_3);
		Thread thread_4 = new Thread(philosopher_4);
		Thread thread_5 = new Thread(philosopher_5);
		
		thread_1.start();
		thread_2.start();
		thread_3.start();
		thread_4.start();
		thread_5.start();
	}
}