
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SortingMain {

	public static void main(String[] args) {

		int[] inputArr = null;
		String filePath = "";
		filePath = "NumberPattern.txt";
		int[] finalArray = null;
		inputArr = getinputValuesFromFile(filePath);
		inputArr = shuffleArrayValues(inputArr);
		BucketSortInterface bucketSort = new BucketSortImpl();
		System.out.println("Indiviual sorts called first");
		System.out.println("#################################################");
		bucketSort.bucketSort(inputArr);
		System.out.println("Bucket Sort");
		System.out.println ("Time taken in nano seconds : "+BucketSortImpl.total);
		System.out.println("The sorted output is in the BucketSort.txt file");
		MergeSortInterface mergeSort = new MergeSortImpl();
		mergeSort.mergeSort(inputArr);
		System.out.println("Merge Sort");
		System.out.println ("Time taken in nano seconds : "+MergeSortImpl.total);
		System.out.println("The sorted output is in the MergeSort.txt file");
		QuickSortInterface quickSort = new QuickSortImpl();
		quickSort.quickSort(inputArr);
		System.out.println("Quick Sort");
		System.out.println ("Time taken in nano seconds : "+QuickSortImpl.total);
		System.out.println("The sorted output is in the QuickSort.txt file");
		System.out.println("#################################################");
		AdaptiveSortInterface sort = new AdaptiveSortImpl();
		System.out.println("Adaptive Sort");
		finalArray = sort.adaptiveSort(inputArr);
		System.out.println("The sorted output is in the AdaptiveSort.txt file");

	}

	private static int[] shuffleArrayValues(int[] inputArr) {
		int index;
		Random random = new Random();
		for (int i = inputArr.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			if (index != i) {
				inputArr[index] ^= inputArr[i];
				inputArr[i] ^= inputArr[index];
				inputArr[index] ^= inputArr[i];
			}
		}
		return inputArr;
	}

	private static int[] getinputValuesFromFile(String filePath) {

		int[] inputArr = null;
		Scanner scanner = null;
		List<Integer> arrayList = null;
		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		arrayList = new ArrayList<Integer>();
		while (scanner.hasNext()) {
			arrayList.add(scanner.nextInt());
		}
		inputArr = new int[arrayList.size()];
		Iterator<Integer> listIterator = arrayList.iterator();
		for (int i = 0; i < inputArr.length && listIterator.hasNext(); i++) {
			inputArr[i] = listIterator.next();
		}
		return inputArr;
	}
}
