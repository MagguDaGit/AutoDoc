package org.rubrum.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class DirectoryHandler {


    boolean isWindows;

    public DirectoryHandler() {
        this.isWindows = System.getProperty("os.name").startsWith("Windows");
    }


    private String expectedSourceFolderName() {
       return "src";
    }


    public File getCurrentDirectory() {
        return new File(System.getProperty("user.dir"));
    }

    public File getMetaDirectory() {
        return new File(getCurrentDirectory() + "/docs/meta");
    }


    public void createMetaDataDirectory() {
        try {
            boolean exists = getMetaDirectory().exists();
            if(!exists) {
                System.out.println("No docs / meta data folder found. Creating these....");
                Files.createDirectories(Paths.get(getMetaDirectory().getAbsolutePath()));
            }
        }
        catch (IOException ioException) {
            System.out.println("Error checking/ creating file structure for meta data to be stored: " + ioException.getMessage());
        }

    }

    public File findJavaSourceFolder() {
        for (File f: Objects.requireNonNull(getCurrentDirectory().listFiles())) {
          if(f.isDirectory() && f.getName().equals(expectedSourceFolderName())) return f;
        }
        return null;
    }


    public void createMetaDataFolder() {

    }


}
