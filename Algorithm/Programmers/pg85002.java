/*
    프로그래머스 위클리 챌린지 > 6주차
    정렬
*/
import java.util.*;
class pg85002 {
    class Boxer {
        int idx, total, win;
        int overWeightWin;
        int weight;
        double winRate;
        public Boxer(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
        
        public void add(boolean w, int weight) {
            this.total++;
            if (w == true) {
                this.win++;
                
                if (this.weight < weight) {
                    this.overWeightWin++;
                }
            }
            
            this.winRate = (win * 100 / (double)total);
        }
    }
    List<Boxer> list;
    public int[] solution(int[] weights, String[] head2head) {
        list = new ArrayList<>();
        
        for (int i = 0; i < weights.length; i++) {
            list.add(new Boxer(i + 1, weights[i]));
        }
        
        for (int i = 0; i < head2head.length; i++) {
            for (int j = 0; j < head2head[i].length(); j++)  {
                if (i == j) continue;
                char c = head2head[i].charAt(j);
                if (c == 'W') {
                    list.get(i).add(true, weights[j]);
                } else if (c == 'L') {
                    list.get(i).add(false, weights[j]);
                }
            }
        }
        
        /*
        for (int i = 0; i < list.size(); i++) {
            Boxer b = list.get(i);
            System.out.println("idx:"+b.idx+", total:"+b.total+", win:"+b.win+", winRate:"+b.winRate+", overWeightWin:"+b.overWeightWin+", weight:"+b.weight+", winRate:"+b.winRate);
        }
        */
        
        Collections.sort(list, new Comparator<Boxer>() {
            public int compare(Boxer o1, Boxer o2) {
                if (o1.winRate == o2.winRate) {
                    if (o1.overWeightWin == o2.overWeightWin) {
                        if (o1.weight == o2.weight) {
                            return o1.idx > o2.idx ? 1 : -1; // 4순위: 번호 오름차순
                        }
                        return o1.weight < o2.weight ? 1 : -1; // 3순위: 자기 몸무게 내림차순
                    }
                    return o1.overWeightWin < o2.overWeightWin ? 1 : -1; // 2순위: 자신보다 무거운 몸무게 이긴 횟수 내림차순
                }
                return o1.winRate < o2.winRate ? 1 : -1; // 1순위: 승률 내림차순
            };
        });
        
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).idx;
        }
        return answer;
    }
}