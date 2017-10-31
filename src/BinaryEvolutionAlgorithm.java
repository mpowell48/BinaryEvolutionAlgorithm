import java.util.*;

public class BinaryEvolutionAlgorithm {
	
	private List<BinaryString> population = new ArrayList<BinaryString>();
	private int numBreedingPairs, evolutionStage = 0, optimalStringIndex = -1;
	private double mutationChance;
	private boolean optimalBinaryStringBorn = false;
	
	public BinaryEvolutionAlgorithm(int sizeBinString, int numBinStrings, int numBreedingPairs, double mutationChance) {
		for(int i = 0; i < numBinStrings; i++) {
			population.add(new BinaryString(sizeBinString));
		}
		
		if(numBreedingPairs*2 <= population.size()) {
			this.numBreedingPairs = numBreedingPairs;
		}
		
		else {
			this.numBreedingPairs = population.size();
		}
		
		if(0 <= mutationChance && mutationChance <= 1) {
			this.mutationChance = mutationChance;
		}
		
		else {
			this.mutationChance = .002;
		}
		
		checkForOptimalString();
	}
	
	public void evolve() {
		rank();
		reproduce();
		kill();
		evolutionStage++;
		checkForOptimalString();
	}
	
	private void rank() {
		List<BinaryString> temp = new ArrayList<BinaryString>();
		int size = population.size();
		
		for(int i = 0; i < size; i++) {
			int largestNumOnes = 0,
				index = 0;
			
			for(int j = 0; j < population.size(); j++) {
				if (population.get(j).getNumOnes() > largestNumOnes) {
					largestNumOnes = population.get(j).getNumOnes();
					index = j;
				}
			}
			
			temp.add(population.get(index));
			population.remove(index);
		}
		
		population = temp;
	}
	
	private void reproduce() {
		Random r = new Random();
		int breedersLeft = numBreedingPairs * 2, index;
		BinaryString parent1, parent2;
		List<BinaryString> holdParents = new ArrayList<BinaryString>(),
						   holdChildren = new ArrayList<BinaryString>();
		
		for(int i = 0; i < numBreedingPairs; i++) {
			index = r.nextInt(breedersLeft);
			parent1 = population.get(index);
			holdParents.add(population.get(index));
			population.remove(index);
			breedersLeft--;
			
			index = r.nextInt(breedersLeft);
			parent2 = population.get(index);
			holdParents.add(population.get(index));
			population.remove(index);
			breedersLeft--;
			
			holdChildren.add(new BinaryString(parent1, parent2, mutationChance));
		}
		
		for(int i = 0; i < holdParents.size(); i++) {
			population.add(holdParents.get(i));
		}
		for(int i = 0; i < holdChildren.size(); i++) {
			population.add(holdChildren.get(i));
		}
	}
	
	private void kill() {
		for(int i = 0; i < numBreedingPairs; i++) {
			population.remove(population.get(0));
		}
	}
	
	private void checkForOptimalString() {
		for(int i = 0; i < population.size(); i++) {
			if(population.get(i).getAllOnes()) {
				optimalBinaryStringBorn = true;
				optimalStringIndex = i;
				i = population.size();
			}
		}
	}
	
	public boolean getOptimalBinaryStringBorn() {
		return optimalBinaryStringBorn;
	}
	
	public int getOptimalStringIndex() {
		return optimalStringIndex;
	}
	
	public int getEvolutionStage() {
		return evolutionStage;
	}
	
	public List<BinaryString> getPopulation() {
		return population;
	}
}
