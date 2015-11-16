package paramonov.valentine.web_crawler.site_checker;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class SiteChecker {
    private final List<Check> checks;
    private final DocumentFetcher document;
    private final CheckResultPersister persister;

    @Autowired
    SiteChecker(List<Check> checks, DocumentFetcher document, CheckResultPersister persister) {
        this.checks = checks;
        this.document = document;
        this.persister = persister;
    }

    void check(String url) {
        document.by(url).map(this::runChecksOn)
                .ifPresent(results -> persister.persist(url, results));
    }

    private Set<CheckResult> runChecksOn(Document document) {
        return checks.stream()
                     .map(check -> new CheckResult(check.getName(), check.performOn(document)))
                     .collect(toSet());
    }
}
