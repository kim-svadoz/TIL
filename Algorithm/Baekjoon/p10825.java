import java.util.*;
import java.io.*;

public class p10825 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        Student[] stu = new Student[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int kor = Integer.parseInt(st.nextToken());
            int eng = Integer.parseInt(st.nextToken());
            int mat = Integer.parseInt(st.nextToken());
            stu[i] = new Student(name, kor, eng, mat);
        }
        Arrays.sort(stu, new Comparator<Student>(){
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.kor == o2.kor) {
                    if (o1.eng == o2.eng) {
                        if (o1.mat == o2.mat) {
                            return o1.name.compareTo(o2.name);
                        } else {
                            return o2.mat - o1.mat;
                        }
                    } else {
                        return o1.eng - o2.eng;
                    }
                } else {
                    return o2.kor - o1.kor;
                }   
            }
        });

        for (int i = 0; i < n; i++) {
            System.out.println(stu[i].name);
        }
    }
    public static class Student {
        String name;
        int kor, eng, mat;
        public Student(String name, int kor, int eng, int mat) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.mat = mat;
        }
    }
}