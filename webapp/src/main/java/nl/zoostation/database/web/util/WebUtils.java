package nl.zoostation.database.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author valentinnastasi
 */
public final class WebUtils {

    private WebUtils() {
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWithHeader);
    }

    public static <T> String toJson(T object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static <T> T fromJson(String s, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(s, clazz);
    }

}
