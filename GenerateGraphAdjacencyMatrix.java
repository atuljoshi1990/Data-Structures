import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateGraphAdjacencyMatrix {

	public List<int[][]> generateGraphs(int noOfParticipatingGraphs) {

		List<int[][]> graphsList = new ArrayList<int[][]>();
		int noOfParticipatingVertices = 0;
		RandomNumberGenerator generateRandomNumbers = new RandomNumberGenerator();
		for (int k = 1; k <= noOfParticipatingGraphs; k++) {
			noOfParticipatingVertices = generateRandomNumbers.generateNoOfParticipatingVertices();
			int adjacencyMatrix[][] = new int[noOfParticipatingVertices][noOfParticipatingVertices];
			Random randomGenerator = new Random();
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				for (int j = 0; j < adjacencyMatrix[i].length; j++) {
					if(i==j){
						adjacencyMatrix[i][j] = 0;
					}else{
						adjacencyMatrix[i][j] = randomGenerator.nextInt(10);
					}
				}
			}
			graphsList.add(adjacencyMatrix);
		}
		return graphsList;
	}
}
