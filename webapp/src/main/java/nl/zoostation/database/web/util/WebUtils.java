package nl.zoostation.database.web.util;

import javax.servlet.http.HttpServletRequest;

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

}
