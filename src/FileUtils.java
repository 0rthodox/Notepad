package ru.mipt.collections;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

public class FileUtils {
    public static List<String> readAll(String path) {
        List<String> readLines = new ArrayList<>();
        try (BufferedReader istream = new BufferedReader(new FileReader(path))){
            while (istream.ready()) {
                readLines.add(istream.readLine());
            }
        } catch (FileNotFoundException noFile) {
            throw new IllegalArgumentException("At FileUtils.readAll while reading " + path + ": " + noFile.getMessage());
        } catch (IOException ioEx) {
            throw new UncheckedIOException("At FileUtils.readAll while reading " + path + ": ", ioEx);
        }
        return readLines;
    }
    /* читаем все строки из файла по пути path и возвращаем этот список */
    public static boolean writeAll(String path, List<String> lines) {
        if (path == null) {
            System.out.println("Path is null");
            return false;
        }
        try(FileWriter ostream = new FileWriter(path, false)) {
            for (String line : lines) {
                ostream.write(line);
                ostream.append(lineSeparator());
            }
            ostream.flush();
        } catch (IOException writingException) {
            System.out.println("Exception happened: " + writingException.getMessage());
            return false;
        }
        return true;

    }
    /*пишем все строки из lines в файла по пути path */
    public static boolean copy(String sourceFile, String destinationFile) {
        return writeAll(destinationFile, readAll(sourceFile));
    }
    /*копируем файл по пути sourceFile в destinationFile*/
    public static boolean delete(String path) {
        if (path == null) {
            return false;
        }
        File toDelete = new File(path);
        return (toDelete.delete());
    }
    /*удаляем файл по пути path*/


}
