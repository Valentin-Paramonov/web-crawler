package paramonov.valentine.web_crawler.site_checker;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/site-check")
class SiteCheckController {
    private final SiteChecker checker;
    private final CheckRecordRepo checkRecords;

    @Autowired
    SiteCheckController(SiteChecker checker, CheckRecordRepo checkRecords) {
        this.checker = checker;
        this.checkRecords = checkRecords;
    }

    @RequestMapping(method = POST)
    void checkSites(@RequestBody List<Url> urls) {
        urls.stream()
            .map(Url::getUrl)
            .map(UrlNormalizer::normalize)
            .filter(Objects::nonNull)
            .forEach(checker::check);
    }

    @Transactional
    @RequestMapping(method = GET)
    @ResponseBody
    Iterable<CheckRecord> getResults() {
        return StreamSupport.stream(checkRecords.findAll().spliterator(), false).peek(CheckRecord::getCheckResults).collect(toList());
    }
}
