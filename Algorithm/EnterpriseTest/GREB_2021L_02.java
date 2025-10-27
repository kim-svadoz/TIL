import java.util.*;
public class GREB_2021L_02 {
    public static void main(String[] args) {
        String[] grades = {
            "DS7651 A0", "CA0055 D+", "AI5543 C0", "OS1808 B-", "DS7651 B+", "AI0001 F", "DB0001 B-", "AI5543 D+", "DS7651 A+", "OS1808 B-"
        };

        solution(grades);
    }

    static class myComparator implements Comparator<Object> {
        Map<String, Integer> map;
        public myComparator(Map<String, Integer> map) {
            this.map = map;
        }
        public int compare(Object o1, Object o2) {
            if (map.get(o1) == map.get(o2)) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    static List<String> li;
    static Map<String, Integer> map;
    static LinkedHashSet<String> lhs;
    static public String[] solution(String[] grades) {
        li =  new ArrayList<>();
        map = new LinkedHashMap<>();
        lhs = new LinkedHashSet<>();

        for (String grade : grades) {
            String key = grade.split(" ")[0];
            int value = grade2Int(grade.split(" ")[1]);
            if (!map.containsKey(key)) {
                map.put(key, value);
            } else {
                // 더 높은 성적이라면 업데이트
                if (value < map.get(key)) {
                    map.remove(key);
                    map.put(key, value);
                }
                // 같거나 낮은성적이라면 최신 성적이 우선순위므로 업데이트 X
            }
        }

        Map<String, Integer> result = sort(map);

        Iterator<String> it = result.keySet().iterator();
        while (it.hasNext()) {
            String gradeId = it.next();
            int val = map.get(gradeId);
            String gradeNum = grade2String(val);
            li.add(gradeId + " " + gradeNum);
        }


        String[] answer = new String[li.size()];
        for (int i = 0; i < li.size(); i++) {
            answer[i] = li.get(i);
            System.out.println(answer[i]);
        }
        return answer;
    }

    static LinkedHashMap<String, Integer> sort(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int comp = (o1.getValue() - o2.getValue()) * 1;
                return comp == 0 ? comp * -1 : comp;
            }
        });

        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        for (Iterator<Map.Entry<String, Integer>> it = entries.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            lhm.put(entry.getKey(), entry.getValue());
        }
        return lhm;
    }

    static String grade2String(int num) {
        if (num == 1) {
            return "A+";
        } else if (num == 2) {
            return "A0";
        } else if (num == 3) {
            return "A-";
        } else if (num == 4) {
            return "B+";
        } else if (num == 5) {
            return "B0";
        } else if (num == 6) {
            return "B-";
        } else if (num == 7) {
            return "C+";
        } else if (num == 8) {
            return "C0";
        } else if (num == 9) {
            return "C-";
        } else if (num == 10) {
            return "D+";
        } else if (num == 11) {
            return "D0";
        } else if (num == 12) {
            return "D-";
        } else {
            return "F";
        }
    }

    static int grade2Int(String grade) {
        if (grade.equals("A+")) {
            return 1;
        } else if (grade.equals("A0")) {
            return 2;
        } else if (grade.equals("A-")) {
            return 3;
        } else if (grade.equals("B+")) {
            return 4;
        } else if (grade.equals("B0")) {
            return 5;
        } else if (grade.equals("B-")) {
            return 6;
        } else if (grade.equals("C+")) {
            return 7;
        } else if (grade.equals("C0")) {
            return 8;
        } else if (grade.equals("C-")) {
            return 9;
        } else if (grade.equals("D+")) {
            return 10;
        } else if (grade.equals("D0")) {
            return 11;
        } else if (grade.equals("D-")) {
            return 12;
        } else  {
            return 13;
        }
    }
}
