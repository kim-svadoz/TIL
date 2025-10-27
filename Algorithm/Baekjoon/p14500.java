/*
1. 'ㅗ' 를 제외한 'ㅁ', 'ㄱ', 'ㅡ', 등의 모형은 DFS로 한번에 다 그릴 수 있다.

2. 0,0 부터 N,M까지 시작점을 바꿔가며 depth 4로 조건을 주어 구현한다.

3. 'ㅗ' 모형은 ㅗ,ㅓ,ㅏ,ㅜ 로 돌아 갈 수 있기 때문에 '┼' 에서 날개 하나를 빼는 식으로 구현한다.

4. 날개가 2개 이하일 때는 함수를 종료시켜 계산을 하지 않는다.

5. 계산값들 중 최고 값을 출력한다.
*/
import java.io.*;
import java.util.*;
public class p14500 {
    static int n, m, max = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 0, 0);
                exception(i, j);
            }
        }
        System.out.println(max);
    }
    
    // 상하좌우 가능한 모양들 (ㅗ 모양 제외)
    // ㅗ 모양은 dfs로 구현이 불가능
    static void dfs(int r, int c, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (OOB(nr, nc)) continue;
            if (visited[nr][nc]) continue;
            
            visited[nr][nc] = true;
            dfs(nr, nc, depth + 1, sum + map[nr][nc]);
            visited[nr][nc] = false;
        }
    }
    
    // 'ㅗ' 모양 구현
    // + 모양에서 각 꼭짓점을 하나씩 뗀다.
    static void exception(int r, int c) {
        int wing = 4; // 상하좌우 날개
        int min = Integer.MAX_VALUE;
        int sum = map[r][c];
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            // 날개가 맵 바깥에 있다면 날개가 아닌 것이므로 하나 뗀다.
            if (OOB(nr, nc)) {
                wing--;
                continue;
            }
            if (wing <= 2) return;
            min = Math.min(min, map[nr][nc]);
            sum = sum + map[nr][nc];
        }
		
        // 날개가 4개일 때는 가장 작은 날개를 없애야 최대의 ㅗ ㅏ ㅓ ㅜ 모양이 나온다.
        if (wing == 4) {
            sum = sum - min;
        }
        max = Math.max(max, sum);
    }
    
    static boolean OOB(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= m;
    }
}

/* 완전 완전탐색

public class  Main {
static int map [][];
static int x, y;
static int max=0;

static void dfs(int a, int b){
    int sum=0;
    // 1-1. 직선 case (세로 놓기)
    sum += map[a][b];
    sum += map[a+1][b];
    sum += map[a+2][b];
    sum += map[a+3][b];
    if(max<sum){
        max = sum;
    }
    // 1-2 직선 case (가로 놓기)
    sum=0;
    sum += map[a][b];
    sum += map[a][b+1];
    sum += map[a][b+2];
    sum += map[a][b+3];
    if(max<sum){
        max = sum;
    }
    // 2. 네모 case
    sum=0;
    sum += map[a][b];
    sum += map[a+1][b];
    sum += map[a+1][b+1];
    sum += map[a][b+1];
    if(max<sum){
        max = sum;
    }
    // 3-1. ㄴ case // 왼상단시작 오른 하단 종료. (반시계방향)
    sum=0;
    sum += map[a][b];
    sum += map[a+1][b];
    sum += map[a+2][b];
    sum += map[a+2][b+1];
    if(max<sum){
        max = sum;
    }
    // 3-1-2. ㄴ case // 왼상단시작 오른 하단 종료. (반시계방향)의 대칭.
    sum=0;
    sum += map[a][b+1];
    sum += map[a+1][b+1];
    sum += map[a+2][b+1];
    sum += map[a+2][b];
    if(max<sum){
        max = sum;
    }
    // 3-2. ㄴ case // 왼하단시작 오른 상단 종료.
    sum=0;
    sum += map[a][b];
    sum += map[a+1][b];
    sum += map[a][b+1];
    sum += map[a][b+2];
    if(max<sum){
        max = sum;
    }
    // 3-2-2. ㄴ case // 왼하단시작 오른 상단 종료. 의 대
    sum=0;
    sum += map[a][b];
    sum += map[a+1][b+2];
    sum += map[a][b+1];
    sum += map[a][b+2];
    if(max<sum){
        max = sum;
    }
    // 3-3. ㄴ case // 왼상단시작 오른 하단 종료 (시계방향)
    sum=0;
    sum += map[a][b];
    sum += map[a][b+1];
    sum += map[a+1][b+1];
    sum += map[a+2][b+1];
    if(max<sum){
        max = sum;
    }
    // 3-3-2. ㄴ case // 왼상단시작 오른 하단 종료 (시계방향) 의 대칭
    sum=0;
    sum += map[a][b];
    sum += map[a][b+1];
    sum += map[a+1][b];
    sum += map[a+2][b];
    if(max<sum){
        max = sum;
    }
    // 3-4. ㄴ case // 오른 상단시작 왼 하단 종료
    sum=0;
    sum += map[a][b+2];
    sum += map[a+1][b+2];
    sum += map[a+1][b+1];
    sum += map[a+1][b];
    if(max<sum){
        max = sum;
    }
    // 3-4-2. ㄴ case // 오른 상단시작 왼 하단 종료 의 대칭
    sum=0;
    sum += map[a+1][b+2];
    sum += map[a+1][b+1];
    sum += map[a+1][b];
    sum += map[a][b];
    if(max<sum){
        max = sum;
    }
    // 4-1. ㄴㄱ case // 왼상단시작 오른 하단 종료. (ㄴㄱ)
    sum=0;
    sum += map[a][b];
    sum += map[a+1][b];
    sum += map[a+1][b+1];
    sum += map[a+2][b+1];
    if(max<sum){
        max = sum;
    }
    // 4-2. ㄴㄱ case // 오른 상단시작 하단 종료.
    sum=0;
    sum += map[a][b+2];
    sum += map[a][b+1];
    sum += map[a+1][b+1];
    sum += map[a+1][b];
    if(max<sum){
        max = sum;
    }
    // 4-3. ㄴㄱ case // 왼하단시작 오른 상단 종료.
    sum=0;
    sum += map[a+2][b];
    sum += map[a+1][b];
    sum += map[a+1][b+1];
    sum += map[a][b+1];
    if(max<sum){
        max = sum;
    }
    // 4-4. ㄴㄱ case // 왼상단시작 오른 하단 종료. (ㄱㄴ꼴)
    sum=0;
    sum += map[a][b];
    sum += map[a][b+1];
    sum += map[a+1][b+1];
    sum += map[a+1][b+2];
    if(max<sum){
        max = sum;
    }
    // 5-1. ㅗ case // ㅜ
    sum=0;
    sum += map[a][b];
    sum += map[a][b+1];
    sum += map[a][b+2];
    sum += map[a+1][b+1];
    if(max<sum){
        max = sum;
    }
    // 5-2. ㅗ case // ㅓ
    sum=0;
    sum += map[a][b+1];
    sum += map[a+1][b+1];
    sum += map[a+2][b+1];
    sum += map[a+1][b];
    if(max<sum){
        max = sum;
    }
    // 5-3. ㅗ case // ㅗ
    sum=0;
    sum += map[a+1][b];
    sum += map[a+1][b+1];
    sum += map[a+1][b+2];
    sum += map[a][b+1];
    if(max<sum){
        max = sum;
    }
    // 5-4. ㅗ case // ㅏ
    sum=0;
    sum += map[a][b];
    sum += map[a+1][b];
    sum += map[a+2][b];
    sum += map[a+1][b+1];
    if(max<sum){
        max = sum;
    }
}

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String p = br.readLine();
    StringTokenizer st = new StringTokenizer(p, " ");
    y = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    map = new int[x+6][y+6];

    for(int i = 3; i<y+3; i++){
        String o = br.readLine();
        st = new StringTokenizer(o, " ");
        for(int j = 3; j<x+3; j++){
            map[j][i] = Integer.parseInt(st.nextToken());
        }
    }
    for(int j=0; j<x+3; j++){
        for(int i=0; i<y+3; i++){
            dfs(j, i); //x, y랑 값바꿔서 틀림...
        }
    }
    System.out.println(max);
    }
}

*/