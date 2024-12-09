package org.example;

import java.util.Comparator;

public class MyArrays {
    @FunctionalInterface
    private interface MidValueComparator {
        int compareMid(int midIndex);
    }

    private static int binarySearchImpl(int fromIndex, int toIndex, MidValueComparator comparator) {
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

    private static void checkRange(int fromIndex, int toIndex, int length) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex > toIndex");
        }
        if (fromIndex < 0 || toIndex > length) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
    }

    public static int binarySearch(byte[] a, byte key) {
        return binarySearchImpl(0, a.length, mid -> Byte.compare(a[mid], key));
    }

    public static int binarySearch(byte[] a, int fromIndex, int toIndex, byte key) {
        checkRange(fromIndex, toIndex, a.length);
        return binarySearchImpl(fromIndex, toIndex, mid -> Byte.compare(a[mid], key));
    }

    public static int binarySearch(double[] a, double key) {
        return binarySearchImpl(0, a.length, mid -> Double.compare(a[mid], key));
    }

    public static int binarySearch(double[] a, int fromIndex, int toIndex, double key) {
        checkRange(fromIndex, toIndex, a.length);
        return binarySearchImpl(fromIndex, toIndex, mid -> Double.compare(a[mid], key));
    }

    public static int binarySearch(char[] a, char key) {
        return binarySearchImpl(0, a.length, mid -> Character.compare(a[mid], key));
    }

    public static int binarySearch(char[] a, int fromIndex, int toIndex, char key) {
        checkRange(fromIndex, toIndex, a.length);
        return binarySearchImpl(fromIndex, toIndex, mid -> Character.compare(a[mid], key));
    }

    public static int binarySearch(int[] a, int key) {
        return binarySearchImpl(0, a.length, mid -> Integer.compare(a[mid], key));
    }

    public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        checkRange(fromIndex, toIndex, a.length);
        return binarySearchImpl(fromIndex, toIndex, mid -> Integer.compare(a[mid], key));
    }

    public static int binarySearch(float[] a, float key) {
        return binarySearchImpl(0, a.length, mid -> Float.compare(a[mid], key));
    }

    public static int binarySearch(float[] a, int fromIndex, int toIndex, float key) {
        checkRange(fromIndex, toIndex, a.length);
        return binarySearchImpl(fromIndex, toIndex, mid -> Float.compare(a[mid], key));
    }

    public static int binarySearch(long[] a, long key) {
        return binarySearchImpl(0, a.length, mid -> Long.compare(a[mid], key));
    }

    public static int binarySearch(long[] a, int fromIndex, int toIndex, long key) {
        checkRange(fromIndex, toIndex, a.length);
        return binarySearchImpl(fromIndex, toIndex, mid -> Long.compare(a[mid], key));
    }

    public static int binarySearch(short[] a, short key) {
        return binarySearchImpl(0, a.length, mid -> Short.compare(a[mid], key));
    }

    public static int binarySearch(short[] a, int fromIndex, int toIndex, short key) {
        checkRange(fromIndex, toIndex, a.length);
        return binarySearchImpl(fromIndex, toIndex, mid -> Short.compare(a[mid], key));
    }

    public static <T> int binarySearch(T[] a, T key, Comparator<? super T> c) {
        if (c == null) {
            throw new NullPointerException("Comparator is null");
        }
        return binarySearchImpl(0, a.length, midIndex -> c.compare(a[midIndex], key));
    }

    public static <T> int binarySearch(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c) {
        if (c == null) {
            throw new NullPointerException("Comparator is null");
        }
        checkRange(fromIndex, toIndex, a.length);
        return binarySearchImpl(fromIndex, toIndex, midIndex -> c.compare(a[midIndex], key));
    }

}
