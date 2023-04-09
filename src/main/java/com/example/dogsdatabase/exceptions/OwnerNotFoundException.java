package com.example.dogsdatabase.exceptions;

public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(Long id) {
        super("Author with id " + id + " not found!");
    }
}
