package paramonov.valentine.web_crawler.server;

import static java.util.Arrays.stream;
import static paramonov.valentine.web_crawler.server.PortNumberResolver.INVALID_PORT;
import static paramonov.valentine.web_crawler.server.ServerRunnerBuilder.buildRunnerWith;
import static paramonov.valentine.web_crawler.server.WebCrawlerInitializer.from;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public final class Runner {
    private static final int DEFAULT_PORT = 8080;
    private static final String CONFIG_LOCATION = "paramonov.valentine.web_crawler";

    private Runner() {
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            printUsageAndExit();
        }
        runServer(portNumberFrom(args));
    }

    private static void runServer(int port) {
        buildRunnerWith().port(port)
                         .initializer(from(webAppContext()))
                         .build()
                         .run();
    }

    private static void printUsageAndExit() {
        System.out.println("Expected arguments: [port_number]");
        System.exit(1);
    }

    private static WebApplicationContext webAppContext() {
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        return context;
    }

    private static int portNumberFrom(String[] args) {
        return stream(args).map(PortNumberResolver::toInt)
                           .filter(p -> p != INVALID_PORT)
                           .findFirst()
                           .orElse(DEFAULT_PORT);
    }
}
