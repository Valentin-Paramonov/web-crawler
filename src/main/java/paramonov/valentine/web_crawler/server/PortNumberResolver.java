package paramonov.valentine.web_crawler.server;

import static java.lang.Integer.parseInt;

class PortNumberResolver {
    static final int INVALID_PORT = -1;

    static Integer toInt(String port) {
        try {
            return parseInt(port);
        } catch (NumberFormatException nfe) {
            return INVALID_PORT;
        }
    }
}
