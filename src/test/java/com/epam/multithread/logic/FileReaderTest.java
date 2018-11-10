package com.epam.multithread.logic;

import com.epam.multithread.exception.ReadFileException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileReaderTest {
    private static final String FILE_PATH = "src/test/resources/trainsWithCorrectLines.txt";
    private static final String FILE_PATH_NONEXISTENT = "src/test/resources/notExist.txt";
    private static final String FIRST_LINE = "1 Brest Minsk west";
    private static final String SECOND_LINE = "2 Minsk Brest east";
    private FileReader dataReader = new FileReader();

    @Test
    public void shouldReturnLinesFromFileWhenFileIsExist() throws ReadFileException {
        // given
        List<String> exampleTestArrayList = new ArrayList<>();
        exampleTestArrayList.add(FIRST_LINE);
        exampleTestArrayList.add(SECOND_LINE);

        // when
        List<String> listOfStrings = dataReader.readLines(FILE_PATH);

        // then
        Assert.assertEquals(exampleTestArrayList, listOfStrings);
    }

    @Test(expected = ReadFileException.class)
    public void shouldThrowReadFileExceptionWhenFileIsNotExist() throws ReadFileException {
        List<String> listOfStrings = dataReader.readLines(FILE_PATH_NONEXISTENT);
    }
}