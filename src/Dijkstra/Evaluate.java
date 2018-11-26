package Dijkstra;

import InOut.StdIn;
import InOut.StdOut;
import edu.princeton.cs.algs4.Stack;

public class Evaluate {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<>();
		Stack<Double> val = new Stack<>();

		while (!StdIn.isEmpty()) {
			String str = StdIn.readString();

			if (str.equals("("));
			else if (str.equals("+"))
				ops.push(str);
			else if (str.equals("-"))
				ops.push(str);
			else if (str.equals("*"))
				ops.push(str);
			else if (str.equals("/"))
				ops.push(str);
			else if (str.equals("sqrt"))
				ops.push(str);
			else if (str.equals(")")) { // Если ")", то достаем, вычисление и печать результата
				String op = ops.pop();
				double v = val.pop();

				if (op.equals("+"))
					v = val.pop() + v;
				else if (op.equals("-"))
					v = val.pop() - v;
				else if (op.equals("*"))
					v = val.pop() * v;
				else if (op.equals("/"))
					v = val.pop() / v;
				else if (op.equals("sqrt"))
					v = Math.sqrt(v);
				val.push(v);
			} else 
				val.push(Double.parseDouble(str));
		}
		StdOut.println(val.pop());
	}
}
