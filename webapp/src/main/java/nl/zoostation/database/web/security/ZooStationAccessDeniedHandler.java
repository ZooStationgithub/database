package nl.zoostation.database.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.zoostation.database.model.json.JsonErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static nl.zoostation.database.web.util.WebUtils.isAjaxRequest;

/**
 * @author valentinnastasi
 */
public class ZooStationAccessDeniedHandler extends AccessDeniedHandlerImpl {

    private ObjectMapper objectMapper;
    private MessageSource messageSource;

    public ZooStationAccessDeniedHandler(
            ObjectMapper objectMapper,
            MessageSource messageSource) {

        this.objectMapper = objectMapper;
        this.messageSource = messageSource;
        this.setErrorPage("/accessdenied");
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        if (!isAjaxRequest(httpServletRequest)) {
            super.handle(httpServletRequest, httpServletResponse, e);
            return;
        }

        JsonErrorResponse jsonErrorResponse = new JsonErrorResponse();
        jsonErrorResponse.setMessage(messageSource.getMessage("page.error.message.403", null, httpServletRequest.getLocale()));
        jsonErrorResponse.setHttpCode(HttpStatus.FORBIDDEN.value());
        jsonErrorResponse.setExceptionClass(e.getClass().getName());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(httpServletResponse.getWriter(), jsonErrorResponse);
    }

}
