package paramonov.valentine.web_crawler.site_checker;

import static javax.persistence.GenerationType.AUTO;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "check_record")
public class CheckRecord {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "test_subject_url", nullable = false, unique = true)
    private String testSubjectUrl;

    @OneToMany
    @Column(name = "check_result")
    private Set<CheckResult> checkResults;

    public String getTestSubjectUrl() {
        return testSubjectUrl;
    }

    public void setTestSubjectUrl(String testSubjectUrl) {
        this.testSubjectUrl = testSubjectUrl;
    }

    public Set<CheckResult> getCheckResults() {
        return checkResults;
    }

    public void setCheckResults(Set<CheckResult> checkResults) {
        this.checkResults = checkResults;
    }
}
