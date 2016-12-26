/** Jason Zhang jzhan127
 * 
 */
import com.github.phf.jb.Bench;
import com.github.phf.jb.Bee;

import java.util.Random;
public class BinarySearchTreeBench {

    private static final int SIZE = 100;
    private static final Random RAND = new Random();

    private BinarySearchTreeBench() {}

    // First some basic "compound operations" to benchmark. Note that each
    // of these is carefully dimensioned (regarding the range of elements)
    // to allow combining them.

    // Insert a number of "consecutive" Integers into the given set.
    private static void insertLinear(Map<Integer, Integer> s) {
        for (int i = 0; i < SIZE; i++) {
            s.insert(i, i);
        }
    }

    // Insert a number of "random" Integers into the given set.
    private static void insertRandom(Map<Integer, Integer> s) {
        for (int i = 0; i < SIZE; i++) {
            int x = RAND.nextInt(SIZE*4);
            if(!s.has(x)){
                s.insert(x, RAND.nextInt(SIZE*4));
            }
        }
    }

    // Remove a number of "random" Integers from the given set.
    private static void removeRandom(Map<Integer, Integer> s) {
        for (int i = 0; i < SIZE; i++) {
            int x = RAND.nextInt(SIZE*2);
            if(s.has(x)){
                s.remove(x);
            }
        }
    }

    // Lookup a number of "consecutive" Integers in the given set.
    private static void lookupLinear(Map<Integer, Integer> s) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = s.has(i);
        }
    }

    // Lookup a number of "random" Integers in the given set.
    private static void lookupRandom(Map<Integer, Integer> s) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = s.has(RAND.nextInt(SIZE*2));
        }
    }

    // Now the benchmarks we actually want to run.

    @Bench
    public static void insertBinarySearchTreeMap(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> s = new BinarySearchTreeMap<Integer, Integer>();
            b.start();
            insertLinear(s);
        }
    }



    @Bench
    public static void insertRandomBinarySearchTreeMap(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            OrderedMap<Integer, Integer> s = new BinarySearchTreeMap<Integer, Integer>();
            b.start();
            insertRandom(s);
        }
    }



    @Bench
    public static void removeRandomBinarySearchTreeMap(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            OrderedMap<Integer, Integer> s = new BinarySearchTreeMap<Integer, Integer>();
            insertRandom(s);
            b.start();
            removeRandom(s);
        }
    }



    @Bench
    public static void lookupLinearBinarySearchTreeMap(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            OrderedMap<Integer, Integer> s = new BinarySearchTreeMap<Integer, Integer>();
            insertLinear(s);
            b.start();
            lookupLinear(s);
        }
    }



    @Bench
    public static void lookupRandomBinarySearchTreeMap(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            OrderedMap<Integer, Integer> s = new BinarySearchTreeMap<Integer, Integer>();
            insertLinear(s);
            b.start();
            lookupRandom(s);
        }
    }


}


