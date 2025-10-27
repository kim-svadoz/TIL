import java.io.*;
import java.util.*;

public class p1933 {
    static int n;
    static List<Building> list = new ArrayList<>();
    static class Building {
        int x, h;
        public Building (int x, int h) {
            this.x = x;
            this.h = h;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int lx = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            list.add(new Building(lx, h));
            list.add(new Building(rx, -h));
        }

        Collections.sort(list, new Comparator<Building>(){
            @Override
            public int compare(Building o1, Building o2) {
                // x높이가 다르다면 x 오름차순, x높이가 같다면 높이 내림차순
                if (o1.x == o2.x) {
                    return o2.h - o1.h;
                }
                return o1.x - o2.x;
            }
        });

        TreeMap<Integer, Integer> tm = new TreeMap<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        List<Building> ansList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Building cur = list.get(i);

            if (cur.h > 0) {
                tm.put(cur.h, tm.getOrDefault(cur.h, 0) + 1);
            } else {
                int key = -cur.h;
                int val = tm.get(key);
                if (val == 1) {
                    tm.remove(-cur.h);
                } else {
                    tm.put(key, val - 1);
                }
            }

            if (tm.size() == 0) {
                ansList.add(new Building(cur.x, 0));
                continue;
            }

            ansList.add(new Building(cur.x, tm.firstKey()));
        }

        bw.write(ansList.get(0).x + " " + ansList.get(0).h + " ");
        int prev = ansList.get(0).h;
        for (int i = 1; i < ansList.size(); i++) {
            if (prev != ansList.get(i).h) {
                bw.write(ansList.get(i).x + " " + ansList.get(i).h + " ");
                prev  = ansList.get(i).h;
            }
        }

        bw.write("\n");
        bw.flush();
        bw.close();
    }
}
