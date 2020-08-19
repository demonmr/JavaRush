package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    private Thread thread;
    private Thread.State state;
   public LoggingStateThread(Thread thread)
   {
       this.thread=thread;
   }
   public void run()
   {
       while (true)
       {if (state!=thread.getState ())
       {state=thread.getState ();
           System.out.println (this.thread.getState ().name ());}
           if (state==State.TERMINATED)
               break;
       }

   }
}
