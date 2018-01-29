
public class ShortestPathCalculator {

	public int dijkstraAlgoImpl(int adjacencyMatrix[][]) {
		int srcVertex = 0;
		int shortestPathCount = 0;
		int noOfParticipatingVertices = adjacencyMatrix.length;
		int shortestPath[] = new int[noOfParticipatingVertices];
		Boolean treeSet[] = new Boolean[noOfParticipatingVertices];
		for (int i = 0; i < noOfParticipatingVertices; i++) {
			shortestPath[i] = Integer.MAX_VALUE;
			treeSet[i] = false;
		}
		shortestPath[srcVertex] = 0;
		for (int i = 0; i < noOfParticipatingVertices - 1; i++) {
			int minimumWeight = Integer.MAX_VALUE;
			int minimumIndex = -1;
			for (int k = 0; k < noOfParticipatingVertices; k++)
				if (treeSet[k] == false && shortestPath[k] <= minimumWeight) {
					minimumWeight = shortestPath[k];
					minimumIndex = k;
				}
			treeSet[minimumIndex] = true;
			for (int k = 0; k < noOfParticipatingVertices; k++)
				if (!treeSet[k] && adjacencyMatrix[minimumIndex][k] != 0
						&& shortestPath[minimumIndex] != Integer.MAX_VALUE
						&& shortestPath[minimumIndex] + adjacencyMatrix[minimumIndex][k] < shortestPath[k])
					shortestPath[k] = shortestPath[minimumIndex] + adjacencyMatrix[minimumIndex][k];
		}
		System.out.println(" ");
		for (int i = 0; i < noOfParticipatingVertices; i++) {
			shortestPathCount = shortestPathCount + shortestPath[i];
		}
		return shortestPathCount;
	}
}