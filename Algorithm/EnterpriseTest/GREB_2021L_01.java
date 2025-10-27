import java.util.*;
public class GREB_2021L_01 {
    public static void main(String[] args) {
        String[] infos = {
            "kim password", "lee abc"
        };
        String[] actions = {
            "ADD 30", 
            "LOGIN kim abc", 
            "LOGIN lee password", 
            "LOGIN kim password", 
            "LOGIN kim password", 
            "LOGIN lee abc", 
            "ADD 30", 
            "ORDER",
            "ORDER",
            "ADD 40",
            "ADD 50"
        };
        solution(infos, actions);
    }

    static List<String> session;
    static List<Integer> cart;
    static List<String> li;
    static HashMap<String, String> hm;
    static public boolean[] solution(String[] infos, String[] actions) {
        session = new ArrayList<>();
        cart = new ArrayList<>();
        li = new ArrayList<>();
        hm = new HashMap<>();

        for (String info : infos) {
            hm.put(info.split(" ")[0], info.split(" ")[1]);
        }

        for (String action : actions) {
            if (action.split(" ")[0].equals("ADD")) {
                int foodId = Integer.parseInt(action.split(" ")[1]);
                Food2Cart(foodId);
            } else if (action.split(" ")[0].equals("LOGIN")) {
                String userId = action.split(" ")[1];
                String userPw = action.split(" ")[2];
                LOGIN(userId, userPw);
            } else if (action.split(" ")[0].equals("ORDER")) {
                ORDER();
            }
        }

        boolean[] answer = new boolean[actions.length];
        for (int i = 0; i < li.size(); i++) {
            if (li.get(i).equals("TRUE")) {
                answer[i] = true;
            } else {
                answer[i] = false;
            }
            System.out.println(answer[i]);
        }
        
        return answer;
    }

    static void LOGIN(String userId, String userPw) {
        if (hm.containsKey(userId)) {
            if (session.size() > 0) {
                li.add("FALSE");
                return;
            }

            if (hm.get(userId).equals(userPw)) {
                li.add("TRUE");
                session.add(userId);
            } else {
                li.add("FALSE");
            }
        } else {
            li.add("FALSE");
        }
    }

    static void Food2Cart(int foodId) {
        if (session.size() != 0) {
            cart.add(foodId);
            li.add("TRUE");
        } else {
            li.add("FALSE");
        }
    }

    static void ORDER() {
        if (cart.size() != 0) {
            li.add("TRUE");
            cart.clear();
        } else {
            li.add("FALSE");
        }
    }
}
