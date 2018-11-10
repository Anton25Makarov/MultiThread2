package com.epam.multithread.logic;

import com.epam.multithread.entities.Train;
import com.epam.multithread.entities.TrainDirection;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrainCreatorTest {
    private static final String FILE_PATH_WITH_INCORRECT_LINES = "src/test/resources/trainsWithIncorrectLines.txt";
    private static final Train TRAIN =
            new Train(1, "Minsk", "Brest", TrainDirection.WEST);

    @Test
    public void shouldCreateAndReturnArrayListOfTrainsFromFileWhenFileIsExist() {
        // given
        TrainCreator trainCreator = new TrainCreator();

        List<Train> expectedTrains = new ArrayList<>();
        expectedTrains.add(TRAIN);

        // when
        List<Train> actualListOfCubes = trainCreator.createTrains(FILE_PATH_WITH_INCORRECT_LINES);

        // then
        Assert.assertEquals(expectedTrains, actualListOfCubes);
    }
}