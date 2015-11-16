package paramonov.valentine.web_crawler.site_checker;

import static org.jsoup.Jsoup.connect;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
class DocumentFetcher {
    private Logger log = org.apache.logging.log4j.LogManager.getLogger(getClass());

    Optional<Document> by(String url) {
        try {
            return Optional.of(connect(url).get());
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
            return Optional.empty();
        }
    }
}
