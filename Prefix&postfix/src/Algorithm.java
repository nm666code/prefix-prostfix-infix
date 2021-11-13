import java.util.Stack;

public class Algorithm {
	
	private static Stack<Character> operators = new Stack<Character>(); 
	private static Stack<Double> numStack = new Stack<Double>();
	
	public static String toPostfix(String instr) {
		String outstr = "";
		for(char c: instr.toCharArray()) {
			if(!isOperator(c))
				outstr += c;
			else {
				if (c == '(')
					operators.push(c);
				else if(c == ')') {
					while(operators.peek() != '(')
						outstr += " " + operators.pop();
					operators.pop();//pop '('
				}
				else {
					while(!operators.empty() && priority(operators.peek()) >= priority(c))
						outstr += " " + operators.pop();
					operators.push(c);
				}
				outstr += " ";
			}
		}
		while(!operators.empty())
			outstr += " " + operators.pop();
		return outstr;	
	}
	
	public static String toPrefix(String instr) {
		String outstr = "";
		char c;
		for(int i = instr.length() - 1; i >= 0; i--) {
			c = instr.charAt(i);
			if(!isOperator(c))
				outstr = c + outstr;
			else {
				if (c == ')')
					operators.push(c);
				else if(c == '(') {
					while(operators.peek() != ')')
						outstr = operators.pop() + " " + outstr;
					operators.pop();//pop '('
				}
				else {
					while(!operators.empty() && priority(c) < priority(operators.peek()))
						outstr = operators.pop() + " " + outstr;
					operators.push(c);
				}
				outstr = " " + outstr;
			}
		}
		while(!operators.empty())
			outstr = operators.pop() + " " + outstr;
		return outstr;	
	}
	
	public static double calPostfix(String instr) {
		Double n1, n2;
		String[] substrs = instr.split("\\s+");
		for(String s: substrs) {
			if(s.matches("[0-9]+"))
				numStack.push(Double.parseDouble(s));
			else {
				n2 = numStack.pop();
				n1 = numStack.pop();
				switch (s.charAt(0)) {
				case '+':
					numStack.push(n1+n2);
					break;
				case '-':
					numStack.push(n1-n2);
					break;
				case '*':
					numStack.push(n1*n2);
					break;
				case '/':
					numStack.push(n1/n2);
					break;
				}
			}	
		}
		return (double)Math.round(numStack.pop()*100)/100;
	}
	
	public static double calPrefix(String instr) {
		Double n1, n2;
		String[] substrs = instr.split("\\s+");
		for(int i = substrs.length - 1; i >= 0; i--) {
			String s = substrs[i];
			if(s.matches("[0-9]+"))
				numStack.push(Double.parseDouble(s));
			else {
				n1 = numStack.pop();
				n2 = numStack.pop();
				switch (s.charAt(0)) {
				case '+':
					numStack.push(n1+n2);
					break;
				case '-':
					numStack.push(n1-n2);
					break;
				case '*':
					numStack.push(n1*n2);
					break;
				case '/':
					numStack.push(n1/n2);
					break;
				}
			}	
		}
		return (double)Math.round(numStack.pop()*100)/100;
	}
	
	private static boolean isOperator(char c) {
		switch(c) {
			case '+':
				return true;
			case '-':
				return true;
			case '*':
				return true;
			case '/':
				return true;
			case '(':
				return true;
			case ')':
				return true;
			default:
				return false;
		}
	}
	
	private static int priority(char c) {
		switch(c) {
			case '(' :
				return 0;
			case ')' :
				return 0;
			case '+':
				return 1;
			case '-':
				return 1;
			case '*':
				return 2;
			case '/':
				return 2;
			default:
				return -1;
		}
	}
}
