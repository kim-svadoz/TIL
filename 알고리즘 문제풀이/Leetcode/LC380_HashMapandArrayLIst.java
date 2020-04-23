package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomizedSet {

    //key: element, value:index in the list
    Map<Integer, Integer> map;
    ArrayList<Integer> list;
    //# of valid elements in the list.
    int size;
    Random r = new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        size=0;
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, size++);
        list.add(val);
        return true;
    }
    
    /*
    1 2 3 4, size 4
    del 2
    1 4 3 2,
    1 4 3, size 3
    */
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int idx = map.get(val);
        map.remove(val);
        if(idx!=size-1){
            int swap = list.get(size-1);
            list.set(idx, swap);
            map.put(swap, idx);
        }
        list.remove(--size);
        return true;
        
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = r.nextInt(size);
        return list.get(idx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */