package org.example;

import java.util.Comparator;
import java.util.List;

public class MyCollections {

    @FunctionalInterface
    private interface ListMidValueComparator<T> {
        int compareMid(int midIndex);
    }

    private static <T> int binarySearchListImpl(List<? extends T> list, int fromIndex, int toIndex, ListMidValueComparator<T> comparator) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = comparator.compareMid(mid);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -(low + 1);
    }

    public static <T extends Comparable<? super T>> int binarySearch(List<? extends T> list, T key) {
        return binarySearchListImpl(list, 0, list.size(), midIndex -> {
            T midVal = list.get(midIndex);
            return midVal.compareTo(key);
        });
    }

    public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        if (c == null) {
            throw new NullPointerException("Comparator is null");
        }

        return binarySearchListImpl(list, 0, list.size(), midIndex -> {
            T midVal = list.get(midIndex);
            return c.compare(midVal, key);
        });
    }
}
