package Week5Exmp;

public class Exmp1 {
	public static void main(String[] args) {
		System.out.println("Find values such that (a==b) is true but x.equals(y) is false: ");
		System.out.println("a = 128\nb = 128");
		Object a = 128;
		Object b = 128;
		
		System.out.print("a == b: ");
		System.out.println(a == b);
		System.out.println("a.equals(b): " + a.equals(b));
		
		System.out.println("===============================");
		
		Object d = -Double.NaN;
		Object c = Double.NaN;
		
		System.out.print("c == d: ");
		System.out.println(c == d);
		System.out.println("c.equals(d)" + c.equals(d));
		
		System.out.println("===============================");
		
		Integer x = 10;
		Integer y = 10;
		
		x = 1024;
		y = 1024;
		
		System.out.print("x == y: ");
		System.out.println(x == y);
		System.out.println("x.equals(y)" + x.equals(y));
		
		System.out.println("===============================");
		
		int z = 0;
		double t = 0.0;
		
		System.out.print("z == t: ");
		System.out.println(z == t);
//		System.out.println("z.equals(t)" + z.equals(t));
	}
}
