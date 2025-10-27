import java.util.*;
import java.io.*;
 
class Main {    
    static int stoi(String s) { return Integer.parseInt(s);}
 
    // ��Ϲ��� [��ȣ][����]
    static int[][] arr = new int[4][8];
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        for(int i=0; i<4; i++) {
            String s = br.readLine();
            for(int j=0; j<8; j++)
                arr[i][j] = s.charAt(j) - '0';
        }
 
        int k = stoi(br.readLine());
 
        // ��Ϲ��� ȸ��ddd
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = stoi(st.nextToken());
            int dir = stoi(st.nextToken());
 
            // ��Ϲ��� ��ȣ�� 1~4, �ε����� 0~3
            solution(idx-1, dir);
        }
 
        // ���� ���
        int score = 0;
        for(int i=0; i<4; i++)
            score += arr[i][0] * (1<<i);
        
        System.out.println(score);
    }
 
    // 9�� ������ 2, 3�� ������ 6
    static void solution(int idx, int dir) {
        left(idx-1, -dir);
        right(idx+1, -dir);
        rotate(idx, dir);
    }
    
    // ���ʿ� �ִ� ��Ϲ��� ȸ�� ���� ����
    static void left(int idx, int dir) {
        if(idx < 0)
            return;
 
        if(arr[idx][2] != arr[idx+1][6]) {
            left(idx-1, -dir);
            rotate(idx, dir);
        }
    }
 
    // �����ʿ� �ִ� ��Ϲ��� ȸ�� ���� ��������
    static void right(int idx, int dir) {
        if(idx > 3)
            return;
 
        if(arr[idx][6] != arr[idx-1][2]) {
            right(idx+1, -dir);
            rotate(idx, dir);
        }
    }
 
    // dir = 1 �ð����, dir = -1 �ݽð����
    static void rotate(int idx, int dir) {
 
        if(dir == 1) {
            int temp = arr[idx][7];
 
            for(int i=7; i>0; i--)
                arr[idx][i] = arr[idx][i-1];
            
            arr[idx][0] = temp;
        } 
        else {    
            int temp = arr[idx][0];
 
            for(int i=0; i<7; i++)
                arr[idx][i] = arr[idx][i+1];
            
            arr[idx][7] = temp;
        }
    }    
}