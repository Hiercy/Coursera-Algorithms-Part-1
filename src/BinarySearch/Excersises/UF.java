package BinarySearch.Excersises;

import InOut.StdIn;
import InOut.StdOut;

public class UF {

	private int[] id; // ������ � �������������� ����������(��� ��� (���������) ��������)
	private int[] sz; // ������ ���������� ��� ������
	private int count; // ���-�� �����������
	
	// ������������� ��������� ������ union-find � N ���������(�� = �� n-1)
	public UF(int n) {
		id = new int[n];
		sz = new int[n];
		count = n;
		for (int i = 0; i < n; i++) { 
			id[i] = i;
			sz[i] = 1; 
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int find(int p) {
		// ������� 1 - ������� �����
//		return id[p];
		
		// ������� 2 - ������� �����������
		// ������� 3 - ������� ����������� (���������� �������)
		while (p != id[p])
			p = id[p];
		return p;
	}
	
	// ����������� p � q � ���� ���������
	public void union(int p, int q) {
		// ������� 1 - ������� �����
//		int IDp = find(p);
//		int IDq = find(q);
//		
//		if (IDp == IDq)
//			return;
//		
//		for (int i = 0; i < id.length; i++) {
//			if (id[i] == IDq)
//				id[i] = IDp;
//		}
//		count--;
		
		// ������� 2 - ������� �����������
		int pRoot = find(p);
		int qRoot = find(q);
		
		if (pRoot == qRoot)
			return;
		
		// ������� 3 - ������� ����������� (���������� �������)
		if (sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		} else {
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
		
//		id[pRoot] = qRoot;
		
		count--;
	}
	
	public static void main(String[] args) {
		int n = StdIn.readInt(); // ���� ���-�� �����
		UF uf = new UF(n); // ������������� N �����������
		while (!StdIn.isEmpty()) { // ������, ���� ������ �� ����� ����
			int p = StdIn.readInt();
			int q = StdIn.readInt(); // ������ ���� ������� �����
			
			if (uf.connected(p, q)) // ������������ ���� ���� ��� �������
				continue;
			
			uf.union(p, q); // ����������� �����������
			StdOut.println(p + " " + q); // ����� ����������
		}
		StdOut.println(uf.count + " �����������");
	}
}
	