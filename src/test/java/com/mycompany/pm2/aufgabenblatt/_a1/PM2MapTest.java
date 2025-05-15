package com.mycompany.pm2.aufgabenblatt._a1;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PM2MapTest {
    
    private PM2Map<String,Integer> map;

    @BeforeEach
    public void setUp() {
        map = new PM2Map<>();
    }

    @AfterEach
    public void tearDown() {
        map = null;
    }

    @Test
    public void testSizeAndIsEmpty_onNewMap() {
        assertEquals(0, map.size(), "new map should have size 0");
        assertTrue(map.isEmpty(), "new map should be empty");
    }
    
    @Test
    public void testPutAndGet_andSize() {
        Integer prev = map.put("a", 1);
        assertNull(prev, "put on new key returns null");
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertEquals(1, map.get("a"));
        
        // overwrite
        prev = map.put("a", 2);
        assertEquals(1, prev.intValue(), "put on existing key returns old value");
        assertEquals(2, map.get("a"), "value should be overwritten");
        assertEquals(1, map.size(), "size should not change when overwriting");
    }
    
    @Test
    public void testPutNullKey_throwsNPE() {
        assertThrows(NullPointerException.class, () -> map.put(null, 5));
    }
    
    @Test
    public void testContainsKeyAndContainsValue() {
        assertFalse(map.containsKey("x"));
        assertFalse(map.containsValue(99));
        map.put("x", 99);
        assertTrue(map.containsKey("x"));
        assertTrue(map.containsValue(99));
    }
    
    @Test
    public void testRemove_existingAndNonExisting() {
        map.put("k1", 10);
        map.put("k2", 20);
        assertEquals(2, map.size());
        
        Integer removed = map.remove("k1");
        assertEquals(10, removed.intValue());
        assertFalse(map.containsKey("k1"));
        assertEquals(1, map.size());
        
        // removing again returns null and size unchanged
        assertNull(map.remove("k1"));
        assertEquals(1, map.size());
    }
    
    @Test
    public void testPutAllAndClear() {
        HashMap<String,Integer> other = new HashMap<>();
        other.put("one", 1);
        other.put("two", 2);
        map.putAll(other);
        
        assertEquals(2, map.size());
        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
    
    @Test
    public void testKeySetValuesEntrySet() {
        map.put("a", 100);
        map.put("b", 200);
        map.put("c", 300);
        
        Set<String> keys = map.keySet();
        assertEquals(Set.of("a","b","c"), keys);
        
        Collection<Integer> vals = map.values();
        assertTrue(vals.containsAll(Set.of(100,200,300)));
        assertEquals(3, vals.size());
        
        Set<Map.Entry<String,Integer>> entries = map.entrySet();
        // build expected
        Set<Map.Entry<String,Integer>> expected = new HashSet<>();
        expected.add(new Map.Entry<>() {
            public String getKey() { return "a"; }
            public Integer getValue() { return 100; }
            public Integer setValue(Integer v) { throw new UnsupportedOperationException(); }
            public boolean equals(Object o) {
                if (!(o instanceof Map.Entry)) return false;
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                return "a".equals(e.getKey()) && Integer.valueOf(100).equals(e.getValue());
            }
            public int hashCode() { return "a".hashCode() ^ Integer.valueOf(100).hashCode(); }
        });
        expected.add(new Map.Entry<>() {
            public String getKey() { return "b"; }
            public Integer getValue() { return 200; }
            public Integer setValue(Integer v) { throw new UnsupportedOperationException(); }
            public boolean equals(Object o) {
                if (!(o instanceof Map.Entry)) return false;
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                return "b".equals(e.getKey()) && Integer.valueOf(200).equals(e.getValue());
            }
            public int hashCode() { return "b".hashCode() ^ Integer.valueOf(200).hashCode(); }
        });
        expected.add(new Map.Entry<>() {
            public String getKey() { return "c"; }
            public Integer getValue() { return 300; }
            public Integer setValue(Integer v) { throw new UnsupportedOperationException(); }
            public boolean equals(Object o) {
                if (!(o instanceof Map.Entry)) return false;
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                return "c".equals(e.getKey()) && Integer.valueOf(300).equals(e.getValue());
            }
            public int hashCode() { return "c".hashCode() ^ Integer.valueOf(300).hashCode(); }
        });
        
        assertEquals(expected, entries, "entrySet should contain all key/value pairs");
    }
}