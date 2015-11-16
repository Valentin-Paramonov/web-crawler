package paramonov.valentine.web_crawler.server;

import java.io.IOException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

class WebCrawlerInitializer {
    private static final String MAPPING_URL = "/*";
    private static final String APP_ROOT = "/";
    private static final String RESOURCE_BASE = "res";

    private final WebApplicationContext context;

    private WebCrawlerInitializer(WebApplicationContext context) {
        this.context = context;
    }

    public static WebCrawlerInitializer from(WebApplicationContext context) {
        return new WebCrawlerInitializer(context);
    }

    public Handler buildHandler() {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath(APP_ROOT);
        handler.setResourceBase(resourceBase());
        addServlets(handler);
        addEventListeners(handler);
        return handler;
    }

    private void addServlets(ServletContextHandler handler) {
        addDispatcherServlet(handler);
    }

    private void addDispatcherServlet(ServletContextHandler handler) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        handler.addServlet(new ServletHolder(dispatcherServlet), MAPPING_URL);
    }

    private void addEventListeners(ServletContextHandler handler) {
        handler.addEventListener(new ContextLoaderListener(context));
    }

    private String resourceBase() {
        try {
            return new ClassPathResource(RESOURCE_BASE).getURI().toString();
        } catch (IOException ioe) {
            return "";
        }
    }
}
