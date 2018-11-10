package com.epam.multithread.logic;

import com.epam.multithread.exception.ReadFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<String> readLines(String filePath) throws ReadFileException {
        List<String> stringList = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(filePath), String.valueOf(StandardCharsets.UTF_8))) {
            while (scanner.hasNextLine()) {
                stringList.add(scanner.nextLine());
            }
        } catch (NoSuchFileException e) {
            throw new ReadFileException("Can not find file", e);
        } catch (IOException e) {
            throw new ReadFileException("I/O Exception", e);
        }
        return stringList;
    }
}
