
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String postfix = Algorithm.toPostfix("256+168/33*44");
		String prefix = Algorithm.toPrefix("386+486*586-1");
		System.out.println(postfix);
		System.out.println(prefix);
		System.out.println(Algorithm.calPostfix(postfix));
		System.out.println(Algorithm.calPrefix(prefix));
	}

}
