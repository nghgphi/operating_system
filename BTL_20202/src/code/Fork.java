package code;
import java.util.concurrent.Semaphore;

public class Fork{
	private int idFork;
	Semaphore sem = new Semaphore(1);
	
	
	public Fork(int id) {
		this.setIdFork(id);
	}

	public int getIdFork() {
		return idFork;
	}

	public void setIdFork(int idFork) {
		this.idFork = idFork;
	}
}
