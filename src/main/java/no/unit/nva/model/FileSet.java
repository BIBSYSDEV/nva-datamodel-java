package no.unit.nva.model;

import java.util.List;

public class FileSet {

    private List<File> files;

    public FileSet() {

    }

    private FileSet(Builder builder) {
        setFiles(builder.files);
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public static final class Builder {
        private List<File> files;

        public Builder() {
        }

        public Builder withFiles(List<File> files) {
            this.files = files;
            return this;
        }

        public FileSet build() {
            return new FileSet(this);
        }
    }
}
