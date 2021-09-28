package no.unit.nva;

import no.unit.nva.model.FileSet;

public interface WithFile extends PublicationBase {

    FileSet getFileSet();

    void setFileSet(FileSet fileSet);

}
