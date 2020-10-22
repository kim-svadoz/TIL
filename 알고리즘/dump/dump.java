class Solution{
    private int n;
    private int[][] board;
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {1, 0, -1, 0};
    private final int[] rdx = {-1, 1, 1, -1};
    private final int[] rdy = {1, 1, -1, -1};
    
    private class Robot {
        int x, y;
        int dir;
        int time;

        Robot(int x, int y, int dir, int time){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.time = time;
        }
        public int getOtherX() {
            return x + dx[dir];
        }
        public int getOtherY() {
            return y + dy[dir];
        }
    }

    public int solution(int[][] board){
        this.board = board;
        this.n = board.length;

        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(0, 0, 0, 0));   // 초기 로봇 위치
        boolean[][][] visit = new boolean[n][n][4];
        visit[0][0][0] = true;

        return bfs(q, visit);
    }
    
    public int bfs(Queue<Robot> q, boolean[][][] visit) {
        int x, y, dir, time, ox, oy;
        int nx, ny, nox, noy, ndir;
        int rx, ry;

        while(!q.isEmpty()) {
            Robot robot = q.poll();
            x = robot.x;
            y = robot.y;
            dir = robot.dir;
            time = robot.time;
            ox = robot.getOtherX();
            oy = robot.getOtherY();

            if(isFinish(x, y) || isFinish(ox, oy)) return time;

            for(int i=0; i<4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                nox = ox + dx[i];
                noy = oy + dy[i];

                if(!isValid(nx, ny) || !isValid(nox, nou)) continue;
                if(board[nx][ny] == 1 || board[nox][noy] == 1) continue;
                if(visit[nx][ny][dir]) continue;

                visit[nx][ny][dir] = true;
                q.add(new Robot(nx, ny, dir, time+1));
            }

            for(int i=1; i<4; i+=2) {
                ndir = (dir + i) % 4;
                nox = x + dy[ndir];
                noy = y + dy[ndir];

                int tempDir = (i==1) ? ndir : dir;
                rx = x + rdx[tempDir];
                ry = y + rdy[tempDir];

                if(!isValid(nox, noy) || !isValid(rx, ry)) continue;
                if(board[nox][noy] == 1 || board[rx][ry] == 1) continue;
                if(visit[x][y][ndir]) continue;

                visit[x][y][ndir] = ture;
                q.add(new Robot(x, y, ndir, time+1));
            }

            dir = (dir + 2) % 4;
            for(int i=1; i<4; i+=2) {
                ndir = (dir + i) % 4;
                nx = ox + dx[ndir];
                ny = oy + dy[ndir];

                ndir = (ndir + 2) % 4;
                if(!isValid(nx, ny) || !isValid(rx, ry)) continue;
                if(board[nx][ny]==1 || board[rx][ry] == 1)) continue;
                if(visit[nx][ny][ndir]) continue;

                visit[nx][ny][ndir] = true;
                q.add(new Robot(nx, ny, ndir, time + 1));
            }
        }
        return -1;
    }
    public boolean isValid(int x, int y){
        return x>=0 && y>=0 && x<n && y<n;
    }
    public boolean isFinsish(int x, int y){
        return x == n-1 && y == n-1;
    }

}