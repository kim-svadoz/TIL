import java.util.*;

public class pg49993 {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {
            "BACDE", "CBADF", "AECB", "BDA"
        };
        solution(skill, skill_trees);
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        List<Character> list = new ArrayList<>();

        char[] skill_arr = skill.toCharArray();

        for (char c : skill_arr) {
            list.add(c);
        }
        char mustSkill; 
        for (String s : skill_trees) {
            int idx = 0;
            mustSkill = skill_arr[idx];
            char[] userSkillTree = s.toCharArray();
            boolean flag = true;
            for (int i = 0; i < userSkillTree.length; i++) {
                // 스킬트리에 해당하는 스킬이므로 체크가 필요하다.
                char c = userSkillTree[i];
                if (list.contains(c)) {
                    if (c == mustSkill) {
                        if (++idx >= skill.length()) break;
                        mustSkill = skill_arr[idx];
                        continue;
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                answer++;
            }
        }

        System.out.println(answer);
        return answer;
    }
}
