import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class to iterate over combinations of 0..N-1
 *
 * @author Salvador Abreu {@code <spa@debian.org>}
 */

public class Comb implements Iterable<List<Integer>> {
    private final int nv;	 // number of vars (sizeof v)
    private final int k;	 // number of distinct values for elements
    private final int off;	 // offset to be added

    public Comb (int nv, int k, int off) {
        this.nv  = nv;
        this.k   = k;
        this.off = off;
    }

    public Comb (int nv, int k) {
        this (nv, k, 0);	// default off to 0
    }

    public Comb (int nv) {
        this (nv, nv, 1);	// default k to nv, off to 1
    }

    @Override
    public Iterator<List<Integer>> iterator () {
        return new CombIterator (nv, k, off);
    }

    private static final class CombIterator
            implements Iterator<List<Integer>> {

        private final int[] v; // current comb
        private final int nv;
        private final int k;
        private final int off;
        private List<Integer> nextV;

        //	private final int[] indices;

        CombIterator (int nv, int k, int off) {
            this.v = new int[nv];
            this.nv  = nv;
            this.k   = k;
            this.off = off;

            loadNextV ();
        }

        @Override
        public boolean hasNext () {
            return nextV != null;
        }

        @Override
        public List<Integer> next () {
            if (hasNext ()) {
                List<Integer> v = nextV;
                makeNextV ();
                return v;
            }
            else return null;
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer<? super List<Integer>> consumer) {

        }

        private boolean increment () {
            for (int i = 0; i<nv; ++i) {
                if (++v[i] < k)
                    return true;
                v[i] = 0;
            }
            return false;
        }

        private void loadNextV () {
            nextV = new ArrayList<>(nv);
            for (int i: v)
                nextV.add (i + off);
        }

        private void makeNextV () {
            if (increment ()) {
                nextV = new ArrayList(nv);
                loadNextV ();
            }
            else
                nextV = null;	// no more
        }
    }

    public static void main(String... args) {
        Comb comb;

        switch (args.length) {
            case 3:
                comb = new Comb(Integer.valueOf(args[0]),
                        Integer.valueOf(args[1]),
                        Integer.valueOf(args[2])); break;
            case 2:
                comb = new Comb(Integer.valueOf(args[0]),
                        Integer.valueOf(args[1])); break;
            default:
                comb = new Comb(Integer.valueOf(args[0])); break;
        }

        for (List<Integer> p: comb)
            System.out.println (p);

        //        perm.forEach(p -> System.out.println(p));
    }

}
