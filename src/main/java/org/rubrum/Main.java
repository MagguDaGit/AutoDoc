package org.rubrum;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Starting AutoDoc CLI....");
        MainProcess mainProcess = new MainProcess();
        mainProcess.start();


        System.out.println("AutoDoc CLI finished");

    }
}