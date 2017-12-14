import java.util.Random;

public class BinaryString {
	private int[][] bin;
	private int numOnes = 0;
	private boolean allOnes = false;
	
	public BinaryString(int binStringSize) {
		bin = new int[binStringSize][2];
		
		Random r = new Random();
		
		for (int i = 0; i < binStringSize; i++) {
			double chance = r.nextDouble();
			
			if(chance < .25) {
				bin[i][0] = 1;
				bin[i][1] = 1;
				numOnes++;
			}
			else if(chance >= .25 && chance < .5) {
				bin[i][0] = 1;
				bin[i][1] = 0;
				numOnes++;
			}
			else if(chance >= .5 && chance < .75) {
				bin[i][0] = 0;
				bin[i][1] = 1;
				numOnes++;
			}
			else if(chance >= .75 && chance < 1) {
				bin[i][0] = 0;
				bin[i][1] = 0;
			}
		}
				
		checkForAllOnes();
	}
	
	public BinaryString(BinaryString parent1, BinaryString parent2, double mutationChance) {
		if(parent1.getBinLength() == parent2.getBinLength()) {
			bin = new int[parent1.getBinLength()][2];
			
			Random r = new Random();
			
			for (int i = 0; i < this.getBinLength(); i++) {
				double chance = r.nextDouble();
				
				if(chance < .25) {
					bin[i][0] = parent1.getBinIthFirstAllel(i);
					bin[i][1] = parent2.getBinIthFirstAllel(i);
					
					if(bin[i][0] == 1 || bin[i][1] == 1) {
						numOnes++;
					}
				}
				else if(chance >= .25 && chance < .5) {
					bin[i][0] = parent1.getBinIthFirstAllel(i);
					bin[i][1] = parent2.getBinIthSecondAllel(i);
					
					if(bin[i][0] == 1 || bin[i][1] == 1) {
						numOnes++;
					}
				}
				else if(chance >= .5 && chance < .75) {
					bin[i][0] = parent1.getBinIthSecondAllel(i);
					bin[i][1] = parent2.getBinIthFirstAllel(i);
					
					if(bin[i][0] == 1 || bin[i][1] == 1) {
						numOnes++;
					}
				}
				else {
					bin[i][0] = parent1.getBinIthSecondAllel(i);
					bin[i][1] = parent2.getBinIthSecondAllel(i);
					
					if(bin[i][0] == 1 || bin[i][1] == 1) {
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
			boolean currentlyExpressed = bin[i][0] == 1 || bin[i][1] == 1;
		
			if(d < mutationChance) {
				if(bin[i][0] == 1) {
					bin[i][0] = 0;
				}
				else {
					bin[i][0] = 1;
				}
			}
			
			d = r.nextDouble();
			
			if(d < mutationChance) {
				if(bin[i][1] == 1) {
					bin[i][1] = 0;
				}
				else {
					bin[i][1] = 1;
				}
			}
			
			if(currentlyExpressed == false && (bin[i][0] == 1 || bin[i][1] == 1)) {
				numOnes++;
			}
			
			if(currentlyExpressed == true && (bin[i][0] == 0 && bin[i][1] == 0)) {
				numOnes--;
			}
		}
	}
	
	private void checkForAllOnes() {
		for(int i = 0; i < bin.length; i++) {
			allOnes = true;
			
			if(bin[i][0] == 0 && bin[i][1] == 0) {
				allOnes = false;
				i = bin.length;
			}
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		
		for(int i = 0; i < bin.length; i++) {
			s = s + bin[i][0];
		}
		
		s = s + "\n";
		
		for(int i = 0; i < bin.length; i++) {
			s = s + bin[i][1];
		}
		
		s = s + "\n";
		s = s + "numOnes: " + numOnes + "\n";
		s = s + "allOnes: " + allOnes + "\n";
		
		return s;
	}
	
	public int getBinLength() {
		return bin.length;
	}
	
	public int getBinIthFirstAllel(int i) {
		return bin[i][0];
	}
	
	public int getBinIthSecondAllel(int i) {
		return bin[i][1];
	}
	
	public int getNumOnes() {
		return numOnes;
	}
	
	public boolean getAllOnes() {
		return allOnes;
	}
}
