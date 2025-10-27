/*
    문제 추천 시스템 ver2
    < this is fail code > : 시간초과

    TreeMap
    - firstEntry() : 현재 맵에서 가장 작은 키 값에 대한 정보
    - lastEntry() : 현재 맵에서 가장 큰 키 값에 대한 정보
    - ceilingKey(key) : treeMap에서 key보다 크거나 같은 값 중에 최소 키 값
    - floorKey(key) : treeMap에서 key보다 작거나 같은 값 중에 최대 키 값

    fail reason 1)
    recommend3 에서 예외처리를 생각 못했다.
    x == 1 : 현재 가진 lmap에서 가장 난이도가 큰 것이 l보다도 작다면 조건에 만족하지 않고
    x == -1 : 현재 가진 lmap에서 가장 난이도가 작은이 l보다도 작다면 조건에 만족하지 않음을
    파악해야한다.

    fail reason 2) 시간초과
    HOW TO?
*/
import java.io.*;
import java.util.*;
public class p21944 {
    static int n, m;
    static TreeMap<Integer, List<Integer>> lMap;
    static TreeMap<Integer, TreeMap<Integer, List<Integer>>> gMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        lMap = new TreeMap<>();
        gMap = new TreeMap<>();
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            addProblem(p, l, g);
        }

        StringBuilder ans = new StringBuilder();
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("recommend")) {
                int g = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    Collections.sort(gMap.get(g).lastEntry().getValue());
                    int len = gMap.get(g).lastEntry().getValue().size();
                    ans.append(gMap.get(g).lastEntry().getValue().get(len - 1)+"\n");
                } else {
                    Collections.sort(gMap.get(g).firstEntry().getValue());
                    int len = gMap.get(g).firstEntry().getValue().size();
                    ans.append(gMap.get(g).firstEntry().getValue().get(0)+"\n");
                }
            } else if (command.equals("recommend2")) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    Collections.sort(lMap.lastEntry().getValue());
                    int len = lMap.lastEntry().getValue().size();
                    ans.append(lMap.lastEntry().getValue().get(len - 1)+"\n");
                } else {
                    Collections.sort(lMap.firstEntry().getValue());
                    int len = lMap.firstEntry().getValue().size();
                    ans.append(lMap.firstEntry().getValue().get(0)+"\n");
                }
            } else if (command.equals("recommend3")) {
                int x = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    if (lMap.lastKey() < l) {
                        ans.append("-1\n");
                    } else {
                        Collections.sort(lMap.ceilingEntry(l).getValue());
                        if (lMap.ceilingEntry(l).getValue().size() == 0) {
                            ans.append("-1\n");
                        } else {
                            ans.append(lMap.ceilingEntry(l).getValue().get(0)+"\n");
                        }
                    }
                } else {
                    if (lMap.firstKey() > l) {
                        ans.append("-1\n");
                    } else {
                        Collections.sort(lMap.floorEntry(l).getValue());
                        int len2 = lMap.floorEntry(l).getValue().size();
                        if (len2 == 0) {
                            ans.append("-1\n");
                        } else {
                            ans.append(lMap.floorEntry(l).getValue().get(len2 - 1)+"\n");
                        }
                    }
                }
            } else if (command.equals("add")) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                addProblem(p, l, g);
            } else if (command.equals("solved")) {
                int p = Integer.parseInt(st.nextToken());
                removeProblem(p);
            }
        }
        System.out.println(ans.toString());
    }
    static void addProblem(int p, int l, int g) {
        if (!gMap.containsKey(g)) {
            gMap.put(g, new TreeMap<>());
        }

        if (!gMap.get(g).containsKey(l)) {
            gMap.get(g).put(l, new ArrayList<>());
        }

        TreeMap<Integer, List<Integer>> tm = gMap.get(g);
        List<Integer> list = tm.get(l);
        list.add(p);
        tm.put(l, list);
        gMap.put(g, tm);


        if (!lMap.containsKey(l)) {
            lMap.put(l, new ArrayList());
        } 
        List<Integer> list2 = lMap.get(l);
        list2.add(p);
        lMap.put(l, list2);
    }

    static void removeProblem(int p) {
        boolean flag = false;
        for (int key : gMap.keySet()) {
            for (int key2 : gMap.get(key).keySet()) {
                if (gMap.get(key).get(key2).contains(p)) {
                    List<Integer> list = gMap.get(key).get(key2);
                    List<Integer> newList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) == p) {
                            flag = true;
                            continue;
                        }
                        newList.add(list.get(i));
                    }
                    gMap.get(key).remove(key2);
                    if (newList.size() > 0) {
                        gMap.get(key).put(key2, newList);
                    }
                    break;
                }
            }
            if (flag) break;
        }

        flag = false;
        for (int key : lMap.keySet()) {
            if (lMap.get(key).contains(p)) {
                List<Integer> list = lMap.get(key);
                List<Integer> newList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) == p) {
                        flag = true;
                    } else {
                        newList.add(list.get(i));
                    }
                }

                if (newList.size() == 0) {
                    lMap.remove(key);
                } else {
                    lMap.put(key, newList);
                }

                break;
            }
            if (flag) break;
        }
    }
}
