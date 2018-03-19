package com.iblotus;

import java.util.concurrent.TimeUnit;

/**
 * Created by xiezhiyan on 3/20/18.
 */
public class Chef implements Runnable {

    private Restaurant r;
    private int count = 0;

    public Chef(Restaurant r){
        this.r = r;
    }

    public void run() {
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    while (r.meal != null){
                        wait();
                    }
                }
                if(++count == 10){
                    System.out.println("Out of food. closing");
                    r.exec.shutdownNow();
                }
                System.out.print("Order up! ");
                synchronized (r.waitPerson){
                    r.meal = new Meal(count);
                    r.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}
