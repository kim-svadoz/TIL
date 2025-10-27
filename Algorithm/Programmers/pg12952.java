public class pg12952 {
    int cnt = 0;
    int k = 0;
    int[] cols;

    public int solution(int n) {
        cols = new int[n + 1];
        k = n;
        queens(0);
        
        return cnt;
    }

    void queens(int level) {
        if (level == k) {
            cnt++;    
        }
        
        for (int i = 0; i < k; i++) {
            cols[level] = i;	// 들어온 level행 에 value i를 넣어보고
            if (promising(level)) {	// 들어갈 수 있는 곳이라면
                queens(level + 1);
            }
        }
    }
    
    boolean promising(int level) {
        for (int i = 0; i < level; i++) {	// level 행 전까지 다 돌아 본다
            if (cols[i] == cols[level]) return false;	// 내(level)가 가지고 있는 값을 들고있다면
            else if (Math.abs(level - i) == Math.abs(cols[level] - cols[i])) return false; // 혹은 대각선
        }
        return true;
    }
}
