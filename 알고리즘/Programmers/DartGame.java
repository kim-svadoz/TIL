package programmers;
public class DartGame{

	public static void main(String[] args) {
		String dartResult = "1D2S#10S";
		Solution(dartResult);
	}
	
	public static int Solution(String dartResult) {
		int answer=0;
		int length = dartResult.length();
		double curScore=0;
		double preScore=0;
		
		for(int i=0; i<length; i++) {
			int pow=1;
			switch(dartResult.charAt(i)) {
				case 'T': //트리플
					pow++;
				case 'D': //더블
					pow++;
				case 'S': //싱글
					curScore = Math.pow(curScore, pow); // pow : 제곱함수
					answer += curScore;
					break;
				case '*':
					answer += preScore;
					answer += curScore;
					curScore *= 2;
					preScore = curScore;
					break;
				case '#':
					answer -= curScore; // rollback
					curScore *= (-1);
                    answer += curScore;
                    preScore = curScore;
                    break;
                default: 
                	preScore = curScore;

                    //curScore 최대값 10. 규칙상 점수 다음에 out of index 안나므로 규칙 이용한 처리
                    if (i > 0 && dartResult.substring(i - 1, i).matches("[-+]?\\d*\\.?\\d+"))
                    {
                        curScore = 10;
                    }
                    else
                    {
                        curScore = Character.getNumericValue(dartResult.charAt(i));
                    }
			}
		
		}
		System.out.println(answer);
		return answer;
	}

}
