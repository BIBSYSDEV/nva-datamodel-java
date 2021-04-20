package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Approval {
    private Instant date;
    private ApprovalsBody approvedBy;
    private ApprovalStatus approvalStatus;
    private String applicationCode;

    public Approval() {

    }

    private Approval(Builder builder) {
        setDate(builder.date);
        setApprovedBy(builder.approvedBy);
        setApprovalStatus(builder.approvalStatus);
        setApplicationCode(builder.applicationCode);
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public ApprovalsBody getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(ApprovalsBody approvedBy) {
        this.approvedBy = approvedBy;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Approval)) {
            return false;
        }
        Approval approval = (Approval) o;
        return Objects.equals(getDate(), approval.getDate())
                && Objects.equals(getApprovedBy(), approval.getApprovedBy())
                && Objects.equals(getApprovalStatus(), approval.getApprovalStatus())
                && Objects.equals(getApplicationCode(), approval.getApplicationCode());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getApprovedBy(), getApprovalStatus(), getApplicationCode());
    }

    public static final class Builder {
        private Instant date;
        private ApprovalsBody approvedBy;
        private ApprovalStatus approvalStatus;
        private String applicationCode;

        public Builder() {
        }

        public Builder withDate(Instant date) {
            this.date = date;
            return this;
        }

        public Builder withApprovedBy(ApprovalsBody approvedBy) {
            this.approvedBy = approvedBy;
            return this;
        }

        public Builder withApprovalStatus(ApprovalStatus approvalStatus) {
            this.approvalStatus = approvalStatus;
            return this;
        }

        public Builder withApplicationCode(String applicationCode) {
            this.applicationCode = applicationCode;
            return this;
        }

        public Approval build() {
            return new Approval(this);
        }
    }
}
