package proteinGen;

import java.util.Random;
import java.util.Scanner;

public class RandomProteinGenerator 
{
	public static int proteinLength;
	private float[] nonUni = {0.072658f, 0.024692f, 0.050007f, 0.061087f,
	        0.041774f, 0.071589f, 0.023392f, 0.052691f, 0.063923f,
	        0.089093f, 0.023150f, 0.042931f, 0.052228f, 0.039871f,
	        0.052012f, 0.073087f, 0.055606f, 0.063321f, 0.012720f,
	        0.032955f};
	private char[] aminoacids = { 'A', 'C', 'D', 'E', 'F', 'G', 'H', 
			'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 
			'W', 'Y' };
	private String aa = "ACDEFGHIKLMNPQRSTVWY";
	private float[] uniform = {0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 
			0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 
			0.05f, 0.05f};
	private float[] frequencies;
	private boolean useUniformFrequencies;

	public RandomProteinGenerator(boolean useUniformFrequencies)
	{
		this.useUniformFrequencies = useUniformFrequencies;
		if(useUniformFrequencies == true)
		{
			frequencies = uniform;
		}
		if(useUniformFrequencies == false)
		{
			frequencies = nonUni;
		}
	}

	/*
	 * Returns a randomly generated protein of length len.
	 */
	
	public String getRandomProtein(int len)
	{
		Random random = new Random();			
		String prot_seq = "";
		
		if(frequencies.equals(uniform))
		{
			for(int j=0; j<len; j++)
			{
				int lets = random.nextInt(19);
				char item = aminoacids[lets];
				prot_seq = prot_seq + item;
			}
		}
		if(frequencies.equals(nonUni))
		{
			for(int j=0; j<len; j++)
			{
				char item = getRandomProtein(random.nextFloat());
				prot_seq = prot_seq + item;
			}
		}
		
		return prot_seq;
	}
	
	public char getRandomProtein(float f)
	{
		float sum = 0.0f;
		for (int x=0;x<nonUni.length;x++)
		{
			sum += nonUni[x];
			if (f<sum)
			{
				return aminoacids[x];
			}
		}
		return aminoacids[aminoacids.length -1];
	}
	/*
	 * Returns the probability of seeing the given sequence
	 * given the underlying residue frequencies represented by
	 * this class.  For example, if useUniformFrequencies==false in 
	 * constructor, the probability of "AC" would be 0.072658 *  0.024692
	 */
	
	public double getExpectedFrequency(String protSeq)
	{
		
		float freq = 1.0f;
		for(int x=0; x<protSeq.length(); x++)
		{
			char pos = protSeq.charAt(x);
			int index = aa.indexOf(pos);
			freq = freq * frequencies[index];
		}
		
		return freq;
	}
	
	/*
	 * calls getRandomProtein() numIterations times generating a protein with length equal to protSeq.length().
	 * Returns the number of time protSeq was observed / numIterations
	 */
	
	public double getFrequencyFromSimulation( String protSeq, int numIterations )
	{
		int prot_length = protSeq.length();
		double count = 0;
		for(int inc = 0; inc < numIterations; inc++)
		{
			String protein_simulation = getRandomProtein(prot_length);
			if(protSeq.equals(protein_simulation))
			{
				count++;
			}
		}
		double observation = count/numIterations;
		return observation;
				
	}
	
	public static void main(String[] args) throws Exception
	{
		Scanner in = new Scanner(System.in);
		RandomProteinGenerator uniformGen = new RandomProteinGenerator(true);
		String testProtein = "ACD";
		int numIterations =  10000000;
		System.out.println("How long would you like your random protein to be?");
		proteinLength = in.nextInt();
		System.out.println(uniformGen.getRandomProtein(proteinLength));
		System.out.println("Uniform Expected Frequency Test:" + uniformGen.getExpectedFrequency(testProtein));  // should be 0.05^3 = 0.000125
		System.out.println("Uniform Frequency Simulation: " + uniformGen.getFrequencyFromSimulation(testProtein,numIterations));  // should be close
		
		RandomProteinGenerator realisticGen = new RandomProteinGenerator(false);
		
		// should be 0.072658 *  0.024692 * 0.050007 == 8.97161E-05
		System.out.println("Nonuniform Expected Frequency Test: " + realisticGen.getExpectedFrequency(testProtein));  
		System.out.println("Nonuniform Frequency Simulation: " + realisticGen.getFrequencyFromSimulation(testProtein,numIterations));  // should be close
		
	}
}
