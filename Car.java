package HomeWork5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch cdlStart;
    private CountDownLatch cdlEnd;
    private CyclicBarrier cbPrep;
    private Semaphore smpTunnel;
    private Lock lockWin;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Semaphore getSmpTunnel() {
        return smpTunnel;
    }

    public Car(Race race, int speed, CountDownLatch cdlStart, CountDownLatch cdlEnd, CyclicBarrier cbPrep, Semaphore smpTunnel, Lock lockWin) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cdlStart = cdlStart;
        this.cdlEnd = cdlEnd;
        this.cbPrep = cbPrep;
        this.smpTunnel = smpTunnel;
        this.lockWin = lockWin;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdlStart.countDown();
            cbPrep.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (lockWin.tryLock()) {
            System.out.println(this.name + " WIN");
        }
        cdlEnd.countDown();
    }
}
