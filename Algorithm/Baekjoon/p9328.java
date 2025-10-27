/*
    열쇠 (Gold1)
    BFS

    ver1: 새로운 열쇠를 얻으면 BFS를 처음부터 다시 실행시키려 했으나 TLE
    ver2: 열쇠를 못찾은 문은 따로 저장해두어, 해당 열쇠를 찾았을 때 그 문의 위치에서 BFS를 실행토록 함
*/
import java.io.*;
import java.util.*;

public class p9328 {
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int T, h, w, answer;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static char[][] map;
    static List<List<Node>> gates; // 열지 못한 문을 저장
    static HashMap<Character, Character> keyMap;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            answer = 0;
            map = new char[h][w];
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            gates = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                gates.add(new ArrayList<>());
            }

            String hasKey = br.readLine();
            keyMap = matchKey(hasKey);
            go();
            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    static HashMap<Character, Character> matchKey(String s) {
        HashMap<Character, Character> hm = new HashMap<>();

        if (s.equals("0")) return hm;

        for (int i = 0; i < s.length(); i++) {
            char letter = (char)((int)(s.charAt(i)));
            hm.put(Character.toUpperCase(letter), letter);
        }
        
        return hm;
    }

    static void go() {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h - 1 || j == 0 || j == w - 1) {
                    char letter = map[i][j];
                    if (letter == '*') continue;
                    
                    if (97 <= letter && letter <= 122) {
                        keyMap.put(Character.toUpperCase(letter), letter);
                        visited[i][j] = true;
                        q.add(new Node(i, j));
                    } else if (letter == '$') {
                        map[i][j] = '.';
                        q.add(new Node(i, j));
                        visited[i][j] = true;
                        answer++;
                    } else if (letter == '.') {
                        visited[i][j] = true;
                        q.add(new Node(i, j));
                    }
                    
                    if (65 <= letter && letter <= 90) {
                        if (keyMap.containsKey(letter)) {
                            visited[i][j] = true;
                            q.add(new Node(i, j));
                        } else {
                            gates.get(letter - 'A').add(new Node(i, j));
                        }
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (OOB(nr, nc)) continue;
                char letter = map[nr][nc];
                if (letter == '*') continue;
                if (visited[nr][nc]) continue;

                if (97 <= letter && letter <= 122) { // 새로운 소문자 열쇠를 발견했으므로 keyMap에 추가한다.
                    q.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                    map[nr][nc] = '.';
                    keyMap.put(Character.toUpperCase(letter), letter);

                    // 혹시 이 열쇠로 열 수 있는 문이 있는지 없는지 확인한다!!!(시간 절약)
                    for (Node node : gates.get(letter - 'a')) {
                        map[node.r][node.c] = '.';
                        visited[node.r][node.c] = true;
                        q.add(new Node(node.r, node.c));
                    }
                } else if (letter == '$') {
                    q.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                    map[nr][nc] = '.';
                    answer++;
                } else if (letter == '.') {
                    q.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                } else if (65 <= letter && letter <= 90) {
                    if (keyMap.containsKey(letter)) { // 해당 대문자에 대한 열쇠를 가지고 있으므로 문을 열수 있다.
                        q.add(new Node(nr, nc));
                        map[nr][nc] = '.';
                        visited[nr][nc] = true;
                    } else {
                        // 아직 해당 문에 대한 열쇠가 없다면, 문을 저장해놓는다.
                        gates.get(letter - 'A').add(new Node(nr, nc));
                    }
                }

                
            }
        }
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= h || c >= w;
    }
}
