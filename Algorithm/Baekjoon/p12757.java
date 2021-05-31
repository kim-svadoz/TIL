/*
    전설의 JBNU
    TreeMap
     - ceilingKey(key) : treeMap에서 key보다 크거나 같은 값 중에 최소 키 값
     - floorKey(key) : treeMap에서 key보다 작거나 같은 값 중에 최대 키 값
*/
import java.io.*;
import java.util.*;
public class p12757 {
    static int n, m, k;
    static TreeMap<Integer, Integer> tm;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        // 초기 데이터
        tm = new TreeMap<>();
        tm.put(-2000000000, -1);
        tm.put(2000000000, -1);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            tm.put(key, value);
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            
            if (command == 1) { // 데이터 추가
                int key = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                tm.put(key, value);
            } else if (command == 2) { // 데이터 수정
                int key = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(key, value);
            } else { // 데이터 출력
                int key = Integer.parseInt(st.nextToken());
                print(key);
            }
        }
    }
    
    static void update(int key, int value) {
        // ceilingKey(key) : treeMap에서 key보다 크거나 같은 값 중에 최소 키 값
        // floorKey(key) : treeMap에서 key보다 작거나 같은 값 중에 최대 키 값
        int rightKey = tm.ceilingKey(key);
        int leftKey = tm.floorKey(key);
        
        if (tm.get(rightKey) == tm.get(leftKey)) {
            tm.put(key, value);
        } else if (rightKey - key < key - leftKey && k >= rightKey - key) {
            tm.put(rightKey, value);
        } else if (rightKey - key > key - leftKey && k >= key - leftKey) {
            tm.put(leftKey, value);
        }
    }
    
    static void print(int key) {
        int rightKey = tm.ceilingKey(key);
        int leftKey = tm.floorKey(key);
        
        if (tm.get(rightKey) == tm.get(leftKey)) {
            System.out.println(tm.get(key));
        } else if (rightKey - key == key - leftKey && k >= rightKey - key) {
            System.out.println("?");
        } else if (rightKey - key < key - leftKey && k >= rightKey - key) {
            System.out.println(tm.get(rightKey));
        } else if (rightKey - key > key - leftKey && k >= key - leftKey) {
            System.out.println(tm.get(leftKey));
        } else {
            System.out.println("-1");
        }
    }
}