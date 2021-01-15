package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Collections;
import nva.commons.core.JacocoGenerated;

import java.util.List;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class FileSet {

    private List<File> files;

    public FileSet() {

    }

    private FileSet(Builder builder) {
        setFiles(builder.files);
    }

    public List<File> getFiles() {
        return files.isEmpty()? Collections.emptyList():files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileSet fileSet = (FileSet) o;
        return Objects.equals(getFiles(), fileSet.getFiles());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getFiles());
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
