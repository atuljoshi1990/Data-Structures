import java.util.Random;

public class RandomNumberGenerator {

	public int generateNoOfParticipatingGraphs() {
		int randomInt = 0;
		int max = 10;
		int min = 2;
		Random randomGenerator = new Random();
		for (int i = 0; i <= 8; i++) {
			randomInt = randomGenerator.nextInt((max - min) + 1) + min;

		}
		return randomInt;
	}

	public int generateNoOfParticipatingVertices() {
		int randomInt = 0;
		int max = 20;
		int min = 10;
		Random randomGenerator = new Random();
		for (int i = 0; i <= 10; i++) {
			randomInt = randomGenerator.nextInt((max - min) + 1) + min;

		}
		return randomInt;
	}

	public int generateNoOfCommonVertices() {
		int randomInt = 0;
		int max = 5;
		int min = 1;
		Random randomGenerator = new Random();
		for (int i = 0; i <= 4; i++) {
			randomInt = randomGenerator.nextInt((max - min) + 1) + min;

		}
		return randomInt;
	}

}
