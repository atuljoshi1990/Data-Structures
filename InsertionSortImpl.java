

public class InsertionSortImpl implements InsertionSortInterface{

	@Override
	public Integer[] insertionSort(Integer[] inputArr) {
		if (inputArr == null || inputArr.length == 0) {
            return null; //to do
        }
		int temp;
        for (int i = 1; i < inputArr.length; i++) {
        	BucketSortImpl.counter++;
            for(int j = i ; j > 0 ; j--){
            	BucketSortImpl.counter++;
                if(inputArr[j] < inputArr[j-1]){
                    temp = inputArr[j];
                    inputArr[j] = inputArr[j-1];
                    inputArr[j-1] = temp;
                    BucketSortImpl.counter++;
                }
            }
        }
        return inputArr;
	}
}
