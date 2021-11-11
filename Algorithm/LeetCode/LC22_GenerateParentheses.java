import java.util.*;
public class LC22_GenerateParentheses {
    public class Solution {
        List<String> list;
        public List<String> generateParenthesis(int n) {
            list = new ArrayList<String>();
            backtrack(new StringBuilder(), 0, 0, n);
            return list;
        }
    
        public void backtrack(StringBuilder sb, int open, int close, int max){
            if(max * 2 == sb.length()){
                list.add(sb.toString());
                return;
            }
    
            if (open < max) {
                backtrack(sb.append("("), open+1, close, max);
                sb.deleteCharAt(sb.length() - 1);
            }
                
            if (close < open) {
                backtrack(sb.append(")"), open, close+1, max);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
