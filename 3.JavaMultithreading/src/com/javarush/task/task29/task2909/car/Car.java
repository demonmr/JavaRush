package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }
  public static Car create(int type, int numberOfPassengers)
    {
        Car car=null;
        switch (type)
        {
            case TRUCK:car= new Truck(numberOfPassengers);break;
            case SEDAN:car= new Sedan(numberOfPassengers);break;
            case CABRIOLET:car= new Cabriolet(numberOfPassengers);break;

        }
        return car;
    }
    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;

    }
   public boolean isSummer(Date date , Date summerStart, Date summerEnd)
   {
       if (date.getTime()>=summerStart.getTime()&&date.getTime()<=summerEnd.getTime())
           return true;
       else
           return false;
   }
    public double getWinterConsumption(int length)
    {
        return (winterFuelConsumption*length)+winterWarmingUp;
    }
    private boolean canPassengersBeTransferred()
    {
      if (isDriverAvailable()&&fuel!=0)
          return true;
          else
              return false;
    }
    public double getSummerConsumption(int length)
    {
        return (summerFuelConsumption*length);
    }
    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
      if (isSummer(date,SummerStart,SummerEnd))
      {  consumption = getSummerConsumption(length);
        } else {
            consumption = getWinterConsumption(length);
        }
        return consumption;
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (!canPassengersBeTransferred())
            return 0;
        else
        return numberOfPassengers;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
        fastenDriverBelt();
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed() ;
}