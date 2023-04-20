package by.it.academy.task.Messenger.controllers;

import by.it.academy.task.Messenger.dto.ErrorResponse;
import by.it.academy.task.Messenger.exceptions.IncorrectDataException;
import by.it.academy.task.Messenger.exceptions.NoAccessException;
import by.it.academy.task.Messenger.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotFoundException(NotFoundException notFoundException) {
        return new ErrorResponse(notFoundException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(IncorrectDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse incorrectDataException(IncorrectDataException incorrectDataException) {
        return new ErrorResponse(incorrectDataException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NoAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse noAccessException(NoAccessException noAccessException) {
        return new ErrorResponse(noAccessException.getMessage(), LocalDateTime.now());
    }
}
