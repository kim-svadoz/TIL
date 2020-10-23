public class Main{
    public static void main(String[] args){
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		solution(genres, plays);
    }
    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Object> genersMap = new HashMap<>();    // 장르 , 곡정보
        Map<String, Integer> playMap = new HashMap<>();     // 장르, 총 장르 재생 수 
        ArrayList<Integer> resultAL = new ArrayList<>();
        for(int i=0; i<genres.length; i++){
            String key = genres[i];
            Hashmap<Integer, Integer> infoMap;      // 곡번호, 재생횟수
                
            if(genresMap.containsKey(key)) {
                infoMap = (HashMap<Integer, Integer>) genersMap.get(key);
            } else {
                infoMap = new HashMap<Integer, Integer>();
            }
            
        }
    }
}