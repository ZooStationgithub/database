package nl.zoostation.database.app.config;

import net.jawr.web.servlet.JawrServlet;
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

        addDispatcherServlet(servletContext, context);
        addJawrJavaScriptServlet(servletContext);
        addJawrCssServlet(servletContext);
        addJawrBinaryServlet(servletContext);
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

    private void addJawrJavaScriptServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic jsServlet = servletContext.addServlet("jsServlet", new JawrServlet());
        jsServlet.addMapping("*.js");
        jsServlet.setInitParameter("configLocation", "/jawr.properties");
        jsServlet.setLoadOnStartup(1);
    }

    private void addJawrCssServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic cssServlet = servletContext.addServlet("cssServlet", new JawrServlet());
        cssServlet.addMapping("*.css");
        cssServlet.setInitParameter("configLocation", "/jawr.properties");
        cssServlet.setInitParameter("type", "css");
        cssServlet.setLoadOnStartup(1);
    }

    private void addJawrBinaryServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic binaryServlet = servletContext.addServlet("binaryServlet", new JawrServlet());
        binaryServlet.addMapping("*.png", "*.jpg", "*.gif", "*.woff", "*.ttf", "*.woff2", "*.svg", "*.eot");
        binaryServlet.setInitParameter("configLocation", "/jawr.properties");
        binaryServlet.setInitParameter("type", "binary");
        binaryServlet.setLoadOnStartup(0);
    }

}
