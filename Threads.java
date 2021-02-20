package HomeWork4;


public class Threads {

    int flag = 0;

    public synchronized void methodA() throws InterruptedException {

        while (flag != 0) {
            wait();
        }
        flag = 1;
        System.out.print("A");
        notifyAll();
    }

    public synchronized void methodB() throws InterruptedException {
        while (flag != 1) {
            wait();
        }
        flag = 2;
        System.out.print("B");
        notifyAll();
    }

    public synchronized void methodC() throws InterruptedException {
        while (flag != 2) {
            wait();
        }
        flag = 0;
        System.out.print("C");
        notifyAll();
    }

    public static void main(String[] args) {
        Threads threads = new Threads();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    threads.methodA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    threads.methodB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    threads.methodC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
