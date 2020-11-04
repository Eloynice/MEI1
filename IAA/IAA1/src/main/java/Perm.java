import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * First permutation: 0, 1, ..., size-1
 * From any permutation compute the next permutation as follows:
 *
 * - from right to left, detect the first i s.t. t[i] < t[i+1]
 *   remark: sub-array at the right of i is in decreasing order
 *
 * - if none (t is in decreasing order, values = n-1 .. 0) then stop
 *
 * - from right to left, detect the first j s.t. t[j] > t[i]
 *   remark: i > j (j is in the right sub-array)
 *
 * - swap t[i] and t[j]
 *   remark: the sub-array at the right of i remains in decreasing order
 *
 * - reverse the sub-array at the right of i
 *   remark: the sub-array at the right of i is now in ascending order
 */

/**
 * A class to iterate over permutations of 0..size-1
 *
 * @author Daniel Diaz {@code <daniel.diaz@univ-paris1.fr>}
 * @author Salvador Abreu {@code <spa@debian.org>}
 */
public class Perm implements Iterable<List<Integer>> {

    private final int size;
    private final int offset;

    public Perm(int size) {
        this(size, 0);  // values = integers 0, 1, ..., size-1
    }

    public Perm(int size, int offset) {
        this.size = size;  // values = integers offset, offset+1, ..., offset+size-1
        this.offset = offset;
    }

    @Override
    public Iterator<List<Integer>> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<List<Integer>> {

        private final int[] t;
        private boolean solAvailable;

        Itr() {
            t = new int[size];
            for (int i = 0; i < size; i++) {
                t[i] = i;
            }
            solAvailable = (size > 0);
        }

        @Override
        public boolean hasNext() {
            return solAvailable;
        }

        @Override
        public List<Integer> next() {
            if (!solAvailable) {
                return null;
            }
            List<Integer> ret = new ArrayList<Integer>(size);
            for (int x : t) {
                ret.add(offset + x);
            }
            solAvailable = nextPermutation();
            return ret;
        }

        @Override
        public void remove() {

        }

        private boolean nextPermutation() {
            int i, j;

            for (i = size - 2; i >= 0 && t[i] >= t[i + 1]; i--) {
            }

            if (i < 0) {
                return false;
            }

            for (j = size - 1; t[j] <= t[i]; j--) {
            }
//            if (j<i)
//                System.out.print(" OCCURS ");
            swap(t, i, j);

            for (int r = size - 1, l = i + 1; r > l; r--, l++) {
                swap(t, r, l);
            }
            //System.out.println("i: " + i + " j: " + j + " ==> " + Arrays.toString(t));
            return true;
        }

        private void swap(int[] t, int i, int j) {
            int tmp = t[i];
            t[i] = t[j];
            t[j] = tmp;
        }
    }

    public static void main(String... args) {
        Perm perm = new Perm (Integer.valueOf(args[0]), 1);

        for (List<Integer> p: perm)
            System.out.println (p);

        //        perm.forEach(p -> System.out.println(p));
    }
}