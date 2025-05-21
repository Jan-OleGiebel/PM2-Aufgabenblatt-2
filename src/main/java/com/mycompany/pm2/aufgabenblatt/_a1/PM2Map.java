/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pm2.aufgabenblatt._a1;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * null darf nicht als Schlüssel verwendet werden! Die Verwendung als Wert, ist ok.
 * @author infwuy396
 */
public class PM2Map<K, V> implements Map<K, V> {
    
    public class MapPaar<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;
        
        /**
         * Constructs a new MapPaar<K, V> object.
         * 
         * @param key
         * @param value 
         * @trows IllegalArgumentException, if the specified key is null.
         */
        public MapPaar(K key, V value) {
            Preconditions.checkNotNull(key);
            
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
        
        @Override
        public V setValue(V value) {            
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        
        @Override
        public boolean equals(Object o) {
            return (this.getKey() == null ?
                    ((MapPaar<K, V>)o).getKey() == null : this.getKey().equals(((MapPaar<K, V>)o).getKey())) &&
                    (this.getValue() == null ?
                    ((MapPaar<K, V>)o).getValue() == null : this.getValue().equals(((MapPaar<K, V>)o).getValue()));
        }
        
        @Override
        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^
                    (this.getValue() == null ? 0 : this.getValue().hashCode());
        }
    }
    
    private MapPaar<K, V>[] map = new MapPaar[0];
    private long numberOfElements;

    @Override
    public int size() {
        if(numberOfElements > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int)numberOfElements;
        }
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for(MapPaar<K, V> element : map) {
            if(element != null) {
                if(Objects.equals(element.getValue(), (V)value)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public V get(Object key) {
        for(MapPaar<K, V> element : map) {
            if(element != null) {
                if(Objects.equals(element.getKey(), (K)key)) {
                    return element.getValue();
                }
            }
        }
        
        return null;
    }
    
    /**
     * Adds the specified key/value pair to the map.
     * 
     * @param key
     * @param value
     * @trows IllegalArgumentException, if the given key is null.
     * @return 
     */
    @Override
    public V put(K key, V value) {
        Preconditions.checkNotNull(key);
        
        V oldValue = null;
        var keyExisted = false;
        
        for(MapPaar<K, V> element : map) {
            if(element != null) {
                if(Objects.equals(element.getKey(), (K)key)) {
                    oldValue = element.getValue();
                    element.setValue(value);
                    keyExisted = true;
                }
            }
        }
        
        if(!keyExisted) {
            if(map.length < (numberOfElements + 1)) {
                this.resize();
            }

            MapPaar<K, V> elementToAdd = new MapPaar<K, V>(key, value);
            map[(int)numberOfElements] = elementToAdd;

            numberOfElements++;
        }
        
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        V oldValue = this.get(key);
        
        for(int i=0; i<map.length; i++) {
            MapPaar<K, V> element = map[i];
            
            if(element != null) {
                if(Objects.equals(element.getKey(), (K)key)) {
                    MapPaar<K, V>[] newMap = new MapPaar[map.length];
                    int lengthPart1 = i;
                    if(i == 0) {
                        lengthPart1 = 1;
                    }
                    System.arraycopy(map, 0, newMap, 0, lengthPart1);
                    if((i+1) < numberOfElements) {
                        System.arraycopy(map, (i+1), newMap, i, ((int)numberOfElements) / 2);
                    }

                    map = newMap;

                    numberOfElements--;
                }
            }
        }
        
        return oldValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.keySet();
        for(K key : m.keySet()) {
            V value = m.get(key);
            this.put(key, value);
        }
    }

    @Override
    public void clear() {
        map = new MapPaar[0];
        
        numberOfElements = 0;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet((int)numberOfElements);
        
        for(MapPaar<K, V> mapPaar : map) {
            if(mapPaar != null) {
                keySet.add(mapPaar.getKey());
            }
        }
        
        return keySet;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> valueArrayList = new ArrayList<V>((int)numberOfElements);
        
        for(MapPaar<K, V> mapPaar : map) {
            if(mapPaar != null) {
                valueArrayList.add(mapPaar.getValue());
            }
        }
        
        return valueArrayList;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        HashSet<Map.Entry<K, V>> entrySet = new HashSet((int)numberOfElements);
        
        for(MapPaar<K, V> mapPaar : map) {
            if(mapPaar != null) {
                entrySet.add(mapPaar);
            }
        }
        
        return entrySet;
    }
    
    private void resize() {
        int newMapSize;
        if(map.length == 0) {
            newMapSize = 1;
        } else {
            newMapSize = map.length * 2;
        }
        
        MapPaar<K, V>[] newMap = new MapPaar[newMapSize];
        System.arraycopy(map, 0, newMap, 0, map.length);
        map = newMap;
    }
    
    @Override
    public int hashCode() {
        var hashCode = 0;
        
        for(MapPaar<K, V> mapPaar : map) {
            hashCode += mapPaar.hashCode();
        }
        
        return hashCode;
    }
    
    @Override
    public boolean equals(Object mapToCompare) {
        return this.entrySet().equals(((Map<K, V>)mapToCompare).entrySet());
    }
}