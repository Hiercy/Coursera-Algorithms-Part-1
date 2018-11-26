package BinarySearch.Excersises;

import InOut.StdIn;
import InOut.StdOut;

public class UF {

	private int[] id; // Доступ к идентификатору компонента(лес или (множество) деревьев)
	private int[] sz; // Размер компонента для корней
	private int count; // кол-во компонентов
	
	// Инициализация структуры данных union-find с N объектами(от = до n-1)
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
		// Вариант 1 - Быстрый поиск
//		return id[p];
		
		// Вариант 2 - Быстрое объединение
		// Вариант 3 - Быстрое объединение (Взвешенный вариант)
		while (p != id[p])
			p = id[p];
		return p;
	}
	
	// Объединение p и q в один компонент
	public void union(int p, int q) {
		// Вариант 1 - Быстрый поиск
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
		
		// Вариант 2 - Быстрое объединение
		int pRoot = find(p);
		int qRoot = find(q);
		
		if (pRoot == qRoot)
			return;
		
		// Вариант 3 - Быстрое объединение (Взвешенный вариант)
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
		int n = StdIn.readInt(); // Ввод кол-во узлов
		UF uf = new UF(n); // Инициализация N компонентов
		while (!StdIn.isEmpty()) { // Читать, пока список не будет пуст
			int p = StdIn.readInt();
			int q = StdIn.readInt(); // Чтение пары связных узлов
			
			if (uf.connected(p, q)) // Игнорировать если узлы уже связаны
				continue;
			
			uf.union(p, q); // Объеденение компонентов
			StdOut.println(p + " " + q); // Вывод соеденений
		}
		StdOut.println(uf.count + " компонентов");
	}
}
	