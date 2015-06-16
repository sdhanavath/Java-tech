package edu.adias.java.tech.multithread.c;

//SynchronizationDemo2.java
class SynchronizationDemo2
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
private String transName;
private double amount;
synchronized void update (String transName, double amount)
{
   this.transName = transName;
   this.amount = amount;
   System.out.println (this.transName + " " + this.amount);
}
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
       if (getName ().equals ("Deposit Thread"))
          ft.update ("Deposit", 2000.0);
       else
          ft.update ("Withdrawal", 250.0);
}

}

