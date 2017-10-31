import java.util.Random;

public class BinaryString {
	private int[] bin;
	private int numOnes = 0;
	private boolean allOnes = false;
	
	public BinaryString(int binStringSize) {
		bin = new int[binStringSize];
		
		Random r = new Random();
		
		for (int i = 0; i < binStringSize; i++) {
			if(r.nextDouble() < .01) {
				bin[i] = 1;
				numOnes++;
			}
			else {
				bin[i] = 0;
			}
		}
				
		checkForAllOnes();
	}
	
	public BinaryString(BinaryString parent1, BinaryString parent2, double mutationChance) {
		if(parent1.getBinLength() == parent2.getBinLength()) {
			bin = new int[parent1.getBinLength()];
			
			Random r = new Random();
			
			for (int i = 0; i < this.getBinLength(); i++) {
				if(r.nextBoolean()) {
					bin[i] = parent1.getBinIthIndex(i);
					
					if(bin[i] == 1) {
						numOnes++;
					}
				}
				
				else {
					bin[i] = parent2.getBinIthIndex(i);
					
					if(bin[i] == 1) {
						numOnes++;
					}
				}
			}
			
			mutate(mutationChance);
		}
		
		else {
			System.out.println("These two parents cannot breed due to inequal sizes");
		}
		
		checkForAllOnes();
	}
	
	private void mutate(double mutationChance) {
		Random r = new Random();
		
		for(int i = 0; i < bin.length; i++) {
			double d = r.nextDouble();
		
			if(d < mutationChance) {
				if(bin[i] == 1) {
					bin[i] = 0;
					numOnes--;
				}
				
				else {
					bin[i] = 1;
					numOnes++;
				}
			}
		}
	}
	
	private void checkForAllOnes() {
		for(int i = 0; i < bin.length; i++) {
			allOnes = true;
			
			if(bin[i] == 0) {
				allOnes = false;
				i = bin.length;
			}
		}
	}
	
	public int getBinLength() {
		return bin.length;
	}
	
	public int getBinIthIndex(int i) {
		return bin[i];
	}
	
	public int getNumOnes() {
		return numOnes;
	}
	
	public boolean getAllOnes() {
		return allOnes;
	}
}
