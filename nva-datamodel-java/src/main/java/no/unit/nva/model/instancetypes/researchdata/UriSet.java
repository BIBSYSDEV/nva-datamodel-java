package no.unit.nva.model.instancetypes.researchdata;

import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public abstract class UriSet implements Set<URI> {

    private final Set<URI> uris;

    protected UriSet(Set<URI> uris) {
        this.uris = uris;
    }

    @JacocoGenerated
    @Override
    public int size() {
        return uris.size();
    }

    @JacocoGenerated
    @Override
    public boolean isEmpty() {
        return uris.isEmpty();
    }

    @JacocoGenerated
    @Override
    public boolean contains(Object o) {
        return uris.contains(o);
    }

    @Override
    public Iterator<URI> iterator() {
        return uris.iterator();
    }

    @JacocoGenerated
    @Override
    public Object[] toArray() {
        return uris.toArray();
    }

    @JacocoGenerated
    @Override
    public <T> T[] toArray(T[] a) {
        return uris.toArray(a);
    }

    @JacocoGenerated
    @Override
    public boolean add(URI uri) {
        return uris.add(uri);
    }

    @JacocoGenerated
    @Override
    public boolean remove(Object o) {
        return uris.remove(o);
    }

    @JacocoGenerated
    @Override
    public boolean containsAll(Collection<?> c) {
        return uris.containsAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean addAll(Collection<? extends URI> c) {
        return uris.addAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean retainAll(Collection<?> c) {
        return uris.retainAll(c);
    }

    @JacocoGenerated
    @Override
    public boolean removeAll(Collection<?> c) {
        return uris.removeAll(c);
    }

    @JacocoGenerated
    @Override
    public void clear() {
        uris.clear();
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UriSet)) {
            return false;
        }
        UriSet uris1 = (UriSet) o;
        return Objects.equals(uris, uris1.uris);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(uris);
    }
}
