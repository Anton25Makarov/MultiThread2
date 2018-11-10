package com.epam.multithread;

import com.epam.multithread.entities.Queue;
import com.epam.multithread.entities.Train;
import com.epam.multithread.entities.TrainConsumer;
import com.epam.multithread.entities.TrainProducer;
import com.epam.multithread.logic.TrainCreator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrainCreator trainCreator = new TrainCreator();
        List<Train> trainList = trainCreator.createTrains("src/main/resources/exampleTrains.txt");

        Queue queue = Queue.getInstance();

        TrainProducer trainProducer = new TrainProducer(queue, trainList);

        TrainConsumer trainConsumer = new TrainConsumer(queue);

        Thread thread = new Thread(trainProducer);

        trainConsumer.consuming();

        thread.start();
    }
}
