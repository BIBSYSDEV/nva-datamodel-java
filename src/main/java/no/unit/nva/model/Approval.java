package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Approval {
    private Instant date;
    private String approvedBy;
    private String approvalStatus;
    private String applicationCode;

    public Approval() {

    }

    private Approval(Builder builder) {
        setDate(builder.date);
        setApprovedBy(builder.approvedBy);
        setApprovalStatus(builder.status);
        setApplicationCode(builder.applicationCode);
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getApprovedBy(), getApprovalStatus(), getApplicationCode());
    }


    public static final class Builder {
        private Instant date;
        private String approvedBy;
        private String status;
        private String applicationCode;

        public Builder() {
        }

        public Builder withDate(Instant val) {
            date = val;
            return this;
        }

        public Builder withApprovedBy(String val) {
            approvedBy = val;
            return this;
        }

        public Builder withStatus(String val) {
            status = val;
            return this;
        }

        public Builder withApplicationCode(String val) {
            applicationCode = val;
            return this;
        }

        public Approval build() {
            return new Approval(this);
        }
    }
}
