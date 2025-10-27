import java.util.*;
public class LC146_LRUCache {
    // 시간복잡도 : O(1);
    // doubly linked list + HashMap !!
    public class CacheItem {
        int key;
        int value;
        CacheItem prev;
        CacheItem next;
        public CacheItem(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    CacheItem head;
    CacheItem tail;
    int capacity;
    Map<Integer, CacheItem> map;
    
    public LC146_LRUCache(int capacity) {
        // 생성자
        head = null;
        tail = null;
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            CacheItem cur = map.get(key);
            
            // head 포인터를 바꿔주어야 한다.
            if (cur != head) {
                if (cur == tail) {
                    // move tail to one in front
                    tail = tail.prev;
                }
                
                // move cur to head
                //    head .... cur.prev > cur > cur.next
                // => cur-head .... cur.prev > cur.next 
                if (cur.prev != null) cur.prev.next = cur.next;
                if (cur.next != null) cur.next.prev = cur.prev;
                cur.next = head;
                head.prev = cur;
                cur.prev = null;
                head = cur;
            }
            
            return cur.value;
        }
    }
    
    public void put(int key, int value) {
        if (get(key) == -1) {
            // insert
            CacheItem cur = new CacheItem(key, value);
            
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                // head .....
                // cur > head .....
                cur.next = head;
                head.prev = cur;
                head = cur;
            }
            
            map.put(key, cur);
            
            if (map.size() > capacity) {
                // evict tail
                // ..... tail.prev > tail
                // ..... tail.prev
                map.remove(tail.key);
                tail.prev.next = null;
                tail = tail.prev;
            }
        } else {
            // update
            map.get(key).value = value;
            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */