package paramonov.valentine.web_crawler.site_checker;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CheckResultRepo extends CrudRepository<CheckResult, Long> {
    Optional<CheckResult> findByCheckRecordAndCheckName(CheckRecord checkRecord, String checkName);

    default void saveOrUpdate(CheckResult checkResult) {
        CheckResult existing = findByCheckRecordAndCheckName(checkResult.getCheckRecord(), checkResult.getCheckName()).orElse(checkResult);
        existing.setStatus(checkResult.getStatus());
        save(existing);
    }
}
