import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuickSortImpl implements QuickSortInterface{

	private int length = 0;
	private int array[];
	static int counter = 0;
	static long total = 0;
	@Override
	public void quickSort(int[] inputArr) {
		
		if (inputArr == null || inputArr.length == 0) {
            return; //to do
        }
        this.array = inputArr;
        length = inputArr.length;
        long start = System.nanoTime ();
        doQuickSort(0, length - 1);
        total += System.nanoTime() - start;
        int ratio = (int) (total/counter);
        BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new FileWriter("QuickSort.txt"));
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
	
    private void doQuickSort(int startIndex, int endIndex) {
        
        int i = startIndex;
        int j = endIndex;
        int pivot = array[startIndex+(endIndex-startIndex)/2];
        //int pivot = array[endIndex];
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
                counter++;
            }
            while (array[j] > pivot) {
                j--;
                counter++;
            }
            if (i <= j) {
            	counter++;
                swapNumbers(i, j);
                i++;
                j--;
            }
        }
        if (startIndex < j){
        	counter++;
        	doQuickSort(startIndex, j);
        }
        if (i < endIndex){
        	counter++;
        	doQuickSort(i, endIndex);
        }
    }
 
    private void swapNumbers(int i, int j) {
        int tempArray = array[i];
        array[i] = array[j];
        array[j] = tempArray;
        counter++;
    }		
}


