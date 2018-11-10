package com.epam.multithread.logic;

import org.junit.Assert;
import org.junit.Test;

public class TrainValidatorTest {
    private static final String VALID_STRING = "1 Brest Minsk east";

    @Test
    public void shouldReturnTrueWhenStringIsValid() {
        // given
        TrainValidator trainValidator = new TrainValidator();

        // when
        boolean result = trainValidator.isValid(VALID_STRING);

        // then
        Assert.assertTrue(result);
    }
}