package no.unit.nva.model.instancetypes.exhibition.manifestations;

import nva.commons.core.JacocoGenerated;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class ExhibitionProductionManifestationList implements List<ExhibitionProductionManifestation> {
    private final List<ExhibitionProductionManifestation> manifestations;

    public ExhibitionProductionManifestationList(List<ExhibitionProductionManifestation> manifestations) {
        this.manifestations = nonNull(manifestations) ? manifestations : Collections.emptyList();
    }

    @Override
    public int size() {
        return manifestations.size();
    }

    @Override
    public boolean isEmpty() {
        return manifestations.isEmpty();
    }

    @JacocoGenerated
    @Override
    public boolean contains(Object o) {
        return manifestations.contains(o);
    }

    @Override
    public Iterator<ExhibitionProductionManifestation> iterator() {
        return manifestations.iterator();
    }

    @JacocoGenerated
    @Override
    public Object[] toArray() {
        return manifestations.toArray();
    }

    @JacocoGenerated
    @Override
    public <T> T[] toArray(T[] a) {
        return manifestations.toArray(a);
    }

    @JacocoGenerated
    @Override
    public boolean add(ExhibitionProductionManifestation manifestation) {
        return manifestations.add(manifestation);
    }

    @JacocoGenerated
    @Override
    public void add(int index, ExhibitionProductionManifestation element) {
        manifestations.add(index, element);
    }

    @JacocoGenerated
    @Override
    public boolean remove(Object o) {
        return manifestations.remove(o);
    }

    @JacocoGenerated
    @Override
    public ExhibitionProductionManifestation remove(int index) {
        return manifestations.remove(index);
    }

    @JacocoGenerated
    @Override
    public boolean containsAll(Collection<?> c) {
        return manifestations.containsAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean addAll(Collection<? extends ExhibitionProductionManifestation> c) {
        return manifestations.addAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean addAll(int index, Collection<? extends ExhibitionProductionManifestation> c) {
        return manifestations.addAll(index, c);
    }

    @JacocoGenerated
    @Override
    public boolean removeAll(Collection<?> c) {
        return manifestations.removeAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean retainAll(Collection<?> c) {
        return manifestations.retainAll(c);
    }

    @JacocoGenerated
    @Override
    public void clear() {
        manifestations.clear();
    }

    @JacocoGenerated
    @Override
    public ExhibitionProductionManifestation get(int index) {
        return manifestations.get(index);
    }

    @JacocoGenerated
    @Override
    public ExhibitionProductionManifestation set(int index, ExhibitionProductionManifestation element) {
        return manifestations.set(index, element);
    }

    @JacocoGenerated
    @Override
    public int indexOf(Object o) {
        return manifestations.indexOf(o);
    }

    @JacocoGenerated
    @Override
    public int lastIndexOf(Object o) {
        return manifestations.lastIndexOf(o);
    }

    @JacocoGenerated
    @Override
    public ListIterator<ExhibitionProductionManifestation> listIterator() {
        return manifestations.listIterator();
    }

    @JacocoGenerated
    @Override
    public ListIterator<ExhibitionProductionManifestation> listIterator(int index) {
        return manifestations.listIterator(index);
    }

    @JacocoGenerated
    @Override
    public List<ExhibitionProductionManifestation> subList(int fromIndex, int toIndex) {
        return manifestations.subList(fromIndex, toIndex);
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExhibitionProductionManifestationList)) {
            return false;
        }
        ExhibitionProductionManifestationList that = (ExhibitionProductionManifestationList) o;
        return Objects.equals(manifestations, that.manifestations);
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(manifestations);
    }
}
