/*
    프로그래머스 > 위클리 챌린지 > 3주차
*/
import java.util.*;

public class pg84021 {
    public static void main(String[] args) {
        int[][] game_board = {
            {1,1,0,0,1,0},
            {0,0,1,0,1,0},
            {0,1,1,0,0,1},
            {1,1,0,1,1,1},
            {1,0,0,0,1,0},
            {0,1,1,1,0,0}
        };
        int[][] table = {
            {1,0,0,1,1,0},
            {1,0,1,0,1,0},
            {0,1,1,0,1,1},
            {0,0,1,0,0,0},
            {1,1,0,1,1,0},
            {0,1,0,0,0,0}
        };
        System.out.println(solution(game_board, table));
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static HashMap<String, Point> blockStore = new HashMap<>();
    static int cnt = 0;
    static ArrayList<Point> block;

    public static int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        for (int i = 0; i < game_board.length; i++)
            for (int j = 0; j < game_board.length; j++)
                game_board[i][j] = game_board[i][j] == 0 ? 1 : 0;
        for (int i = 0; i < game_board.length; i++)
            for (int j = 0; j < game_board.length; j++)
                if (game_board[i][j] == 1) {
                    String blockString = bfs(game_board, i, j);
                    Point bp = blockStore.getOrDefault(blockString, new Point(0, cnt));
                    bp.x++;
                    blockStore.put(blockString, bp);
                }
        for (int r = 0; r < 4; r++) {
            table = rotate(table);
            int[][] temptable = new int[table.length][table.length];
            for (int d = 0; d < table.length; d++)
                temptable[d] = table[d].clone();
            for (int i = 0; i < temptable.length; i++)
                for (int j = 0; j < temptable.length; j++)
                    if (temptable[i][j] == 1) {
                        String blockString = bfs(temptable, i, j);
                        if (blockStore.containsKey(blockString)) {
                            for (Point bp : block)
                                table[bp.x][bp.y] = 0;
                            Point p = blockStore.get(blockString);
                            answer += p.y;
                            if (--p.x == 0)
                                blockStore.remove(blockString);
                            else
                                blockStore.put(blockString, p);
                        }
                    }
        }
        return answer;
    }

    static int[][] rotate(int[][] board) {
        int[][] newboard = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                newboard[j][board.length - i - 1] = board[i][j];
        return newboard;
    }

    static String bfs(int[][] board, int x, int y) {
        int dr[] = new int[]{1, -1, 0, 0};
        int dc[] = new int[]{0, 0, 1, -1};
        int minX = 987654321;
        int minY = 987654321;
        int maxX = 0;
        int maxY = 0;
        Queue<Point> que = new LinkedList<>();
        block = new ArrayList<>();
        que.add(new Point(x, y));
        board[x][y] = 0;
        cnt = 0;

        while (!que.isEmpty()) {
            Point now = que.poll();
            cnt++;
            minX = Math.min(now.x, minX);
            minY = Math.min(now.y, minY);
            maxX = Math.max(now.x, maxX);
            maxY = Math.max(now.y, maxY);
            block.add(new Point(now.x, now.y));
            for (int i = 0; i < 4; i++) {
                int nowx = now.x + dr[i];
                int nowy = now.y + dc[i];
                if (nowx < 0 || nowy < 0 || nowx >= board.length || nowy >= board.length)
                    continue;
                if (board[nowx][nowy] == 0)
                    continue;
                board[nowx][nowy] = 0;
                que.add(new Point(nowx, nowy));
            }
        }
        return makeString(minX, minY, maxX, maxY, block);

    }

    static String makeString(int minX, int minY, int maxX, int maxY, ArrayList<Point> block) {
        int[][] blockArr = new int[maxX - minX + 1][maxY - minY + 1];
        for (Point p : block)
            blockArr[p.x - minX][p.y - minY] = 1;
        String blockString = "";
        for (int i = 0; i < blockArr.length; i++) {
            for (int j = 0; j < blockArr[0].length; j++)
                blockString += blockArr[i][j];
            blockString += "n";
        }
        return blockString;
    }
}
