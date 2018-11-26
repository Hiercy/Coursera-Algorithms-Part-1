package PriorityQueues.Example1;

import PriorityQueues.MaxPQ;
import PriorityQueues.MinPQ;

public class DynamicMedian<Key extends Comparable<Key>> {

	private int size;

	public int size() {
		return size;
	}

	public static void getMedian(double[] arr) {
		int leng = arr.length;
		MinPQ<Integer> min = new MinPQ<>();
		MaxPQ<Integer> max = new MaxPQ<>();
		double[] median = new double[leng];

		for (int i = 0; i < leng; i++) {
			double num = arr[i];

			if (min.size() == 0 || num < min.peek()) 
				min.insert((int)num);
			else 
				max.insert((int) num);

//			rebalance(min, max);
			median[i] = getMedian(min, max);
		}
		for (int i = 0; i < median.length; i++) {
			System.out.println(median[i]);
		}
		//		return median;
	}

	public void delMedian(int[] arr) {
		MinPQ<Integer> min = new MinPQ<>();
		MaxPQ<Integer> max = new MaxPQ<>();

		if (max.size() > min.size())
			max.delMax();
		else
			min.delMin();
	}

	public static double getMedian(MinPQ<Integer> min, MaxPQ<Integer> max) {
		if (min.size() > max.size())
			return max.max();
		else if (max.size() == min.size())
			return ((double)max.max() + min.min())/2;
		else 
			return min.min();
	}

	public static void main(String[] args) {
		double[] a = {10,1,2,3,4,5,6,7,8,9,21};
		MaxPQ<Integer> p = new MaxPQ<>();
		getMedian(a);
	}
}
