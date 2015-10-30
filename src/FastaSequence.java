package parsing;

public class FastaSequence 
{
	private final String header;
	private final StringBuffer sequence;
	
	public FastaSequence(String header, StringBuffer sequence) 
	{
		this.header = header;
		this.sequence = sequence;
	}

	// returns the sequence header
	public String getHeader()
	{
		return this.header;	
	}

	// returns the DNA sequence of this FastaSequence
	public String getSequence() 
	{
		return this.sequence.toString();
	}

	public StringBuffer getBuffer()
	{
		return this.sequence;
	}
	
	// returns the number of G's, C's divided by the length of this sequence
	public float getGCRatio()
	{
		int GCs = 0;
		int ATs = 0;
		float GC_content = 0f;
		for(int i = 0; i < sequence.length(); i++)	
		{
			char item = sequence.charAt(i);
			String s = Character.toString(item);
			if(s.equals("G") || s.equals("C"))
				GCs++;
			else
				ATs++;
			
		}
		GC_content = ((float)GCs)/((float)(ATs+GCs));
		return GC_content;
	
	}
}
