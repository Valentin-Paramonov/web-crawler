package paramonov.valentine.web_crawler.site_checker;

import java.util.Optional;

class UrlNormalizer {
    static String normalize(String url) {
        return Optional.of(url)
                       .map(String::toLowerCase)
                       .filter(l -> !l.matches("^https?://.*$"))
                       .map(l -> "http://" + l)
                       .orElse(url);
    }
}
