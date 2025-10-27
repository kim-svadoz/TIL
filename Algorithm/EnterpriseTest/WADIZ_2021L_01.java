import java.util.*;
class test1 {
    public static void main(String[] args) {
        int[][] passwords = {
            {101,1234},
            {102,54321},
            {201,202},
            {202,1}
        };
        String s = "101#1234#102#654321#51#203#201#202#1#";
        solution(passwords, s);
    }
    static HashMap<Integer, Integer> hm;
    static public int solution(int[][] passwords, String s) {
        int answer = 0;
        hm = new HashMap<>();;
        
        for (int[] password : passwords) {
            int house = password[0];
            int pw = password[1];
            hm.put(house, pw);
        }
        
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                // 두번째 String인 비밀번호를 봐야할 조건을 만족한다면 sb2에 비밀번호를 저장한다.
                if (flag) {
                    sb2.append(s.charAt(i));
                } else {
                    sb1.append(s.charAt(i));
                }
            } else if (s.charAt(i) == '#') {
                // 비밀번호까지 모두 입력이 된 상태라면 정합성을 판단한다.
                if (flag) {
                    int key = Integer.parseInt(sb1.toString());
                    if (hm.containsKey(key)) {
                        if(hm.get(key) == Integer.parseInt(sb2.toString())) {
                            answer++;
                        }
                    }
                    sb1.delete(0, sb1.toString().length());
                    sb2.delete(0, sb2.toString().length());

                    flag = false;
                } else {
                    // 잘못된 호수이므로 다음 sb1에 있던 것을 초기화 한다.
                    if (sb1.toString().length() < 3) {
                        sb1.delete(0, sb1.toString().length());
                        flag = false;
                        continue;
                    }
                    // 첫번째 호수를 입력받았으니 flag를 true로 바꾸고 비밀번호 체크할 준비를 한다.
                    flag = true;
                }
            }
        }
        
        return answer;
    }
}