package paramonov.valentine.web_crawler.site_checker;

import org.jsoup.nodes.Document;

public interface Check {
    CheckStatus performOn(Document document);
    String getName();
}
