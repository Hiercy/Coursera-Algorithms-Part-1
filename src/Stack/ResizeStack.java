package Stack;

public class ResizeStack<Item>{
	private Item[] a; // �������� �����
	private int N; // ������

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void push(Item item) {
		// ���� �� ��� ����� ��� ���������?
		// ���� ����� ���, �� ������� ������ � 2 ����
		if (N == a.length)
			resize(2*a.length);
		a[N++] = item;
	}

	// ����������� ���� � ������ ������� �������
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}

	public Item pop() {
		// �������� �������� � �������� �����
		Item item = a[--N];
		a[N] = null; // �������� �������� �� ������

		if (N > 0 && N == a.length/4)
			resize(a.length/2);

		return item; 
	}

	public static void main(String[] args) {
		ResizeStack<Integer> s = new ResizeStack<Integer>();

		for (int i = 0; i < 10000; i++) {
			s.push(i);
		}
		System.out.println("After Push loop");
		System.out.println("Size = " + s.size());

		while (!s.isEmpty()) {
			s.pop();
		}
		System.out.println("After Pop loop");
		System.out.println("Size = " + s.size());
	}
}
