package com.epam.multithread.logic;

public class TrainValidator {
    private static final String NUMBER_PATTERN = "[-]?\\d+\\.?\\d*";

    public boolean isValid(String train) {

        String values[] = train.split(" ");

        if (values.length != 4) {
            return false;
        }

        if (!values[0].matches(NUMBER_PATTERN)) {
            return false;
        }

        if (!isDirectionValid(values[3])) {
            return false;
        }

        return true;
    }

    private boolean isDirectionValid(String string) {
        switch (string) {
            case "west":
                return true;
            case "east":
                return true;
            default:
                return false;
        }
    }
}
