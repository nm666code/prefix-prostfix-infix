import java.util.Stack;

public class Algorithm {
	
	private static Stack<String> operators = new Stack<String>(); 
	
	public static String toPostfix(String instr) {
		String outstr = "";
		for(char c: instr.toCharArray()) {
			if(!isOperator(c))
				outstr += c;
			else {
				if(operators.isEmpty())
					operators.push(Character.toString(c));
				else {
					if (c == '(')
						operators.add(Character.toString(c));
					else if(c == ')') {
						while(operators.peek().charAt(0) != '(')
							outstr += operators.pop();
						operators.pop();//pop '('
					}
					else {
						while(!operators.empty() && priority(c) <= priority(operators.peek().charAt(0)))
							outstr += operators.pop();
						operators.add(Character.toString(c));
					}	
				}
			}
		}
		while(!operators.empty())
			outstr += operators.pop();
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
				if(operators.isEmpty())
					operators.add(Character.toString(c));
				else {
					if (c == ')')
						operators.push(Character.toString(c));
					else if(c == '(') {
						while(operators.peek().charAt(0) != ')')
							outstr = operators.pop() + outstr;
						operators.pop();//pop '('
					}
					else {
						while(!operators.empty() && priority(c) < priority(operators.peek().charAt(0)))
							outstr = operators.pop() + outstr;
						operators.add(Character.toString(c));
					}	
				}
			}
		}
		while(!operators.empty())
			outstr = operators.pop() + outstr;
		return outstr;	
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
