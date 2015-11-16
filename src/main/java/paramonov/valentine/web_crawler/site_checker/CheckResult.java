package paramonov.valentine.web_crawler.site_checker;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.*;

@Entity
@Table(name = "check_result", uniqueConstraints = {
    @UniqueConstraint(columnNames = "check_record"),
    @UniqueConstraint(columnNames = "check_name")
})
class CheckResult {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "check_record", nullable = false)
    private CheckRecord checkRecord;

    @Column(name = "check_name", nullable = false)
    private String checkName;

    @Column(name = "check_status", nullable = false)
    private CheckStatus status;

    CheckResult() {
    }

    CheckResult(String checkName, CheckStatus status) {
        this.checkName = checkName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CheckRecord getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(CheckRecord checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public CheckStatus getStatus() {
        return status;
    }

    public void setStatus(CheckStatus status) {
        this.status = status;
    }
}
