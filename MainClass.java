package HomeWork5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch cdlStart = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch cdlEnd = new CountDownLatch(CARS_COUNT);
    public static final CyclicBarrier cbPrep = new CyclicBarrier(CARS_COUNT+1);
    public static final Semaphore smpTunnel = new Semaphore(CARS_COUNT/2);
    public static final Lock lockWin = new ReentrantLock();



    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cdlStart, cdlEnd, cbPrep, smpTunnel, lockWin);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cdlStart.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            cbPrep.await();
            cdlEnd.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}



