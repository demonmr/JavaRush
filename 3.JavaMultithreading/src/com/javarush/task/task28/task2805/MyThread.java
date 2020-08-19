package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread{
   private static AtomicInteger priority = new AtomicInteger (Thread.MIN_PRIORITY);
    public MyThread () {
        super ();
        Priority();
    }

    public MyThread (Runnable target) {
        super (target);
        Priority();
    }

    public MyThread (ThreadGroup group, Runnable target) {
        super (group, target);
        Priority();
    }

    public MyThread (String name) {
        super (name);
        Priority();
    }

    public MyThread (ThreadGroup group, String name) {
        super (group, name);
        Priority();
    }

    public MyThread (Runnable target, String name) {
        super (target, name);
        Priority();
    }

    public MyThread (ThreadGroup group, Runnable target, String name) {
        super (group, target, name);
        Priority();
    }

    public MyThread (ThreadGroup group, Runnable target, String name, long stackSize) {
        super (group, target, name, stackSize);
        Priority();
    }

    private void Priority()
    {
        int groupPriority = currentThread ().getThreadGroup ().getMaxPriority ();
        int tmp = priority.getAndIncrement ();
        if (tmp<Thread.MAX_PRIORITY&&tmp<groupPriority)
        {
            this.setPriority (tmp);
        }
        if (tmp==Thread.MAX_PRIORITY)
        {
            this.setPriority (tmp);
            priority.set (1);
        }

    }
}

