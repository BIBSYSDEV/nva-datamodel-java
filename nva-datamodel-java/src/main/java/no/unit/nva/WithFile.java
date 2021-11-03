package no.unit.nva;


import no.unit.nva.file.model.FileSet;

public interface WithFile extends PublicationBase {

    FileSet getFileSet();

    void setFileSet(FileSet fileSet);

}
