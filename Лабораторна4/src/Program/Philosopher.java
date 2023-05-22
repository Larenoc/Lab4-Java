package Program;

import java.util.concurrent.Semaphore;

class Philosopher implements Runnable {
    private Semaphore waiter;
    private Semaphore[] forks;
    private int id;

    public Philosopher(Semaphore waiter, Semaphore[] forks, int id) {
        this.waiter = waiter;
        this.forks = forks;
        this.id = id;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println("Philosopher " + id + " " + action);
        Thread.sleep((long) (Math.random() * 100));
    }

    public void run() {
        try {
            while (true) {
                doAction(": Thinking");

                waiter.acquire(); // Філософ запитує офіціанта

                forks[id].acquire(); // Філософ бере ліву виделку
                forks[(id + 1) % forks.length].acquire(); // Філософ бере праву виделку

                doAction(": Eating");

                forks[(id + 1) % forks.length].release(); // Філософ повертає праву виделку
                forks[id].release(); // Філософ повертає ліву виделку

                waiter.release(); // Філософ повертає дозвіл офіціанту
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

	public void interrupt() {
		// TODO Auto-generated method stub
		
	}
}




