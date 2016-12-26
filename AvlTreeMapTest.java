/** Jason Zhang jzhan127
 * 
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class AvlTreeMapTest extends MapTestBase {

    @Override
    protected OrderedMap<String, String> createMap() {
        return new AvlTreeMap<String, String>();
    }
}
