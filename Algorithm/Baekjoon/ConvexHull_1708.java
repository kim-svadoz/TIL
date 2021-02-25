package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class ConvexHull_1708{
    static int n;
    // x, y 절대값은 절대 40,000을 넘지 않음
    static Point first = new Point(40001, 40001);
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        List<Point> points = new ArrayList<Point>();
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        // 점들 중, x좌표값이나, y좌표값이 가장 작은 점을 기준점으로 잡는다
        for(int i=0; i<points.size(); i++){
            if(points.get(i).y < first.y){
	            first = points.get(i);    
            } else if(points.get(i).y == first.y){
                if(points.get(i).x < first.x)
                    first = points.get(i);
            }
        }
        // 기준점과 나머지 점들이 ccw로 반시계방향(좌회전)이 되도록 정렬을 시키고, 만약 일직선상에 있으면 거리가 증가하게끔 정렬한다.
        points.sort(new Comparator<Point>(){
            /* 두 값을 처리한 리턴 결과가 음수,0,양수 이냐에 따라 첫번 째 인자의 정렬순위가 낮음, 같음,높음으로 정의된다.
            compare(Product o1, Product o2)
            ex) o1(1), 02(99)일 경우
            return o2-o1일 때 return 결과가 양수임으로 o1의 우선순위가 더 높다(내림차순)
            
            ex) o1(1), o2(99)일 경우
            reutrn o1-o2일 때는 return 결과가 음수이므로 o1의 우선순위가 더 낮다(오름차순)
            
            */
            public int compare(Point second, Point third){
                int ccwR = ccw(first, second, third);
                if(ccwR > 0) // 반시계
                    return -1; // 오름차순
                else if (ccwR < 0)
                    return 1; // 내림차순
                else if (ccwR == 0){
                    long distR1 = dist(first, second);
                    long distR2 = dist(first, third);
                    if(distR1 > distR2)
                        return 1;
                }
                return -1;
            }
        });
        
        // 그라함 스캔 알고리즘
        Stack<Point> stack = new Stack<Point>();
        stack.add(first);
        for(int i=1; i<points.size(); i++){
            // 시계방향이면 제거한다.
            while(stack.size() > 1 && ccw(stack.get(stack.size()-2), stack.get(stack.size()-1), points.get(i)) <= 0){
                stack.pop();
            }
            stack.add(points.get(i));
        }
        
        bw.write(stack.size() + "\n");
        
        bw.flush();
        br.close();
        bw.close();
    }
    // ccw알고리즘 => 시계방향:-1, 일직선:0, 반시계:1
    static int ccw(Point a, Point b, Point c){
        long ccwR = (a.x*b.y + b.x*c.y + c.x*a.y) - (b.x*a.y + c.x*b.y + a.x*c.y);
        if(ccwR > 0)
            return 1;
        if(ccwR < 0)
            return -1;
        return 0;
    }
    static long dist(Point a, Point b){
        return (b.x-a.x)*(b.x-a.x) + (b.y-a.y)*(b.y-a.y);
    }
    static class Point{
        long x, y;
        public Point(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
}
