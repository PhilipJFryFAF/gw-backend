package com.faforever.gw.exceptions;

public class SemanticsException extends RuntimeException {
    public SemanticsException(String message){
        super("There was a semantical error: "+message);
    }
}
