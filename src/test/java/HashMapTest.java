import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
    private HashMapImpl<Integer, Long> hashMap;

    @Before
    public void setUp() {
        hashMap = new HashMapImpl<>();
        hashMap.put(1, 1L);
        hashMap.put(51, 3L);
        hashMap.put(101, 5L);
    }

    @Test
    public void checkSizeOfEmptyHashMap() {
        Assert.assertEquals(0, new HashMapImpl<>().size());
    }

    @Test
    public void checkSizeAfterPut() {
        int actual = hashMap.size();
        Assert.assertEquals(3, actual);
    }

    @Test
    public void checkSizeAfterResize() {
        HashMapImpl<Integer, Long> map = new HashMapImpl<>();
        for (int i = 1; i <= 10000; i++) {
            map.put(i * 10 + 4, (long) i);
        }
        int actual = map.size();
        Assert.assertEquals(10000, actual);
    }

    @Test(expected = RuntimeException.class)
    public void checkNotExistedKey() {
        hashMap.get(0);
    }

    @Test
    public void getFirstValue() {
        long actual = hashMap.get(1);
        Assert.assertEquals(1L, actual);
    }

    @Test
    public void getThirdValue() {
        long actual = hashMap.get(101);
        Assert.assertEquals(5L, actual);
    }

    @Test
    public void checkPutOfSameKey() {
        hashMap.put(51, 5L);
        long actual = hashMap.get(51);
        Assert.assertEquals(5L, actual);
    }
}
