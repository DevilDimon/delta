package ru.dserov.delta;

import java.util.List;
import java.util.Objects;

/**
 * Stores a pair of {@link java.util.List} objects.
 * @param <V> the element type of the stored lists.
 */
public final class ListPair<V> {
    final List<V> first;
    final List<V> second;

    /**
     * Constructs a new {@link ListPair} with the specified members.
     * @param first the first member of the pair.
     * @param second the second member of the pair.
     */
    public ListPair(final List<V> first, final List<V> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "first: " + first.toString()+ " second: " + second.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListPair<?> listPair = (ListPair<?>) o;
        return first.equals(listPair.first) &&
                second.equals(listPair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
