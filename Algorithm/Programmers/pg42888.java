import java.io.IOException;
import java.util.*;
public class pg42888 {
    public static void main(String[] args) throws IOException {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        solution(record);
    }
    static class Command {
        String com, id;
        public Command (String com, String id) {
            this.com = com;
            this.id = id;
        }
    }
    public static String[] solution(String[] record) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        Queue<Command> q = new LinkedList<>();
        
        for (String str : record) {
            String[] arr = str.split(" ");
            String Do = arr[0];
            if (Do.equals("Enter")) {
                String uid = arr[1];
                String nickname = arr[2];
                map.put(uid, nickname);
                q.offer(new Command(Do, uid));
            } else if (Do.equals("Change")) {
                String uid = arr[1];
                String nickname = arr[2];
                map.put(uid, nickname);
            } else {
                String uid = arr[1];
                q.offer(new Command(Do, uid));
            }
        }
        
        int size = q.size();
        System.out.println(size);
        String[] result = new String[size];
        
        for (int i = 0; i < size; i++) {
            Command now = q.poll();
            String Do = now.com;
            String uid = now.id;
            String nick = map.get(uid);
            sb.append(nick).append("님이 ");
            if (Do.equals("Enter")) {
                sb.append("들어왔습니다.");
            } else if (Do.equals("Leave")) {
                sb.append("나갔습니다.");
            }
            
            result[i] = sb.toString();
            sb.setLength(0);
        }
        
        return result;
    }
}