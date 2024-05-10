package org.rubrum.service;

import java.io.File;
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

    public File findJavaSourceFolder() {
        for (File f: Objects.requireNonNull(getCurrentDirectory().listFiles())) {
          if(f.isDirectory() && f.getName().equals(expectedSourceFolderName())) return f;
        }
        return null;
    }


}
