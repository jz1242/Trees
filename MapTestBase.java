/** Jason Zhang jzhan127
 * 
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import org.junit.Test;

public abstract class MapTestBase {
    private OrderedMap<String, String> test;

    protected abstract OrderedMap<String,String> createMap();

    @Before
    public void setupMapTests() {
        test = this.createMap();
    }

    @Test
    public void testHas() {
        test.insert("a", "b");
        assertEquals(true, test.has("a"));

    }
    @Test
    public void testNotHave() {
        test.insert("a", "b");
        assertEquals(false, test.has("c"));

    }
    @Test
    public void testPut() {
        test.insert("a", "b");
        test.insert("c", "d");
        test.insert("e", "f");
        test.put("a", "1");
        assertEquals(test.get("a"), "1");

    }
    @Test (expected=IllegalArgumentException.class)
    public void testPutExceptionWithNull() {
        test.insert("a", "b");
        test.insert("c", "d");
        test.insert("e", "f");
        test.put(null, "1");

    }
    @Test (expected=IllegalArgumentException.class)
    public void testPutExceptionWithInvalidKey() {
        test.insert("a", "b");
        test.insert("c", "d");
        test.insert("e", "f");
        test.put("q", "1");

    }
    @Test 
    public void testInsert() {
        test.insert("a", "b");
        test.insert("c", "d");
        test.insert("e", "f");
        assertEquals(test.get("a"), "b");

    }
    @Test (expected=IllegalArgumentException.class)
    public void testInsertException() {
        test.insert(null, "b");
    }

    @Test (expected=IllegalArgumentException.class)
    public void testInsertExceptionDuplicates() {
        test.insert("a", "b");
        test.insert("a", "b");
    }
    @Test
    public void testRemove() {
        test.insert("a", "b");
        test.insert("c", "d");
        assertEquals(test.remove("a"), "b");
    }
    @Test (expected=IllegalArgumentException.class)
    public void testRemoveExceptionNotValidKey() {
        test.insert("a", "b");
        test.insert("c", "d");
        test.remove("d");
    }
    @Test (expected=IllegalArgumentException.class)
    public void testRemoveExceptionNull() {
        test.insert("a", "b");
        test.insert("c", "d");
        test.remove(null);
    }
    @Test
    public void testEmpty() {
        assertEquals(test.size(), 0);
    }
    @Test 
    public void testSize() {
        test.insert("a", "b");
        test.insert("c", "d");
        test.insert("e", "f");
        test.insert("g", "h");
        assertEquals(test.size(), 4);
    }
    @Test
    public void testIterator(){
       test.insert("a", "b");
       test.insert("c", "d");
       test.insert("e", "f");
       int count = 0;
       for(String a :test){
           count++;
       }
       assertEquals(count, 3);
       
    }

}
