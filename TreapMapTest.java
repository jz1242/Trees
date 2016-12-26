/** Jason Zhang jzhan127
 * 
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class TreapMapTest extends MapTestBase {
    @Override
    protected OrderedMap<String, String> createMap() {
        return new TreapMap<String, String>();
    }

}
