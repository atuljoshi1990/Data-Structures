

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BucketSortImpl implements BucketSortInterface{

	static int counter = 0;
	static long total = 0;
	@Override
	public void bucketSort(int[] inputArr) {
		
		if (inputArr == null || inputArr.length == 0) {
            return; //to do
        }
		
        long start = System.nanoTime ();
		int defaultBucketSize = 5;
		int maximumVal = inputArr[0];
		int minimumVal = inputArr[0];
		for (int i = 1; i < inputArr.length; i++) {
			counter++;
		    if(inputArr[i] > maximumVal) {
		    	counter++;
		    	maximumVal = inputArr[i];
		    }else if(inputArr[i] < minimumVal){
		    	counter++;
		    	minimumVal = inputArr[i];
		    }
		}
		int bucketCount = (maximumVal - minimumVal) / defaultBucketSize + 1;
		List<List<Integer>> bucketsList = new ArrayList<List<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
        	counter++;
            bucketsList.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < inputArr.length; i++) {
        	counter++;
            bucketsList.get((inputArr[i] - minimumVal) / defaultBucketSize).add(inputArr[i]);
        }
        int index = 0;
        InsertionSortInterface sort = new InsertionSortImpl();
        for (int i = 0; i < bucketsList.size(); i++) {
        	counter++;
            Integer[] bucketArray = new Integer[bucketsList.get(i).size()];
            bucketArray = bucketsList.get(i).toArray(bucketArray);
            if(0!=bucketArray.length){
            	counter++;
            	bucketArray = sort.insertionSort(bucketArray);
                for (int j = 0; j < bucketArray.length; j++) {
                	counter++;
                	inputArr[index++] = bucketArray[j];
                }	
            }
            
        }
        total += System.nanoTime() - start;
        int ratio = (int) (total/counter);
        BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new FileWriter("BucketSort.txt"));
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
}
