package nl.zoostation.database.app.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author valentinnastasi
 */
public class ZooStationWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = createWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setInitParameter("throwExceptionIfNoHandlerFound", Boolean.toString(true));
        dispatcherServlet.setLoadOnStartup(1);

    }

    private WebApplicationContext createWebApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("nl.zoostation.database.app.config");
        return context;
    }

}
