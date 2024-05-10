package org.rubrum.service;

import java.io.File;
import java.io.IOException;

public class FileScanner {

    MetaDataParser parser = new MetaDataParser();
    public void traverseDirectory(File directory) {
        // Get all files and directories inside the current directory
        File[] files = directory.listFiles();

        // Iterate through each file and directory
        for (File file : files) {
            // If it's a directory, recursively call the method on it
            if (file.isDirectory()) {
                traverseDirectory(file);
            } else {
                // If it's a file, parse lines
                scanFile(file);
            }
        }


    }

    /**
     * When we are certain that the file object is a file and not a directory we want to gather the properties.
     * @param file
     */
    public void scanFile(File file) {
        try {
            parser.parseFile(file);
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
