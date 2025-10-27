import java.io.*;
import java.util.*;

public class SUMMER_2021H_INTERN_01 {
    public static void main(String[] args) throws IOException {
        String code = "012345";
        String day = "20190620";
        String[] data = {
            "price=80 code=987654 time=2019062113",
            "price=90 code=012345 time=2019062014",
            "price=120 code=987654 time=2019062010",
            "price=110 code=012345 time=2019062009",
            "price=95 code=012345 time=2019062111"
        };
        solution(code, day, data);
    }

    static class Pair implements Comparable<Pair>{
        String time, price;
        public Pair(String time, String price) {
            this.time = time;
            this.price = price;
        }
        public int compareTo(Pair o) {
            return Integer.parseInt(time) - Integer.parseInt(o.time);
        }
    }
    static public int[] solution(String code, String day, String[] data) {
        Map<String, HashMap<String, PriorityQueue<Pair>>> stock = new HashMap<>();
        for (String str : data) {
            String[] mData = str.split(" ");
            String price = mData[0].split("=")[1];
            String ccode = mData[1].split("=")[1];
            String time = mData[2].split("=")[1];
            String dday = time.substring(0, 8);
            System.out.println("price:"+price+", ccode:"+ccode+", time:"+time+", dday:"+dday);

            if (!stock.containsKey(ccode)) {
                stock.put(ccode, new HashMap<>());
            }

            if (!stock.get(ccode).containsKey(dday)) {
                stock.get(ccode).put(dday, new PriorityQueue<Pair>());
            }
            
            stock.get(ccode).get(dday).offer(new Pair(time, price));
            
        }

        int[] answer = new int[stock.get(code).get(day).size()];
        int idx = 0;
        while (!stock.get(code).get(day).isEmpty()) {
            Pair cur = stock.get(code).get(day).poll();
            System.out.println(cur.price+" "+cur.time);
            answer[idx++] = Integer.parseInt(cur.price);
        }
        
        return answer;
    }
}
