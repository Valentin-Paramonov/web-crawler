package paramonov.valentine.web_crawler.server;

import static java.util.Arrays.stream;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

class ServerRunner {
    private final Logger log = getLogger(getClass());
    private final Server server;

    ServerRunner(int port) {
        server = new Server(port);
    }

    public void run() {
        try {
            server.start();
            log.info("Server started at port {}", getPort());
            server.join();
            log.info("Server finished");
        } catch (Exception e) {
            log.catching(Level.ERROR, e);
        }
    }

    public void stop() throws Exception {
        server.stop();
    }

    public boolean isStarted() {
        return server.isStarted();
    }

    public int getPort() {
        return stream(server.getConnectors()).findFirst()
                                             .map(SelectChannelConnector.class::cast)
                                             .map(SelectChannelConnector::getLocalPort)
                                             .orElseThrow(() -> new IllegalStateException("Running port could not be determined"));
    }

    ServerRunner setHandler(Handler handler) {
        server.setHandler(handler);
        return this;
    }
}
