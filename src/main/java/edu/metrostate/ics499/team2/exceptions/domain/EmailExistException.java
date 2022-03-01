package edu.metrostate.ics499.team2.exceptions.domain;

public class EmailExistException extends Exception {
    public EmailExistException(String message) {
        super(message);
    }
}
