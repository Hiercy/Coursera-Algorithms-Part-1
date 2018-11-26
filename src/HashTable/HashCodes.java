package HashTable;

public class HashCodes {
	
	public HashCodes() {
	}

	public static void main(String[] args) {
		HashCodes a = new HashCodes();
		HashCodes b = new HashCodes();
		
		int hash  = a.hashCode();
		int hash1 = b.hashCode();
		
		System.out.println(a.hashCode());
		System.out.println(hash);
		
		System.out.println("============HASH B===========");
		
		System.out.println(b.hashCode());
		System.out.println(hash1);
		
		System.out.println(b == a);
	}
}
