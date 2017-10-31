import java.util.Scanner;

public class BEADriver {

	public static void main(String[] args) {
		
		int sizeBinString = 200, numBinStrings = 60, numBreedingPairs = 15;
		double mutationChance = .002;
		
		sizeBinString = BEADriver.getIntegerInput(sizeBinString, "the size of each binary string");
		numBinStrings = BEADriver.getIntegerInput(numBinStrings, "the number of binary strings");
		numBreedingPairs = BEADriver.getIntegerInput(numBreedingPairs, "the number of breeding pairs");
		
		BinaryEvolutionAlgorithm bea = new BinaryEvolutionAlgorithm(sizeBinString,
																	numBinStrings,
																	numBreedingPairs,
																	mutationChance);
		
		boolean optimalBinaryStringBorn = false;
		
		while(!optimalBinaryStringBorn) {
			bea.evolve();
			if(bea.getOptimalBinaryStringBorn()) {
				System.out.println("The Optimal String has been born!\n" +
								   "After " + bea.getEvolutionStage() + " evolution stages.\n");
				optimalBinaryStringBorn = true;
			}
		}
	}
	
	public static int getIntegerInput(int value, String descriptor) {
		Scanner s = new Scanner(System.in);
		
		boolean inputValid = false;
		
		String input;
		
		while(!inputValid) {
			System.out.print("Please specify " + descriptor + 
							 	".\nPress Enter for default (" +
							 	value + ").\n");
			input = s.nextLine();
			
			if(input.equals("")) {
				inputValid = true;
			} 
			else {
				try {
					value = Integer.parseInt(input);
					inputValid = true;
				}
				catch (NumberFormatException e) {
				  	System.out.println("Not an integer.");
				}
			}
		}
		
		return value;
	}
}
