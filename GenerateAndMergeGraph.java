import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GenerateAndMergeGraph {

	public void generateAndMergeGraph(List<int[][]> graphsList, int noOfCommonVertices) {
		List<Integer> sstList = new ArrayList<Integer>();
		List<int[][]> tempGraphList = new ArrayList<int[][]>();
		for (int i = 0; i < graphsList.size(); i++) {
			int sstMergedGraph = 0;
			int sstPrimaryGraph = 0;
			long start = 0;
			long total = 0;
			ShortestPathCalculator shortestPath = new ShortestPathCalculator();
			int[][] primaryGraph = pickPrimaryGraphRandomly(graphsList,tempGraphList);
			double primaryComplexity = calculateComplexity(primaryGraph);
			tempGraphList.add(primaryGraph);
			System.out.println("Primary Graph : ");
			for (int[] a : primaryGraph) {
				System.out.println(Arrays.toString(a));
			}
			int[][] secondaryGraph = generateSecondaryGraph(primaryGraph, noOfCommonVertices);
			int[][] mergedGraph = mergeGraphs(primaryGraph, secondaryGraph);
			double mergedComplexity = calculateComplexity(mergedGraph);
			tempGraphList.add(primaryGraph);
			start = System.nanoTime ();
			sstPrimaryGraph = shortestPath.dijkstraAlgoImpl(primaryGraph);
			total += System.nanoTime() - start;
			System.out.println("Execution time Primary Graph : "+total);
			System.out.println("Shortest path of Primary Graph: " + sstPrimaryGraph);
			System.out.println("Asymptotic complexity Primary Graph: "+primaryComplexity);
			start = System.nanoTime ();
			sstMergedGraph = shortestPath.dijkstraAlgoImpl(mergedGraph);
			total += System.nanoTime() - start;
			System.out.println("Execution time Merged Graph : "+total);
			System.out.println("Shortest Path of Merged Graph: " + sstMergedGraph);
			System.out.println("Asymptotic complexity Merge Graph: "+mergedComplexity);
			System.out.println(" ");
			sstList.add(sstPrimaryGraph);
			sstList.add(sstMergedGraph);
		}
		int min = sstList.get(0);
		System.out.println("Shortest paths among primary and merged graphs : ");
		for (Integer i : sstList) {
			System.out.println(i);
			if (i < min) {
				min = i;
			}
		}
		System.out.println("Minimum shortest path : " + min);
	}

	private double calculateComplexity(int[][] graph) {
		double complexity = 0.0;
		int vertices = graph.length;
		int edges = 0;
			for (int i = 0; i < graph.length; i++) {
				for (int j = 0; j < graph[i].length; j++) {
					if(graph[i][j]!=0){
						edges++;
					}
				}
			}
			edges = edges-vertices;
			complexity = ((edges+vertices)*(Math.log(vertices)/Math.log(2)));
		return complexity;
	}

	private int[][] mergeGraphs(int[][] primaryGraph, int[][] secondaryGraph) {
		int[] primaryArray = new int[primaryGraph.length * primaryGraph.length];
		int[] secondaryArray = new int[secondaryGraph.length * secondaryGraph.length];
		List<Integer> mergedList = new ArrayList<Integer>();
		for (int i = 0; i < primaryGraph.length; i++) {
			for (int j = 0; j < primaryGraph[i].length; j++) {
				primaryArray[(i * primaryGraph.length) + j] = primaryGraph[i][j];
			}
		}
		for (int i = 0; i < secondaryGraph.length; i++) {
			for (int j = 0; j < secondaryGraph[i].length; j++) {
				secondaryArray[(i * secondaryGraph.length) + j] = secondaryGraph[i][j];
			}
		}
		for (int i = 0; i < primaryArray.length; i++) {
			mergedList.add(primaryArray[i]);
		}
		for (int i = 0; i < secondaryArray.length; i++) {
			if (secondaryArray[i] != primaryArray[i]) {
				mergedList.add(secondaryArray[i]);
			}
		}
		Iterator<Integer> it = mergedList.iterator();
		int[] mergedArray = new int[mergedList.size()];
		Iterator<Integer> listIterator = mergedList.iterator();
		for (int i = 0; i < mergedArray.length && listIterator.hasNext(); i++) {
			mergedArray[i] = listIterator.next();
		}
		int mergedArrayLength = mergedArray.length;
		int mergeGraphLength = (int) Math.sqrt(mergedArrayLength);
		int[][] mergedGraph = new int[mergeGraphLength][mergeGraphLength];
		int count = 0;
		for (int i = 0; i < mergedGraph.length; i++) {
			for (int j = 0; j < mergedGraph[i].length; j++) {
				mergedGraph[i][j] = mergedArray[count++];
			}
		}
		System.out.println("Merged Graph :");
		for (int[] a : mergedGraph) {
			System.out.println(Arrays.toString(a));
		}
		return mergedGraph;
	}

	private int[][] generateSecondaryGraph(int[][] primaryGraph, int noOfCommonVertices) {

		int[][] secondaryGraph = new int[primaryGraph.length][primaryGraph.length];
		Random randomGenerator = new Random();
		for (int i = 0; i < secondaryGraph.length; i++) {
			for (int j = 0; j < secondaryGraph[i].length; j++) {
				int randomWeight = randomGenerator.nextInt(10);
				if(i==j){
					secondaryGraph[i][j] = 0;
				}else{
					if (primaryGraph[i][j] == randomWeight) {
						j--;
					} else {
						secondaryGraph[i][j] = randomWeight;
					}
				}
			}
		}
		for (int i = 0; i < noOfCommonVertices; i++) {
			int randomRow = 0;
			int randomColumn = 0;
			int max = primaryGraph.length - 1;
			int min = 0;
			randomRow = randomGenerator.nextInt((max - min) + 1) + min;
			randomColumn = randomGenerator.nextInt((max - min) + 1) + min;
			secondaryGraph[randomRow][randomColumn] = primaryGraph[randomRow][randomColumn];
		}
		return secondaryGraph;
	}

	private int[][] pickPrimaryGraphRandomly(List<int[][]> graphsList, List<int[][]> tempGraphList) {
		int[][] primaryGraph = null;
		for (int i = 0; i < graphsList.size(); i++) {
			primaryGraph = graphsList.get(new Random().nextInt(graphsList.size()));
			if (tempGraphList.contains(primaryGraph)) {
				i--;
			}
		}
		
		return primaryGraph;
	}
}
