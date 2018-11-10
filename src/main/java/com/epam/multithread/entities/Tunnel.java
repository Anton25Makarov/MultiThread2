package com.epam.multithread.entities;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tunnel implements Runnable {
    private static final int THREAD_POOL_SIZE = 3;
    private static final int WAITING_TIME_FOR_TRAIN = 1;
    private Queue trainQueue;
    private ExecutorService trains;

    public Tunnel(Queue trainQueue) {
        this.trainQueue = trainQueue;
        this.trains = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    @Override
    public void run() {
        Train train;
        Train lastTrain = null;
        while (true) {
            train = trainQueue.pull();

            if (train == null) {
                break;
            }

            if (lastTrain != null && lastTrain.getTrainDirection()
                    != train.getTrainDirection()) {// wait until the trains pass the tunnel
                try {
                    trains.awaitTermination(WAITING_TIME_FOR_TRAIN, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            trains.execute(train);
            lastTrain = train;
        }

        trains.shutdown();
    }

    @Override
    public String toString() {
        return "Tunnel{" +
                "trainQueue=" + trainQueue +
                ", trains=" + trains +
                '}';
    }
}
