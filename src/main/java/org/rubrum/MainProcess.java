package org.rubrum;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.rubrum.service.DirectoryHandler;
import org.rubrum.service.FileScanner;
import org.rubrum.service.MetaDataCollector;
import org.rubrum.service.MetaDataParser;

import java.io.File;

public class MainProcess {
    FileScanner fileIterator = new FileScanner();
    DirectoryHandler directoryHandler = new DirectoryHandler();

    MetaDataParser parser = new MetaDataParser();

    MetaDataCollector collector = new MetaDataCollector();



    public void start(String pathToDirectory) {
    fileIterator.traverseDirectory(new File(pathToDirectory), parser,collector);
    }

    public void start() throws JsonProcessingException {
        File sourceFolder = directoryHandler.findJavaSourceFolder();
        directoryHandler.createMetaDataDirectory();
        System.out.println("Found source folder: " + sourceFolder.getAbsolutePath());
        fileIterator.traverseDirectory(new File(sourceFolder.getAbsolutePath()),parser, collector );
        System.out.println("Finished folder scanning");
        System.out.println(collector.convertMetadataToJson());
        boolean major = collector.doesCurrentIncludeMajorChanges();
        collector.saveMetadata(directoryHandler.getMetaDirectory().getAbsolutePath(), major);

    }




}
