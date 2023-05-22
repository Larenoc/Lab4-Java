package Program;

import java.util.concurrent.Semaphore;

class DiningPhilosophers {
    public static void main(String[] args) throws InterruptedException {
        int numberOfPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];
        Semaphore[] forks = new Semaphore[numberOfPhilosophers];
        Semaphore waiter = new Semaphore(numberOfPhilosophers - 1);

        for (int i = 0; i < numberOfPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < numberOfPhilosophers; i++) {
            philosophers[i] = new Philosopher(waiter, forks, i + 1);
            Thread t = new Thread(philosophers[i]);
            t.start();
        }

        Thread.sleep(10000);

        for (Philosopher philosopher : philosophers) {
            philosopher.interrupt(); // Зупиняємо роботу філософів
        }
    }
}