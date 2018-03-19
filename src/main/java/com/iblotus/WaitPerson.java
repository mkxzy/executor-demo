package com.iblotus;

/**
 * Created by xiezhiyan on 3/20/18.
 */
public class WaitPerson implements Runnable {

    private Restaurant r;

    public WaitPerson(Restaurant r){
        this.r = r;
    }

    public void run() {
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    while (r.meal == null){
                        wait();
                    }
                }
                System.out.println("Waitperson got " + r.meal);
                synchronized (r.chef){
                    r.meal = null;
                    r.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }

    }
}
