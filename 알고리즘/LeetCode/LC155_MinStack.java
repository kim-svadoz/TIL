package LeetCode;

import java.util.Stack;

public class LC155_MinStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
    3 : 1,2,3 min=1
    2 : 1,2 min=1
    1 : 1 min=1
    */
	
	class Node{
        int val, min;
        Node(int val, int min){
            this.val = val;
            this.min = min;
        }
    }
    Stack<Node> s = new Stack<>();
    /** initialize your data structure here. */
    
    public void push(int x) {
        Node node = null;
        if(s.isEmpty()){
            node = new Node(x, x);
        }else {
            node = new Node(x, x < s.peek().min ? x : s.peek().min);
        }
        s.push(node);
    }
    
    public void pop() {
        s.pop();
    }
    
    public int top() {
        return s.peek().val;
    }
    
    public int getMin() {
        return s.peek().min;
    }
}
