package edu.school21.cinema;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AnnotationsBasedApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("");
        dispatcher.setMultipartConfig(multipartConfigElement);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setInitParameter("encoding", "UTF-8");
        dispatcher.setInitParameter("forceEncoding", "true");
   /*     ServletRegistration.Dynamic h2Console = servletContext.addServlet("h2_console", new WebServlet());
        h2Console.addMapping("/h2/*");*/
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(Cinema.class);
        return context;
    }
}