package no.unit.nva.model.contexttypes;

import no.unit.nva.model.contexttypes.utils.IssnUtil;
import no.unit.nva.model.exceptions.InvalidIssnException;

public class Report extends Book implements SerialPublication, PublicationContext {

    private String printIssn;
    private String onlineIssn;

    public Report() {
        super();
    }

    @Override
    public void setOnlineIssn(String onlineIssn) throws InvalidIssnException {
        this.onlineIssn = IssnUtil.checkIssn(onlineIssn);
    }

    public String getOnlineIssn() {
        return onlineIssn;
    }

    @Override
    public void setPrintIssn(String printIssn) throws InvalidIssnException {
        this.printIssn = IssnUtil.checkIssn(printIssn);
    }

    public String getPrintIssn() {
        return printIssn;
    }
}
