package com.epam.multithread.entities;

import java.util.List;

public class TrainProducer implements Runnable {
    private Queue queue;
    private List<Train> trains;

    public TrainProducer(Queue queue, List<Train> trains) {
        this.queue = queue;
        this.trains = trains;
    }

    @Override
    public void run() {
        for (Train train : trains) {
            queue.push(train);
        }
    }
}
