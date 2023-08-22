package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import no.unit.nva.model.associatedartifacts.file.AdministrativeAgreement;
import nva.commons.core.JacocoGenerated;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class AssociatedArtifactList implements List<AssociatedArtifact> {

    public static final String NULL_OBJECT_MUST_BE_SINGLETON_IN_LIST = "AssociatedArtifactLists containing "
            + "NullAssociatedArtifact must contain only this element as a singleton";
    private final List<AssociatedArtifact> associatedArtifacts;
    
    @JsonCreator
    public AssociatedArtifactList(List<AssociatedArtifact> artifacts) {
        throwExceptionIfNullObjectIsNotOnlyElementInList(artifacts);
        this.associatedArtifacts = nonNull(artifacts) ? artifacts : Collections.emptyList();
    }

    public AssociatedArtifactList(AssociatedArtifact... artifacts) {
        this(Arrays.asList(artifacts));
    }
    
    public static AssociatedArtifactList empty() {
        return new AssociatedArtifactList(Collections.emptyList());
    }
    
    @Override
    public int size() {
        return associatedArtifacts.size();
    }
    
    @Override
    public boolean isEmpty() {
        return associatedArtifacts.isEmpty();
    }

    @JacocoGenerated
    @Override
    public boolean contains(Object o) {
        return associatedArtifacts.contains(o);
    }

    @JacocoGenerated
    @Override
    public Iterator<AssociatedArtifact> iterator() {
        return associatedArtifacts.iterator();
    }

    @JacocoGenerated
    @Override
    public Object[] toArray() {
        return associatedArtifacts.toArray();
    }

    @JacocoGenerated
    @Override
    public <T> T[] toArray(T[] a) {
        return associatedArtifacts.toArray(a);
    }

    @JacocoGenerated
    @Override
    public boolean add(AssociatedArtifact associatedArtifact) {
        return associatedArtifacts.add(associatedArtifact);
    }

    @JacocoGenerated
    @Override
    public void add(int index, AssociatedArtifact element) {
        associatedArtifacts.add(index, element);
    }

    @JacocoGenerated
    @Override
    public boolean remove(Object o) {
        return associatedArtifacts.remove(o);
    }

    @JacocoGenerated
    @Override
    public AssociatedArtifact remove(int index) {
        return associatedArtifacts.remove(index);
    }

    @JacocoGenerated
    @Override
    public boolean containsAll(Collection<?> c) {
        return associatedArtifacts.containsAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean addAll(Collection<? extends AssociatedArtifact> c) {
        return associatedArtifacts.addAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean addAll(int index, Collection<? extends AssociatedArtifact> c) {
        return associatedArtifacts.addAll(index, c);
    }

    @JacocoGenerated
    @Override
    public boolean removeAll(Collection<?> c) {
        return associatedArtifacts.removeAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean retainAll(Collection<?> c) {
        return associatedArtifacts.retainAll(c);
    }

    @JacocoGenerated
    @Override
    public void clear() {
        associatedArtifacts.clear();
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        return associatedArtifacts.equals(o);
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(associatedArtifacts);
    }

    @JacocoGenerated
    @Override
    public AssociatedArtifact get(int index) {
        return associatedArtifacts.get(index);
    }

    @JacocoGenerated
    @Override
    public AssociatedArtifact set(int index, AssociatedArtifact element) {
        return associatedArtifacts.set(index, element);
    }

    @JacocoGenerated
    @Override
    public int indexOf(Object o) {
        return associatedArtifacts.indexOf(o);
    }

    @JacocoGenerated
    @Override
    public int lastIndexOf(Object o) {
        return associatedArtifacts.lastIndexOf(o);
    }
    
    @Override
    public ListIterator<AssociatedArtifact> listIterator() {
        return associatedArtifacts.listIterator();
    }

    @JacocoGenerated
    @Override
    public ListIterator<AssociatedArtifact> listIterator(int index) {
        return associatedArtifacts.listIterator(index);
    }

    @JacocoGenerated
    @Override
    public List<AssociatedArtifact> subList(int fromIndex, int toIndex) {
        return associatedArtifacts.subList(fromIndex, toIndex);
    }

    public boolean isPublishable() {
        return !isEmpty() && associatedArtifactsAreNotOnlyAdministrativeAgreements();
    }

    private void throwExceptionIfNullObjectIsNotOnlyElementInList(List<AssociatedArtifact> artifacts) {
        if (nonNull(artifacts) && containsNullObject(artifacts) && artifacts.size() > 1) {
            throw new InvalidAssociatedArtifactsException(NULL_OBJECT_MUST_BE_SINGLETON_IN_LIST);
        }
    }

    private boolean containsNullObject(List<AssociatedArtifact> artifacts) {
        return artifacts.stream().anyMatch(NullAssociatedArtifact.class::isInstance);
    }

    private boolean associatedArtifactsAreNotOnlyAdministrativeAgreements() {
        return associatedArtifacts.stream().anyMatch(item -> !(item instanceof AdministrativeAgreement));
    }
}
