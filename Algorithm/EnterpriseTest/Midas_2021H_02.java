import java.io.*;
import java.util.*;

public class Midas_2021H_02 {
    public static void main(String[] args) {
        int n = 10;
        int[] v1 = {1, 10, 6, 5, 6, 9};
        int[] v2 = {3, 7, 2, 8, 7, 3};
        int[] num = {3, 4, 5, 1, 8, 7, 9, 2};
        int[] amount = {10, 5, 6, -1, -8, 2, -2, 5};
        solution(n, v1, v2, num, amount);
    }

    static List<List<Integer>> list;
    public static int solution(int n, int[] v1, int[] v2, int[] num, int[] amount) {
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int vlen = v1.length;
        int[] group = new int[n + 1];
        Arrays.fill(group, -1);
        int groupNum = 0;
        for (int i = 0; i < vlen; i++) {
            // A와 B는 같은 팀
            int studentA = v1[i];
            int studentB = v2[i];

            // 두 학생 모두 어디에도 속해있지 않다. -> 새로운 그룹에 할당해준다.
            if (group[studentA] == -1 && group[studentB] == -1) {
                list.get(groupNum).add(studentA);
                list.get(groupNum).add(studentB);
                group[studentA] = groupNum;
                group[studentB] = groupNum;
                groupNum++;
            } else if (group[studentA] == -1 && group[studentB] != -1) { // A가 속해 있지 않고, B가 속해있다.
                list.get(group[studentB]).add(studentA);
                group[studentA] = group[studentB];
            } else if (group[studentA] != -1 && group[studentB] == -1) { // A가 속해있고, B가 속해 있지 않다.
                list.get(group[studentA]).add(studentB);
                group[studentB] = group[studentA];
            } else {  // 두 학생 모두 속한 그룹이 있다. -> 그룹을 합친다.
                // B그룹을 A그룹으로 합칠 것이다.
                if (group[studentA] == group[studentB]) continue;
                int dest = group[studentA];
                int target = group[studentB];
                for (int k : list.get(target)) {
                    list.get(dest).add(k);
                }
                list.get(target).clear();
                for (int j = 0; j <= n; j++) {
                    if (group[j] == target) {
                        group[j] = group[studentA];
                    }
                }
            }
        }
        // 그룹에 속해 있지 않은 학생들을 따로 한 그룹으로 묶어준다.
        for (int i = 1; i <= n; i++) {
            if (group[i] == -1) {
                list.get(groupNum).add(i);
                group[i] = groupNum;
            }
        }

        int numlen = num.length;
        // student가 score만큼 상벌점을 얻는다.
        List<List<Integer>> total = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            total.add(new ArrayList<>());
        }
        for (int i = 0; i < numlen; i++) {
            int student = num[i];
            int score = amount[i];
            total.get(group[student]).add(score);
        }

        List<Integer> maxGroupNum = new ArrayList<>();
        int maxScore = -1;
        for (int i = 0; i < total.size(); i++) {
            List<Integer> tmp = total.get(i);
            int sum = 0;
            if (tmp.size() != 0) {
                for (int k : tmp) {
                    sum += k;
                }
            }
            if (maxScore < sum) {
                maxGroupNum.clear();
                maxScore = sum;
                maxGroupNum.add(i);
            } else if (maxScore == sum) {
                maxGroupNum.add(i);
            }
        }

        int answer = n;
        for (int curGroupNum : maxGroupNum) {
            if (list.get(curGroupNum).size() == 0) continue;
            Collections.sort(list.get(curGroupNum));
            answer = Math.min(list.get(curGroupNum).get(0), answer);
        }

        System.out.println(answer);
        return answer;
    }
}
