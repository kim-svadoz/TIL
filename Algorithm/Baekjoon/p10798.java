import java.io.*;

public class p10798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputArr = new String[5];
        int max_len = 0;
        for (int i = 0; i < 5; i++) {
            inputArr[i] = br.readLine();
            max_len = Math.max(max_len, inputArr[i].length());
        }

        char[][] arr = new char[5][max_len];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < inputArr[i].length(); j++) {
                arr[i][j] = inputArr[i].charAt(j);
            }
        }
        for (int i = 0; i < max_len; i++) {
            for (int j = 0; j < 5; j++) { 
                if (arr[j][i] == '\0') continue;
                
                System.out.print(arr[j][i]);
            }
        }
    }
}
