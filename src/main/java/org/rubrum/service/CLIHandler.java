package org.rubrum.service;

import java.text.NumberFormat;
import java.util.Scanner;

public class CLIHandler {

    Scanner scanner;


    public CLIHandler() {
        scanner = new Scanner(System.in);
    }

    public int startupMessage() {
        System.out.println("Welcome to the auto doc generator");
        System.out.println("Please enter one of the following options");
        System.out.println("1: Generate new Graph version");
        System.out.println("2: Generate new Markdown (MD) document file of the graph");
        try {
            return Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException exception) {
            System.out.println("Could not process option selected. Please input a valid integer listed in options");
            return startupMessage();
        }
    }



}
