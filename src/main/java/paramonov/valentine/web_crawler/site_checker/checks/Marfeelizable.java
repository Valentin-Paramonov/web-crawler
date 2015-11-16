package paramonov.valentine.web_crawler.site_checker.checks;

import java.util.Optional;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import paramonov.valentine.web_crawler.site_checker.Check;
import paramonov.valentine.web_crawler.site_checker.CheckStatus;

@Component
class Marfeelizable implements Check {

    @Override
    public CheckStatus performOn(Document document) {
        return hasNewsInTitle(document) ? CheckStatus.POSITIVE : CheckStatus.NEGATIVE;
    }

    private boolean hasNewsInTitle(Document document) {
        return Optional.of(document)
                       .map(Document::title)
                       .map(String::toLowerCase)
                       .filter(this::containsNews).isPresent();
    }

    private boolean containsNews(String title) {
        return title.contains("news") || title.contains("noticias");
    }

    @Override
    public String getName() {
        return "Marfeelizable";
    }
}
