package BinarySearch.Excersises;

public class Main {
	public static String reverse(String str) {
//		String result = "";
//		for (int i = 0; i < str.length(); i++)
//			result = str.charAt(i) + result;
//		return result.trim();
		// Recursion
		return str.isEmpty() ? "" : reverse(str.substring(1)) + str.charAt(0);
	}
	
	public static void main(String[] args) {
		String a = "Hello";
		System.out.println(reverse(a));
	}
}
