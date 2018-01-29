import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MergeSortImpl implements MergeSortInterface{

	private int[] array;
	private int[] tempArr;
	private int length;
	static int counter = 0;
	static long total = 0;
	@Override
	public void mergeSort(int[] inputArr) {
		if (inputArr == null || inputArr.length == 0) {
            return; //to do
        }
		this.array = inputArr;
        this.length = inputArr.length;
        this.tempArr = new int[length];
        
        long start = System.nanoTime ();
        doMergeSort(0, length - 1);
        total += System.nanoTime() - start;
        int ratio = (int) (total/counter);
        BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new FileWriter("MergeSort.txt"));
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
		for(int i=0;i<inputArr.length;i++){
			try {
				outputWriter.write(Integer.toString(inputArr[i]));
				outputWriter.newLine();
			} catch (IOException e) {
				// TODO 
				e.printStackTrace();
			}			
		}
		try {
			outputWriter.flush();
			outputWriter.close(); 
		} catch (IOException e) {
			// TODO 
			e.printStackTrace();
		}  
	}
	private void doMergeSort(int startIndex, int endIndex) {
        
        if (startIndex < endIndex) {
        	counter++;
            int middle = startIndex + (endIndex - startIndex) / 2;
            doMergeSort(startIndex, middle);
            doMergeSort(middle + 1, endIndex);
            mergeParts(startIndex, middle, endIndex);
        }
    }
 
    private void mergeParts(int lowerIndex, int mid, int higherIndex) {
 
        for (int i = lowerIndex; i <= higherIndex; i++) {
        	counter++;
            tempArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = mid + 1;
        int k = lowerIndex;
        while (i <= mid && j <= higherIndex) {
        	counter++;
            if (tempArr[i] <= tempArr[j]) {
            	counter++;
                array[k] = tempArr[i];
                i++;
            } else {
            	counter++;
                array[k] = tempArr[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
        	counter++;
            array[k] = tempArr[i];
            k++;
            i++;
        }
 
    }
}
