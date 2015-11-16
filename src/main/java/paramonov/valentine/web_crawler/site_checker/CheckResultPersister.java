package paramonov.valentine.web_crawler.site_checker;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CheckResultPersister {
    private final CheckRecordRepo checkRecords;
    private final CheckResultRepo checkResults;

    @Autowired
    CheckResultPersister(CheckRecordRepo checkRecords, CheckResultRepo checkResults) {
        this.checkRecords = checkRecords;
        this.checkResults = checkResults;
    }

    void persist(String url, Set<CheckResult> results) {
        CheckRecord record = new CheckRecord();
        record.setTestSubjectUrl(url);
        CheckRecord savedRecord = checkRecords.saveOrUpdate(record);
        savedRecord.setCheckResults(results.stream()
                                           .peek(res -> res.setCheckRecord(savedRecord))
                                           .peek(checkResults::saveOrUpdate)
                                           .collect(toSet()));
    }
}

