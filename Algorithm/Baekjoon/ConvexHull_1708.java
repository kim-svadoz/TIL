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
    // x, y ���밪�� ���� 40,000�� ���� ����
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
        
        // ���� ��, x��ǥ���̳�, y��ǥ���� ���� ���� ���� ���������� ��´�
        for(int i=0; i<points.size(); i++){
            if(points.get(i).y < first.y){
	            first = points.get(i);    
            } else if(points.get(i).y == first.y){
                if(points.get(i).x < first.x)
                    first = points.get(i);
            }
        }
        // �������� ������ ������ ccw�� �ݽð����(��ȸ��)�� �ǵ��� ������ ��Ű��, ���� �������� ������ �Ÿ��� �����ϰԲ� �����Ѵ�.
        points.sort(new Comparator<Point>(){
            /* �� ���� ó���� ���� ����� ����,0,��� �̳Ŀ� ���� ù�� ° ������ ���ļ����� ����, ����,�������� ���ǵȴ�.
            compare(Product o1, Product o2)
            ex) o1(1), 02(99)�� ���
            return o2-o1�� �� return ����� ��������� o1�� �켱������ �� ����(��������)
            
            ex) o1(1), o2(99)�� ���
            reutrn o1-o2�� ���� return ����� �����̹Ƿ� o1�� �켱������ �� ����(��������)
            
            */
            public int compare(Point second, Point third){
                int ccwR = ccw(first, second, third);
                if(ccwR > 0) // �ݽð�
                    return -1; // ��������
                else if (ccwR < 0)
                    return 1; // ��������
                else if (ccwR == 0){
                    long distR1 = dist(first, second);
                    long distR2 = dist(first, third);
                    if(distR1 > distR2)
                        return 1;
                }
                return -1;
            }
        });
        
        // �׶��� ��ĵ �˰�����
        Stack<Point> stack = new Stack<Point>();
        stack.add(first);
        for(int i=1; i<points.size(); i++){
            // �ð�����̸� �����Ѵ�.
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
    // ccw�˰����� => �ð����:-1, ������:0, �ݽð�:1
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
