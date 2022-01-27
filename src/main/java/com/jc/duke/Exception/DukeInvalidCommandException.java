package com.jc.duke.Exception;

/**
 * DukeInvalidCommandException handles invalid commands for Duke.
 */

public class DukeInvalidCommandException extends Exception {
    //no other code needed
    public DukeInvalidCommandException(String message){
        super(message);
    }
}