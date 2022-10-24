import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Sort {

	public static void main(String args[]) {

		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(6);
		list.add(11);
		list.add(3);
		list.add(1);
		list.add(2);
		list.add(5);
		
		list.forEach(e -> System.out.print(e + " "));
		System.out.println();
		
		for (int i = 1; i <= list.size() - 1; i++) {
			int j = i;
			while (j > 0 && list.get(j - 1) > list.get(j)) {
				swap(list, j - 1, j);
				j--;
			}
		}

		list.forEach(e -> System.out.print(e + " "));
	}

	public static void swap(List<Integer> arr, int i, int j) {
		int temp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, temp);
	}

}
