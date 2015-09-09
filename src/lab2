package Lab2;

import java.util.Random;

public class nuc {
	public static void main(String[] args)
	{
		Random random = new Random();
		int count = 0;
		for(int i=0; i<1000; i++)
		{
		
		String nucs = "";
		
		char[] letters = {'A', 'T', 'C', 'G'};
		
		for(int j=0; j<3; j++)
		{
			int lets = random.nextInt(4);
			char item = letters[lets];
			nucs = nucs + item;
			if (nucs.equals("AAA"))
			{
				count ++;
			}
		}
		System.out.println(nucs);
		
		}
		System.out.println(count);
	}

}
