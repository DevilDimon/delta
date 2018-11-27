package ru.dserov.delta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Various utility functions for electronic table management.
 */
public final class DiffUtils {
    private DiffUtils() {}

    /**
     * Returns a {@link ListPair}, where the first list contains only the elements for insertion,
     * and the second list only the elements for removal. The lists do not share common elements.
     *
     * @param toInsert the {@link List} of elements to insert. Each element is a list of bytes.
     * @param toRemove the {@link List} of elements to remove. Each element is a list of bytes.
     * @return a pair of lists stripped of duplicates between each other, or null, if any of the params is null.
     */
    public static ListPair<List<Byte>> delta(final List<List<Byte>> toInsert, final List<List<Byte>> toRemove) {
        if (toInsert == null || toRemove == null) {
            return null;
        }

        if (toInsert.isEmpty() || toRemove.isEmpty()) {
            return new ListPair<>(toInsert, toRemove);
        }

        // Count each distinct sequence occurrence in `toRemove`
        var toRemoveMap = new HashMap<List<Byte>, Integer>();
        toRemove.forEach(sequence -> {
            toRemoveMap.put(sequence, toRemoveMap.getOrDefault(sequence, 0) + 1);
        });

        var toInsertResult = new ArrayList<List<Byte>>();

        // Check whether `toInsert` member is also to be removed
        // If so, decrement the occurrence count
        // Otherwise, add the value to the resulting `toInsertResult` array
        toInsert.forEach(sequence -> {
            if (toRemoveMap.containsKey(sequence) && toRemoveMap.get(sequence) > 0) {
                toRemoveMap.put(sequence, toRemoveMap.get(sequence) - 1);
            } else {
                toInsertResult.add(sequence);
            }
        });

        // Construct the resulting `toRemoveResult` array using the remaining occurrence counts
        var toRemoveResult = new ArrayList<List<Byte>>();
        toRemoveMap.forEach((sequence, count) -> {
            if (count <= 0) {
                return;
            }

            for (int i = 0; i < count; i++) {
                toRemoveResult.add(new ArrayList<>(sequence));
            }
        });

        return new ListPair<>(toInsertResult, toRemoveResult);
    }
}
