package nl.zoostation.database.app.config;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @author valentinnastasi
 */
public class ZooStationWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = createWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(context));

        addDispatcherServlet(servletContext, context);
        addDandelionServlet(servletContext);
        addDandelionFilter(servletContext);
    }

    private WebApplicationContext createWebApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("nl.zoostation.database.app.config");
        return context;
    }

    private void addDispatcherServlet(ServletContext servletContext, WebApplicationContext context) {
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setInitParameter("throwExceptionIfNoHandlerFound", Boolean.toString(true));
        dispatcherServlet.setLoadOnStartup(1);
    }

    private void addDandelionServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic dandelionServlet = servletContext.addServlet("dandelionServlet", new DandelionServlet());
        dandelionServlet.addMapping("/ddlAssets/*");
    }

    private void addDandelionFilter(ServletContext servletContext) {
        EnumSet<DispatcherType> types = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR);

        FilterRegistration.Dynamic dandelionFilter = servletContext.addFilter("dandelionFilter", new DandelionFilter());
        dandelionFilter.addMappingForUrlPatterns(types, true, "/*");
    }

}
