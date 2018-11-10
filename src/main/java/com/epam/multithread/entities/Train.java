package com.epam.multithread.entities;


import java.util.concurrent.TimeUnit;

public class Train implements Runnable {
    private long id;
    private String stationFrom;
    private String stationTo;
    private TrainDirection trainDirection;

    public Train(long id, String stationFrom, String stationTo, TrainDirection TrainDirection) {
        this.id = id;
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.trainDirection = TrainDirection;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) { // just for imitate the movement of trains
            System.out.println("Tunnel: " + Thread.currentThread().getName() +
                    ". Train: " + this + ". Distance: " + ((double) i / (3 - 1)));
            sleepOn(300);
        }
        System.out.println("Done: " + this);
    }

    private void sleepOn(int milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public TrainDirection getTrainDirection() {
        return trainDirection;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", trainDirection=" + trainDirection +
                '}';
    }
}
