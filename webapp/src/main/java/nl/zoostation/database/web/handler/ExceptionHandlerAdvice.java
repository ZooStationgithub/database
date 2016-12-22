package nl.zoostation.database.web.handler;

import nl.zoostation.database.model.json.JsonErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static nl.zoostation.database.web.util.WebUtils.isAjaxRequest;

/**
 * @author valentinnastasi
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private MessageSource messageSource;

    @Autowired
    public ExceptionHandlerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Model model, NoHandlerFoundException e) {
        model.addAttribute("message", messageSource.getMessage("page.error.message.404", null, LocaleContextHolder.getLocale()));
        return "/error/error";
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest request, Model model, Exception e) {
        if (isAjaxRequest(request)) {
            JsonErrorResponse jsonErrorResponse = new JsonErrorResponse();
            jsonErrorResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            jsonErrorResponse.setExceptionClass(e.getClass().getName());
            jsonErrorResponse.setMessage(ExceptionUtils.getMessage(e));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonErrorResponse);
        }

        model.addAttribute("message", messageSource.getMessage("page.error.message.500", null, LocaleContextHolder.getLocale()));
        model.addAttribute("exceptionMessage", ExceptionUtils.getMessage(e));
        model.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));

        return "/error/error";
    }
}
