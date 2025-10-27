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
				case 'T': //Ʈ����
					pow++;
				case 'D': //����
					pow++;
				case 'S': //�̱�
					curScore = Math.pow(curScore, pow); // pow : �����Լ�
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

                    //curScore �ִ밪 10. ��Ģ�� ���� ������ out of index �ȳ��Ƿ� ��Ģ �̿��� ó��
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
