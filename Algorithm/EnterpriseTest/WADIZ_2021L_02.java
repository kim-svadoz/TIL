import java.util.*;
public class WADIZ_2021L_02 {
    public static void main(String[] args) {
        String[] code = {
            "a=3", 
            "..a=4", 
            "..b=3", 
            "..print a", 
            ".......a=6", 
            ".......print a", 
            ".......print b", 
            "..print a", 
            "....a=7", 
            "....print a", 
            "print a", 
            "print b", 
            "a=4", 
            "print a", 
            "...print a"
        };

        solution(code);
    }

    static HashMap<String, TreeMap<Integer, Stack<Integer>>> map;
    static List<String> li = new ArrayList<>();
    static public String[] solution(String[] code) {
        String[] answer;
        map = new HashMap<>();
        for (String c : code) {
            int level = c.length() - c.replace(".", "").length();
            if (c.indexOf("print") < 0) {
                String key = c.replace(".", "").split("=")[0];
                int value = Integer.parseInt(c.split("=")[1]);
                if (!map.containsKey(key)) {
                    map.put(key, new TreeMap<Integer, Stack<Integer>>(Collections.reverseOrder()));
                }
                TreeMap<Integer, Stack<Integer>> tm = map.get(key);

                if (!tm.containsKey(level)) {
                    tm.put(level, new Stack<Integer>());
                }
                Stack<Integer> st = tm.get(level);
                st.push(value);
                tm.put(level, st);
                map.put(key, tm);
                
                deleteOutOfLevel(level);
            } else {
                deleteOutOfLevel(level);

                String var = c.split("print ")[1];
                if (map.containsKey(var)) {
                    TreeMap<Integer, Stack<Integer>> tmp = map.get(var);

                    Set<Integer> keyset = tmp.keySet();
                    Iterator<Integer> it = keyset.iterator();
                    while (it.hasNext()) {
                        int k = (int)it.next();
                        if (k > level) {
                            continue;
                        } else {
                            Stack<Integer> stk = tmp.get(k);
                            if (stk.size() > 0) {
    
                                li.add(var+"="+stk.peek());
                                break;
                            }
                        }
                    }
                } else {
                    li.add("error");
                }
            }
        }

        answer = new String[li.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = li.get(i);
            System.out.println(answer[i]);
        }
        
        return answer;
    }

    static void deleteOutOfLevel(int level) {
        // 현재 level보다 큰 블록은 다 지워줘야 한다.
        Set<String> set = map.keySet();
        Iterator<String> itt = set.iterator();
        while (itt.hasNext()) {
            String k = (String) itt.next();
            TreeMap<Integer, Stack<Integer>> tmValue = map.get(k);
            
            Set<Integer> keyset = tmValue.keySet();
            Iterator<Integer> it2 = keyset.iterator();
            while (it2.hasNext()) {
                int lev = (int) it2.next();
                if (lev > level) {
                    it2.remove();
                    map.get(k).remove(lev);
                }
            }

            if (map.get(k).size() == 0) {
                map.remove(k);
            }
        }
    }
}
