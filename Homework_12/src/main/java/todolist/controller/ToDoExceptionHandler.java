package todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import todolist.exception.ServerException;
import todolist.exception.NotFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class ToDoExceptionHandler {

    @ExceptionHandler(ServerException.class)
    public ModelAndView handleDaoException(ServerException e) {
        ModelAndView modelAndView = new ModelAndView("errors/err500");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("errors/err404");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
