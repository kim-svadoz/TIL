// 문자열 그래프 위상정렬
import java.io.*;
import java.util.*;

public class p21276 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, k;
    static String[] person;
    static int[] parent;
    static int[] degree;
    static int[] grandParent;
    static List<List<Integer>> list;
    static Map<String, Integer> map = new HashMap<>();
    static Map<Integer, String> mapReverse = new HashMap<>();
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 총 사람 수
        person = new String[n];
        parent = new int[n];
        degree = new int[n];
        grandParent = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            person[i] = st.nextToken();
        }

        Arrays.sort(person);

        for (int i = 0; i < n; i++) {
            // 출력을 위해 String <-> Integer 를 저장할 수 있는 Map을 두 개 만들어 사용한다
            map.put(person[i], i);
            mapReverse.put(i, person[i]);
        }

        m = Integer.parseInt(br.readLine()); // 기억하는 정보의 개수

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            String X = st.nextToken();
            String Y = st.nextToken();

            int x = map.get(X);
            int y = map.get(Y);

            list.get(y).add(x);
            degree[x]++;
        }

        solve();

    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();

        List<Integer> grandlist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 조상노드가 하나도 없는 노드를 q에 추가한다.
            if (degree[i] == 0) {
                grandlist.add(i);
                q.offer(i);
            }
        }

        System.out.println(q.size());
        for (int i = 0; i < q.size(); i++) {
            System.out.print(mapReverse.get(grandlist.get(i))+" ");
        }
        System.out.println();

        // key값 정렬을 위해 HashMap 대신 TreeMap을 사용한다.
        TreeMap<String, List<String>> resultMap = new TreeMap<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<String> mList = new ArrayList<>();

            if (list.get(cur).size() == 0) {
                String motherName = mapReverse.get(cur);
                List<String> dumpList = new ArrayList<>();
                dumpList.add("-");
                resultMap.put(motherName, dumpList);
            }

            for (Integer next : list.get(cur)) {
                String childName= mapReverse.get(next);
                String motherName = mapReverse.get(cur);

                if (degree[next] - 1 == degree[cur]) {
                    if (resultMap.containsKey(motherName)) {
                        mList = resultMap.get(motherName);
                        mList.add(childName);
                    } else {
                        mList.add(childName);
                    }
                  
                    resultMap.put(motherName, mList);
                } 

                degree[next]--;
                if (degree[next] == 0) {
                    q.add(next);
                }

                resultMap.put(motherName, mList);
            }
        }

        Iterator<String> keyit = resultMap.keySet().iterator();
        String key;
        List<String> resultList = new ArrayList<>();
        while (keyit.hasNext()) {
            key = (String)keyit.next();
            System.out.print(key + " ");
            resultList = resultMap.get(key);
            Collections.sort(resultList);

            if (resultList.get(0) == "-") {
                System.out.print("0");
                System.out.println();
            } else {
                System.out.print(resultList.size()+" ");
                for (String s : resultList) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        }
    }
}