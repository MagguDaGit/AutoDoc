package org.rubrum;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.rubrum.service.*;

import java.io.File;

public class MainProcess {

    CLIHandler cliHandler = new CLIHandler();
    FileScanner fileIterator = new FileScanner();
    DirectoryHandler directoryHandler = new DirectoryHandler();

    MetaDataParser parser = new MetaDataParser();

    MetaDataCollector collector = new MetaDataCollector();



    public void start(String pathToDirectory) {
    fileIterator.traverseDirectory(new File(pathToDirectory), parser,collector);
    }

    public void start()  {
        //Required setup/check of existing directory to store files created
        File sourceFolder = directoryHandler.findJavaSourceFolder();
        directoryHandler.createMetaDataDirectory();
        int operation = cliHandler.startupMessage();
        switch (operation) {
            case 1 -> {
                System.out.println("Scanning files for OGM annotations....");
                fileIterator.traverseDirectory(new File(sourceFolder.getAbsolutePath()), parser, collector);
                System.out.println("Finished scanning");
                boolean major = collector.doesCurrentIncludeMajorChanges();
                System.out.println("metadata marked with " + (major ? "major" : "minor") + " changes");
                collector.saveMetadata(directoryHandler.getMetaDirectory().getAbsolutePath(), major);
            }
            case 2 -> {
                double selectedVersion = cliHandler.selectGraphVersionToGenerate(directoryHandler);
                File metaDataFile = directoryHandler.getMetaDataFileByVersion(selectedVersion);
                System.out.println("Processing: " + metaDataFile.getName());
                System.out.println("Not yet implemented");

            }
            default -> {
                System.out.println("The selected option does not exist. Please select a option with the number in range of 1-3");
                start();
            }
        }


    }




}
