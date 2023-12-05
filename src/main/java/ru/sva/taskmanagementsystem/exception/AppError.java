package ru.sva.taskmanagementsystem.exception;

public class AppError extends RuntimeException {
    public AppError(String message) {
        super(message);
    }
}
