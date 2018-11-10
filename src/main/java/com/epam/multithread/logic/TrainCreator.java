package com.epam.multithread.logic;

import com.epam.multithread.entities.Train;
import com.epam.multithread.exception.ReadFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class TrainCreator {
    private static final Logger LOGGER = LogManager.getLogger(TrainCreator.class.getName());

    public List<Train> createTrains(String filePath) {
        FileReader fileReader = new FileReader();
        List<String> stringList = new ArrayList<>();
        try {
            stringList = fileReader.readLines(filePath);
        } catch (ReadFileException e) {
            LOGGER.error(e);
        }
        DataParser dataParser = new DataParser();
        return dataParser.parseTrains(stringList);
    }
}
