package paramonov.valentine.web_crawler.site_checker;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CheckRecordRepo extends CrudRepository<CheckRecord, Long> {
    Optional<CheckRecord> findByTestSubjectUrl(String testSubjectUrl);

    default CheckRecord saveOrUpdate(CheckRecord record) {
        CheckRecord existingRecord = findByTestSubjectUrl(record.getTestSubjectUrl()).orElse(record);
        existingRecord.setCheckResults(record.getCheckResults());
        save(existingRecord);
        return existingRecord;
    }
}
