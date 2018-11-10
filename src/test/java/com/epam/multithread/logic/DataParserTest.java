package com.epam.multithread.logic;

import com.epam.multithread.entities.Train;
import com.epam.multithread.entities.TrainDirection;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataParserTest {

    private static final String VALID_STRING_FIRST = "1 Brest Minsk east";
    private static final String VALID_STRING_SECOND = "2 Minsk Brest west";
    private static final Train FIRST_TRAIN =
            new Train(1, "Brest", "Minsk", TrainDirection.EAST);
    private static final Train SECOND_TRAIN =
            new Train(2, "Minsk", "Brest", TrainDirection.WEST);

    @Test
    public void shouldReturnListOfTrainsWhenStringsAreCorrect() {
        // given
        DataParser dataParser = new DataParser();

        List<String> stringList = new ArrayList<>();
        stringList.add(VALID_STRING_FIRST);
        stringList.add(VALID_STRING_SECOND);

        List<Train> expectedTrains = new ArrayList<>();
        expectedTrains.add(FIRST_TRAIN);
        expectedTrains.add(SECOND_TRAIN);

        // when
        List<Train> actualTrains = dataParser.parseTrains(stringList);

        // then
        Assert.assertEquals(expectedTrains, actualTrains);
    }

}