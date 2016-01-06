
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;


public class NeedlemanWunsch 
{
	
	private static final HashMap<String,Integer> AAs = new HashMap<String,Integer>();
	private static final int linear_gap = -8; //can change this gap penalty
	private static final int[][] BLOSSUM_50 = new int[20][20]; // got 20 from size of Blossum matrix
	private static int[][] scoring_matrix;
	private static int[][] traceback;
	
	static
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("Users/Smatlock/Blossum50.txt"));
			
//			Skip first row
			
			String line = br.readLine();
			line = br.readLine();
			
			int i = 0;
			int j = 0;
			
			while (line != null)
			{
				StringTokenizer st = new StringTokenizer(line);
				AAs.put(st.nextToken(), i);
				while(st.hasMoreTokens())
				{
					BLOSSUM_50[i][j] = Integer.parseInt(st.nextToken());
					j++;
				}
				j = 0;
				i++;
				line = br.readLine();
				if (line.length() < 10)
				{
					line = null;
				}
			}
			
			br.close();
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		String filename = "/Users/Smatlock/twoSeqs.txt";
		try
		{
			
//			See FastaSequence Lab for FastaSequence code
			List<FastaSequence> sequences = FastaSequence.readFastaFile(filename);
			
			String first = sequences.get(0).getSequence();
			String second = sequences.get(1).getSequence();
			
			scoring_matrix = new int[first.length()][second.length()];
			traceback = new int[first.length()][second.length()];
			
			long start_time = System.currentTimeMillis();
			for(int x = 0; x < first.length();x++)
			{
				for (int y = 0; y < second.length(); y++)
				{
					if (x == 0)
					{
						if (y == 0)
						{
							scoring_matrix[x][y] = 0;
							traceback[x][y] = 0;
						}
						else
						{
							scoring_matrix[x][y] = scoring_matrix[x][y-1] + linear_gap;
							traceback[x][y] = -1;
						}
					}
					else
					{
						if (y == 0)
						{
							scoring_matrix[x][y] = scoring_matrix[x-1][y] + linear_gap;
							traceback[x][y] = 1;
						}
						else
						{
							int maxPointer = 0;
							
							int maxVal = scoring_matrix[x-1][y-1] + BLOSSUM_50[AAs.get(""+first.charAt(x))][AAs.get(""+second.charAt(y))];
							if (maxVal < scoring_matrix[x-1][y] + linear_gap)
							{
								maxVal = scoring_matrix[x-1][y] + linear_gap;
								maxPointer = 1;
							}
							if (maxVal < scoring_matrix[x][y-1] + linear_gap)
							{
								maxVal = scoring_matrix[x][y-1] + linear_gap;
								maxPointer = -1;
							}
							
							scoring_matrix[x][y] = maxVal;
							traceback[x][y] = maxPointer;
						}
					}
				}
			}
			
//			Get traceback 
			
			StringBuffer seq1 = new StringBuffer();
			StringBuffer seq2 = new StringBuffer();
			
			int x = traceback.length-1;
			int y = traceback[x].length-1;

			while (x!=0 && y!=0)
			{
				if (traceback[x][y] == 0)
				{
					seq1.append(""+first.charAt(x));
					seq2.append(""+second.charAt(y));
					
					x--;
					y--;
				}
				else if(traceback[x][y] == -1)
				{
					seq1.append("-", 0, 1);
					seq2.append(""+second.charAt(y));
					
					y--;
				}
				else
				{
					seq1.append("" + first.charAt(x));
					seq2.append("-", 0, 1);
					
					x--;
				}
			}
			
			seq1.append(""+first.charAt(x), 0, 1);
			seq2.append(""+second.charAt(y), 0, 1);
			
			String[] results = new String[2];
			
			results[0] = seq1.reverse().toString();
			results[1] = seq2.reverse().toString();
			
			System.out.println(results[0] + "\n" + results[1]);
			System.out.println("Time = "+(System.currentTimeMillis()-start_time)/1000f + " seconds");
			System.out.println(scoring_matrix[scoring_matrix.length-1][scoring_matrix[0].length-1]);
			
		}
		catch (Exception ex)
		
		{
			ex.printStackTrace();
		}
	}
}
