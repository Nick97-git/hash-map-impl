import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
    private HashMapImpl map;

    @Before
    public void setUp() {
        map = new HashMapImpl();
        for (int i = 1; i <= 1000; i++) {
            map.put(i * 10 + 4, i);
        }
    }

    @Test
    public void checkSizeOfEmptyHashMap() {
        Assert.assertEquals(0, new HashMapImpl().size());
    }

    @Test
    public void checkSizeAfterPut() {
        int actual = map.size();
        Assert.assertEquals(1000, actual);
    }

    @Test(expected = RuntimeException.class)
    public void checkNotExistedKey() {
        map.get(0);
    }

    @Test
    public void getValue() {
        long actual = map.get(14);
        Assert.assertEquals(1L, actual);
    }

    @Test
    public void checkPutOfSameKey() {
        map.put(14, 5L);
        long actual = map.get(14);
        Assert.assertEquals(5L, actual);
    }
}
