import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SSSPMain {

	public static void main(String[] args) {

		RandomNumberGenerator generateRandomNumbers = new RandomNumberGenerator();
		int noOfParticipatingGraphs = 0;
		
		int noOfCommonVertices = 0;
		List<int[][]> graphsList = null;
		int count = 1;
		noOfParticipatingGraphs = generateRandomNumbers.generateNoOfParticipatingGraphs();
		noOfCommonVertices = generateRandomNumbers.generateNoOfCommonVertices();

		System.out.println("Number Of participating graphs : " + noOfParticipatingGraphs);
		System.out.println("Number Of common vertices : " + noOfCommonVertices);
		System.out.println("##################################################################");
		System.out.println("==================================================================");
		GenerateGraphAdjacencyMatrix generateGraphAdjacencyMatrix = new GenerateGraphAdjacencyMatrix();
		graphsList = generateGraphAdjacencyMatrix.generateGraphs(noOfParticipatingGraphs);
		Iterator<int[][]> it = graphsList.iterator();
		while (it.hasNext()) {
			int[][] adjacencyMatrix = it.next();
			System.out.println(" ");
			System.out.println("Adjacency Matrix " + count++ + " : ");
			System.out.println("Number Of participating vertices : " + adjacencyMatrix.length);
			for (int[] a : adjacencyMatrix) {
				System.out.println(Arrays.toString(a));
			}
			System.out.println("==================================================================");
			
		}
		System.out.println("##################################################################");
		GenerateAndMergeGraph generateAndMergeGraph = new GenerateAndMergeGraph();
		generateAndMergeGraph.generateAndMergeGraph(graphsList, noOfCommonVertices);

	}

}
