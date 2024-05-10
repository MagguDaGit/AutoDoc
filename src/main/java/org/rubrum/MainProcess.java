package org.rubrum;

import org.rubrum.service.DirectoryHandler;
import org.rubrum.service.FileScanner;

import java.io.File;

public class MainProcess {
    FileScanner fileIterator = new FileScanner();
    DirectoryHandler directoryHandler = new DirectoryHandler();



    public void start(String pathToDirectory) {
    fileIterator.traverseDirectory(new File(pathToDirectory));
    }

    public void start() {
        File sourceFolder = directoryHandler.findJavaSourceFolder();
        System.out.println("Found source folder: " + sourceFolder.getAbsolutePath());
        fileIterator.traverseDirectory(new File(sourceFolder.getAbsolutePath()));
    }




}
