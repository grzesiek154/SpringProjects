package com.trainings_notebook.backend.exceptions;

public class SpringNotebookException extends RuntimeException {

    public SpringNotebookException(String message) {
        super(message);
    }

    public SpringNotebookException(String message, Throwable cause) {
        super(message, cause);
    }
}
