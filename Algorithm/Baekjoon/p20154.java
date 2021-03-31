import java.io.*;

public class p20154 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int[] arr = {
            3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1
        };
        int[] strToNumArr = new int[line.length()];

        int sum = 0;

        for (int i = 0; i < strToNumArr.length; i++) {
            strToNumArr[i] = arr[line.charAt(i) - 'A'];
        }

        for (int i = 0; i < strToNumArr.length; i++) {
            sum += strToNumArr[i];
            sum %= 10;
        }

        if (sum % 2 == 0) {
            System.out.println("You're the winner?");;
        } else {
            System.out.println("I'm a winner!");
        }
    }
}
