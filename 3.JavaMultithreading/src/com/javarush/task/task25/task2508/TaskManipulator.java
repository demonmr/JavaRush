package com.javarush.task.task25.task2508;

import static java.lang.Thread.sleep;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread thread;
    @Override
    public void run() {
        while (!thread.isInterrupted ())
        {
            try {
                System.out.println (thread.getName ());
                sleep (100);
            } catch (InterruptedException e) {
                Thread.currentThread ().interrupt ();
            }
    }
    }

    @Override
    public void start (String threadName) {
        Thread threads=new Thread (this);
        threads.setName (threadName);
        this.thread = threads;
        this.thread.start ();
    }

    @Override
    public void stop () {
        thread.interrupt();
    }
}
