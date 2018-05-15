package ru.bellintegrator.weatherbroker.client.exception;

/**
 * Класс проверяемого исключения, которое выбрасывается в случае отсутствии результата
 */
public class NotFoundException extends Exception {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
