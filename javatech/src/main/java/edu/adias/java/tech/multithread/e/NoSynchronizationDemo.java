package edu.adias.java.tech.multithread.e;

//NoSynchronizationDemo.java
class NoSynchronizationDemo
{
public static void main (String [] args)
{
   FinTrans ft = new FinTrans ();
   TransThread tt1 = new TransThread (ft, "Deposit Thread");
   TransThread tt2 = new TransThread (ft, "Withdrawal Thread");
   tt1.start ();
   tt2.start ();
}
}
class FinTrans
{
public static String transName;
public static double amount;
}
class TransThread extends Thread
{
private FinTrans ft;
TransThread (FinTrans ft, String name)
{
   super (name); // Save thread's name
   this.ft = ft; // Save reference to financial transaction object
}
public void run ()
{
   for (int i = 0; i < 2; i++)
   {
        if (getName ().equals ("Deposit Thread"))
        {
            synchronized (this)
            {
               ft.transName = "Deposit";
               try
               {
                  Thread.sleep ((int) (Math.random () * 1000));
               }
               catch (InterruptedException e)
               {
               }
               ft.amount = 2000.0;
               System.out.println (ft.transName + " " + ft.amount);
            }
        }
        else
        {
            synchronized (this)
            {
               ft.transName = "Withdrawal";
               try
               {
                  Thread.sleep ((int) (Math.random () * 1000));
               }
               catch (InterruptedException e)
               {
               }
               ft.amount = 250.0;
               System.out.println (ft.transName + " " + ft.amount);
            }
        }
   }
}
}

